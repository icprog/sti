/*! \file agilent34970aDevice.cpp
 *  \author David M.S. Johnson
 *  \brief Source-file for the class agilent34970aDevice
 *  \section license License
 *
 *  Copyright (C) 2009 David Johnson <david.m.johnson@stanford.edu>\n
 *  This file is part of the Stanford Timing Interface (STI).
 *
 *  The STI is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  The STI is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with the STI.  If not, see <http://www.gnu.org/licenses/>.
 */



#include "agilent34970aDevice.h"

agilent34970aDevice::agilent34970aDevice(ORBManager*    orb_manager, 
							std::string    DeviceName, 
							std::string    Address, 
							unsigned short ModuleNumber,
							unsigned short primaryGPIBAddress) : 
STI_Device(orb_manager, DeviceName, Address, ModuleNumber)
{ 
	primaryAddress = primaryGPIBAddress; // normally 16
	secondaryAddress = 0;
	outputOn = false; // default to power off
	frequency = 3.36; // in GHz
	frequencyIncrement = 0.001; // in GHz
	outputPower = -7.0; // in dBm
	gpibID = "Have Not Queried"; // initializes with null result - haven't checked yet
	dmmEnabled = true;
	activeChannel = 301; //ranges from 301-322.
	upperChannel = 320; //321 & 322 are thermocouple channels
	lowerChannel = 301;

}

agilent34970aDevice::~agilent34970aDevice()
{
}


void agilent34970aDevice::defineAttributes() 
{
	addAttribute("GPIB ID", gpibID); //response to the IDN? query
	addAttribute("DMM Enabled", "True", "True, False");
	addAttribute("Active Channel", activeChannel);
	//addAttribute("Frequency (GHz)", frequency);
	//addAttribute("Frequency Increment (GHz)", frequencyIncrement);
	//addAttribute("Output", "Off", "Off, On");
	//addAttribute("Output Power (dBm)", outputPower);
}

void agilent34970aDevice::refreshAttributes() 
{
	setAttribute("GPIB ID", gpibID); //response to the IDN? query
	setAttribute("DMM Enabled", (dmmEnabled ? "True" : "False"));
	setAttribute("Active Channel", activeChannel);
	//setAttribute("Frequency (GHz)", frequency);
	//setAttribute("Frequency Increment (GHz)", frequencyIncrement);
	//setAttribute("Output", (outputOn ? "On" : "Off"));
	//setAttribute("Output Power (dBm)", outputPower);
}

bool agilent34970aDevice::updateAttribute(string key, string value)
{
	//converts desired command into GPIB command string and executes via gpib controller partner device
	double tempDouble;
	std::string tempString;
	double tempValue = 0;
	bool successDouble = stringToValue(value, tempDouble);
	bool commandSuccess;
	bool success = false;
	std::string result;
	std::string commandString;

	if(key.compare("GPIB ID") == 0)
	{
		tempString = queryDevice("*idn?");
		if(tempString.compare("") == 0)
			success = false;
		else
		{
			success = true;
			gpibID = tempString;
		}
		std::cerr << std::endl << "Identification: " << gpibID << std::endl;
	}
	else if(key.compare("DMM Enabled") == 0)
	{
		if(value.compare("True") == 0)
			commandSuccess = commandDevice("INSTRUMENT:DMM ON");
		else
			commandSuccess = commandDevice("INSTRUMENT:DMM OFF");
		
		if(commandSuccess)
		{
			success = stringToValue(queryDevice("INSTRUMENT:DMM?"), tempValue);
			if(tempValue == 1)
				dmmEnabled = true;
			else if(tempValue == 0)
				dmmEnabled = false;
			else
			{
				success = false;
				dmmEnabled = false;
			}
		}

		std::cerr << std::endl << "DMM Status: " << tempValue << std::endl;
	}
	else if(key.compare("Active Channel") == 0)
	{
		if(successDouble)
		{
			uInt32 tempChannel = tempDouble;
			std::cerr << std::endl << "Channel :" << tempChannel << std::endl;
			if((tempChannel <= upperChannel) && (tempChannel >= lowerChannel))
				activeChannel = tempChannel;	
			else
				std::cerr << std::endl << "Please choose a channel between 301 & 322." << std::endl;
		}
		

		
		std::cerr << std::endl << "Channel :" << activeChannel << std::endl;



		if(activeChannel < 321)
			commandString = "MEAS:VOLT:DC? (@" + valueToString(activeChannel) + ")";
		else
			commandString = "MEAS:TEMP? (@" + valueToString(activeChannel) + ")";

		std::cerr << std::endl << "Channel " << activeChannel << ": " << queryDevice(commandString) << std::endl;
		success = true;
	}


	return success;
}

void agilent34970aDevice::defineChannels()
{
	for( int i = 0; i < 20; i++ )
	{
		addInputChannel(i, DataDouble);
	}
	
}

bool agilent34970aDevice::readChannel(ParsedMeasurement& Measurement)
{
	double tempMeasurement;

	uInt32 measureChannel = Measurement.channel() + 301;

	std::string commandString = "MEAS:VOLT:DC? (@" + valueToString(measureChannel) + ")";

	bool measureSuccess = stringToValue(queryDevice(commandString),tempMeasurement);
	Measurement.setData(tempMeasurement);
	return measureSuccess;
}

void agilent34970aDevice::parseDeviceEvents(const RawEventMap& eventsIn, 
        SynchronousEventVector& eventsOut) throw(std::exception)
{
	
	
}
void agilent34970aDevice::definePartnerDevices()
{
	addPartnerDevice("gpibController", "li-gpib.stanford.edu", 12, "gpib"); //local name (shorthand), IP address, module #, device name as defined in main function
}

void agilent34970aDevice::stopEventPlayback()
{
}

std::string agilent34970aDevice::execute(int argc, char **argv)
{
	unsigned short channel; 
	std::string result;
	
	//command comes as "channelToRead"
	if(argc == 2)
	{
		bool channelSuccess = stringToValue(argv[1], channel);
		ParsedMeasurement parsedMeasurement(1, channel, 0);
		readChannel(parsedMeasurement);
		result = valueToString(parsedMeasurement.numberValue());
	}
	else
		result = ""; //command needs to contain only 1 piece of information

	return result;
}
bool agilent34970aDevice::deviceMain(int argc, char **argv)
{
	return false;
}
std::string agilent34970aDevice::queryDevice(std::string query)
{
	std::string queryString;
	std::string result;
	queryString = valueToString(primaryAddress) + " " + valueToString(secondaryAddress) + " " + query + " 1";
	std::cerr << "query_str: " << queryString << std::endl;

	result = partnerDevice("gpibController").execute(queryString.c_str()); //usage: partnerDevice("lock").execute("--e1");

	return result;
}
bool agilent34970aDevice::commandDevice(std::string command)
{
	std::string commandString;
	std::string result;
	commandString = valueToString(primaryAddress) + " " + valueToString(secondaryAddress) + " " + command + " 0";

	result = partnerDevice("gpibController").execute(commandString.c_str()); //usage: partnerDevice("lock").execute("--e1");

	if(result.compare("1")==0)
		return true;
	else
		return false;
}