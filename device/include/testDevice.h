
#ifndef TESTDEVICE_H
#define TESTDEVICE_H

#include "STI_Device.h"
#include <string>


class testDevice : public STI_Device
{
public:
	
	testDevice(ORBManager* orb_manager, std::string DeviceName, 
		std::string Address, unsigned short ModuleNumber) : 
	STI_Device(orb_manager, DeviceName, Address, ModuleNumber) {};
	~testDevice() {};

	// Device main()
	bool deviceMain(int argc, char** argv);

	// Device Attributes
	void defineAttributes();
	void refreshAttributes() {};
	bool updateAttribute(std::string key, std::string value);

	// Device Channels
	void defineChannels();
	bool readChannel(unsigned short channel, const MixedValue& valueIn, MixedData& dataOut) {return false;}
	bool writeChannel(unsigned short channel, const MixedValue& value) {return false;}
	
	// Device Command line interface setup
	std::string execute(int argc, char** argv);
	void definePartnerDevices() {addPartnerDevice("test", "128.12.174.77", 1, "testDevice");};
	
	// Device-specific event parsing
	void parseDeviceEvents(
		const RawEventMap&      eventsIn, 
		SynchronousEventVector& eventsOut) throw(std::exception)
	{parseDeviceEventsDefault(eventsIn, eventsOut);}

	// Event Playback control
	void stopEventPlayback() {};
	void pauseEventPlayback() {};
	void resumeEventPlayback() {};

};

#endif

