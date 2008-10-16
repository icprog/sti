/*! \file EtraxBus.h
 *  \author Jason Michael Hogan
 *  \brief Include-file for the class EtraxBus
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

#ifndef ETRAXBUS_H
#define ETRAXBUS_H

#ifdef HAVE_BUS_SPACE_H
extern "C" {
#  include <bus_space.h>
}
#endif

#include <utils.h>

class EtraxBus {

public:

	EtraxBus(unsigned int MemoryAddress);
	~EtraxBus();

	void writeData(unsigned int data);

	void setMemoryAddress(unsigned int MemoryAddress);
	unsigned int getMemoryAddress() const;

	void setupMemoryBus();

private:

	uInt32 memoryAddress;

#ifdef HAVE_BUS_SPACE_H

	// variables for setting the address for writing via CPU addresses using bus_space_write
	static bus_space_tag_t  tag;
	bus_space_handle_t      ioh, ioh1;
	Int32                   old_speed;

#endif


};

#endif
