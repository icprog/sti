/*************************************************************************
 *
 * Name:   USB1408FS.h
 *
 * C++ Windows header for Stanford Timing Interface to control the Measurement Computing USB-1408FS DAQ
 *
 * David Johnson 6/3/2008
 * Kasevich Group - Stanford University
 *
 *
 **************************************************************************/


#ifndef AUTOLOCKER_H
#define AUTOLOCKER_H

// Includes
#include <iostream>
#include "Vortex6000.h"
#include "USB1408FS.h"
#include "math.h"



// Class Definitions
class AUTOLOCKER 
	{ 
	public:
		
		AUTOLOCKER(); //constructor

		void enable_lock(); 
		void disable_lock(); 
		bool enable_vortex_loop();

	protected:
	
	private:

		int channel;

		double pv; // program piezo voltage
		double measured_pv; //piezo voltage from controller
		double feedback_signal; // signal applied to current input on vortex controller from CsLock board
		double set_point_voltage; // voltage level for abs(feedback_signal) above which increment piezo_voltage by piezo_adjustment
		double piezo_adjustment; // amount to adjust piezo voltage when feedback_signal above threshold_voltage 

		double avg_signal;

		int vortex_current_usb_channel;

		//initialize vortex laser
		Vortex6000 vortex6000;

		//initialize USB-1408FS DAQ
		#ifndef USB_DAQ
		#define USB_DAQ
			USB1408FS usb1408fs;
		#endif


	};


#endif
