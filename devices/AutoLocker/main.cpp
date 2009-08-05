

#include "ENET_GPIB_device.h"
#include "AGILENT8648A.h"
#include "AGILENT54621A.h"
#include "HP83711B.h"
#include "Matlab.h"

#include <windows.h>
#include <iostream> //cin & cout commands
#include <string> //needed for string manipulations
#include <sstream> //needed for conversion of int to string
#include <vector> //needed to be able to use vectors for data storage

int main(int argc, char* argv[])

{
	//define data containers
	std::vector <double> timeVectorOff;
	std::vector <double> signalVectorOff;
	std::vector <double> timeVectorSerrodyne;
	std::vector <double> signalVectorSerrodyne;
	std::string data;


	HP83711B hp83711b(15, 0, 7);
	//AGILENT8648A agilent8648a(-2.9);
	agilent54621A Agilent54621A;
	MATLABPLOTTER matlabplotter;

	//Agilent54621A.quickCommand("*RST");
	Agilent54621A.what_is_my_name();

	/*hp83711b.what_is_my_name();
	hp83711b.get_output_state();
	hp83711b.set_power(-1);
	hp83711b.output_on();
	hp83711b.get_output_state();
	std::cerr << "Power is: " << hp83711b.get_power() << std::endl;
	hp83711b.getSystemError();
	hp83711b.getSystemError();
	hp83711b.getSystemError();
	*/
	
	

	bool triggerSuccess = Agilent54621A.setupTrigger("External", "Edge", "Pos");
	bool acquisitionSetupSuccess = Agilent54621A.setupAcquisition();
	bool scalingInformationSuccess = Agilent54621A.queryScalingInformation();
	
	bool parseSuccess = false;
	double frequency;
	double power;

	double initialPower = 5.0; //dBm
	double finalPower = 7.0;
	double powerIncrement = 0.1;
	double outputPower = initialPower;
	unsigned int numberAmplitudeSteps = unsigned int(floor((abs(finalPower - initialPower)) / powerIncrement + 1));

	double outputFrequency = 1000; //in MHz
	double frequencyIncrement = 5;
	double finalFrequency = 1600; //in MHz
	unsigned int numberFrequencySteps = unsigned int(floor((finalFrequency - outputFrequency)/frequencyIncrement + 1));
	
	for(unsigned int i = 0; i < numberFrequencySteps; i++) // frequency loop
	{
		outputPower = initialPower;
		for(unsigned int j = 0; j < numberAmplitudeSteps; j++)// amplitude loop
		{
			hp83711b.set_power(outputPower);
			hp83711b.set_frequency(outputFrequency/1000);
			//agilent8648a.set_power(outputPower);
			//agilent8648a.set_frequency(outputFrequency);

			data = Agilent54621A.saveData();
			parseSuccess = Agilent54621A.parseData(data, timeVectorOff, signalVectorOff);
	
			hp83711b.output_on();
			hp83711b.get_output_state();
			//agilent8648a.output_on();
			//agilent8648a.get_output_state();
	
			data = Agilent54621A.saveData();
			parseSuccess = Agilent54621A.parseData(data, timeVectorSerrodyne, signalVectorSerrodyne);
	
			hp83711b.output_off();
			hp83711b.get_output_state();
			//agilent8648a.output_off();
			//agilent8648a.get_output_state();

			//matlabplotter.plotData(timeVectorOff, signalVectorOff, true);
			//matlabplotter.plotData(timeVectorSerrodyne, signalVectorSerrodyne, false);

			frequency = hp83711b.get_frequency();
			power = hp83711b.get_power();
			//frequency = agilent8648a.get_frequency();
			//power = agilent8648a.get_power();

			matlabplotter.savedata(i*numberAmplitudeSteps + j, frequency, power, timeVectorOff, signalVectorOff, timeVectorSerrodyne, signalVectorSerrodyne);
	
			timeVectorOff.clear();
			signalVectorOff.clear();
			timeVectorSerrodyne.clear();
			signalVectorSerrodyne.clear();

			outputPower = outputPower + powerIncrement;
		}
		outputFrequency = outputFrequency + frequencyIncrement;
	}

	


//	std::cout << "Continue?...";
//	std::cin >> cont;
//	if (!cont) {return 0;}

	return 0;
};

//	std::vector <std::string> recipients;
//	recipients.push_back("david.m.johnson@stanford.edu");
//	recipients.push_back("sdickers@stanford.edu");
