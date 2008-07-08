/*************************************************************************
 *
 * Name:   AutoLocker.cpp
 *
 * C++ Windows source code for Stanford Timing Interface to auto-lock the master laser to Rb
 *
 * David Johnson 7/8/2008
 * Kasevich Group - Stanford University
 *
 *
 **************************************************************************/

// Include files 

#include "AutoLocker.h"

//===========================================================================

AUTOLOCKER::AUTOLOCKER()
{
	//stuff	




}

//===========================================================================

void AUTOLOCKER::enable_lock() 
{

	system("putty -load \"ep-timing1\" -m enable_lock.txt");

}

//===========================================================================

void AUTOLOCKER::disable_lock() 
{

	system("putty -load \"ep-timing1\" -m disable_lock.txt");

}
bool AUTOLOCKER::enable_vortex_loop()
{

	//use vortex GPIB controller to zero-out applied voltage on piezo

	feedback_signal = 0; // signal applied to current input on vortex controller from CsLock board
	set_point_voltage = 3.5; // voltage level for abs(feedback_signal) above which increment piezo_voltage by piezo_adjustment
	piezo_adjustment = 0.1; // amount to adjust piezo voltage when feedback_signal above threshold_voltage 

	//Scan over Rb87 cooling line (1.12 GHz red of 85 cooling)
	// assumes laser is locked ~400 MHz offset from Rb85 cooling


	//specify channel to read
	vortex_current_usb_channel = 6;

	int i;

	measured_pv = vortex6000.get_piezo_voltage();
	pv = measured_pv;

	while(1) {
		measured_pv = vortex6000.get_piezo_voltage();
		if (pv != measured_pv) {
			std::cerr << "Discrepancy between measured & expected piezo voltage." << std::endl;
			std::cerr << "Measured PV: " << measured_pv << std::endl;
		}

		std::cerr << "piezo voltage: " << pv << std::endl;
		avg_signal = 0;

		while( fabs(avg_signal) < set_point_voltage ) {
			
			for(i=1; i < 10; i++) {
				feedback_signal = feedback_signal + usb1408fs.read_input_channel(vortex_current_usb_channel);
				Sleep(100);
			}

			avg_signal = 0.1 * feedback_signal;
			feedback_signal = 0;

			std::cerr << "avg_signal: " << avg_signal << std::endl;
		}
		
		if(avg_signal > set_point_voltage) {
			pv = pv - piezo_adjustment;
			std::cerr << "**piezo voltage: " << pv << std::endl;
			vortex6000.set_piezo_voltage(pv);
		}

		if(avg_signal < (-1.0*set_point_voltage)) {
			pv = pv + piezo_adjustment;
			std::cerr << "**piezo voltage: " << pv << std::endl;
			vortex6000.set_piezo_voltage(pv);
		}

	}
}
