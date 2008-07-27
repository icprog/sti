/*! \file RemoteDevice.h
 *  \author Jason Michael Hogan
 *  \brief Include-file for the class RemoteDevice
 *  \section license License
 *
 *  Copyright (C) 2008 Jason Hogan <hogan@stanford.edu>\n
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

#ifndef REMOTEDEVICE_H
#define REMOTEDEVICE_H

#include "device.h"
#include "Attribute.h"

#include <string>
#include <vector>
#include <map>

class ORBManager;
class Attribute;


typedef std::map<std::string, Attribute> attributeMap;

class RemoteDevice
{
public:

	RemoteDevice(ORBManager* orb_manager, std::string name, 
		STI_Server_Device::TDevice& device);

	RemoteDevice() {};
	~RemoteDevice();

	bool isActive();
	void activate();
	void deactivate();

	std::string deviceName();

	// Question - What is the 1st const doing here???
//	STI_Server_Device::TDevice const * device() const;
	STI_Server_Device::TDevice  * device();

	bool addChannel(const STI_Server_Device::TDeviceChannel & tChannel);

	void printChannels();

	//Forwarding functions
	bool setAttribute(std::string key, std::string value);
	attributeMap const * getAttributes();

	std::vector<STI_Server_Device::TDeviceChannel> const * getChannels() const;

	STI_Server_Device::TMeasurementSeq*	getStreamingData(
		                                             unsigned short channel,
                                                     double         initial_t, 
                                                     double         final_t, 
                                                     double         delta_t);
	std::string DataTransferErrMsg() const;
	STI_Server_Device::TMeasurementSeqSeq* measurements();

private:

	bool isUnique(const STI_Server_Device::TDeviceChannel & tChannel);

	static void acquireObjectReferencesWrapper(void* object);
    
	void acquireObjectReferences();

	ORBManager* orbManager;

	attributeMap attributes;

	std::vector<STI_Server_Device::TDeviceChannel> channels;
	bool active;

	std::string name_l;
	STI_Server_Device::TDevice tDevice;

	STI_Server_Device::Configure_var ConfigureRef;
//	STI_Server_Device::DataTransfer_var timeCriticalDataRef;
//	STI_Server_Device::DataTransfer_var streamingDataRef;
	STI_Server_Device::DataTransfer_var DataTransferRef;

	std::string configureObjectName;
	std::string dataTransferObjectName;
//	std::string timeCriticalObjectName;
//	std::string streamingObjectName;
};

#endif
