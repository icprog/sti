/*! \file
 *  \author Olaf Mandel
 *  \brief Include-file for the class ParsedMeasurement
 *  \section license License
 *
 *  Copyright (C) 2008 Olaf Mandel <mandel@stanford.edu>\n
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

#ifndef PARSEDMEASUREMENT_H
#define PARSEDMEASUREMENT_H

#include <string>
#include "parsedpos.h"

namespace libPython
{

/*! \brief The ParsedMeasurement class represents information for one
 *      measurement event
 *
 * This class is instantiated by the meas() function of the
 * \link timing_module Timing module\endlink.
 */
class ParsedMeasurement
{
public:
    unsigned    channel;
    double      time;
    std::string desc;
    ParsedPos   position;

    ParsedMeasurement(unsigned channel, double time, const std::string &desc,
                      const ParsedPos &position);
    ~ParsedMeasurement();
};

};

#endif
