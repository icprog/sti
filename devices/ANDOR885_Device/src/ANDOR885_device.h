/*! \file ANDOR885_device.cpp
 *  \author Susannah Dickerson 
 *  \brief Source file for the iXon DV-885 camera
 *  \section license License
 *
 *  Copyright (C) 2009 Susannah Dickerson <sdickers@stanford.edu>\n
 *  This file is part of the Stanford Timing Interface (STI).
 *	
 *	This structure shamlessly derived from source code originally by Jason
 *	Hogan <hogan@stanford.edu> and David M.S. Johnson <david.m.johnson@stanford.edu>
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

#ifndef ANDOR885_DEVICE_H
#define ANDOR885_DEVICE_H

#include "FPGA_Device.h"
#include <math.h>
#include <iostream>
#include "atmcd32d.h"
#include <stdio.h>

#define TRIGGERMODE_INTERNAL           0
#define TRIGGERMODE_EXTERNAL           1
/* 
#define TRIGGERMODE_EXTERNAL_START     6
#define TRIGGERMODE_EXTERNAL_EXPOSURE  7
#define TRIGGERMODE_EXTERNAL_SOFTWARE 10
*/

#define READMODE_MULTI_TRACK           1
#define READMODE_RANDOM_TRACK          2
#define READMODE_SINGLE_TRACK          3
#define READMODE_IMAGE                 4

#define EXPOSURE_DEFAULT            0.01f //exposure time; f denotes a float

#define TTL_OPEN_LOW                   0  // external shutter opens on low TTL
#define TTL_OPEN_HIGH                  1  // external shutter opens on high TTL
#define SHUTTERMODE_AUTO               0  // shutter controlled automatically
#define SHUTTERMODE_OPEN               1  // shutter always open
#define SHUTTERMODE_CLOSE              2  // shutter always closed
#define SHUTTER_CLOSE_TIME             1  // time it takes to close shutter in ms
#define SHUTTER_OPEN_TIME              1  // time it takes to open shutter in ms


class ANDOR885_Device : public FPGA_Device
{

public:

	ANDOR885_Device(ORBManager* orb_manager, 
		std::string DeviceName, 
		std::string IPAddress,
		unsigned short ModuleNumber);
	~ANDOR885_Device();

//STI_Device functions

	// Device setup
	bool deviceMain(int argc, char **argv);
	bool deviceExit();

	// Device Attributes
	void defineAttributes();
	void refreshAttributes();
	bool updateAttribute(std::string key, std::string value);

	// Device Channels
	void defineChannels();
	bool readChannel(ParsedMeasurement &Measurement);

	// Device Command line interface setup
	std::string execute(int argc, char **argv);
	void definePartnerDevices(); // requires none

	// Device-specific event parsing
	void parseDeviceEvents(const RawEventMap &eventsIn, 
		boost::ptr_vector<SynchronousEvent>  &eventsOut) throw(std::exception);

	// Event Playback control
	void stopEventPlayback() {};

	void writeData(uInt32 data);

private:
	
	short wordsPerEvent();
	bool InitializeCamera();

	//Inherent camera parameters
	AndorCapabilities caps;                     // AndorCapabilities structure
	char              model[32];                // headmodel
	int 			  gblXPixels;       		// dims of
	int				  gblYPixels;       		//      CCD chip
	int				  VSnumber;					//Location of fastest vertical speed in speed index table
	int				  HSnumber;					//Location of fastest horizontal speed in speed index table
	int               ADnumber;                 // AD Index



	//Camera parameters we can change
	int	acquisitionMode;						//Acquisition Mode; usually Single Scan (1) or Run Till Abort (5)
	int readMode;								//Readout Mode; usually Image (4)
	float exposureTime;							//Exposure time in seconds; usually 0.01
	float accumulateTime;						//Accumulation cycle time; not usually used.
	float kineticTime;							//Kinetic cylce time; not usually used.
	int	ttl;									//Determines if shutter opens or closes on a TTL high
	int	shutterMode;							//Shutter Mode; usually Open (1) 
	int	closeTime;								//Time required to close shutter in ms; usually 1
	int	openTime;								//Time required to open shutter in ms; usually 1
	int	triggerMode;							//Trigger Mode; usually Internal (0) or External (1)


};


#endif
