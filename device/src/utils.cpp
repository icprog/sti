
#ifdef HAVE_CONFIG_H
#  include <config.h>
#endif

#include <types.h>
#include <utils.h>
#include <stdlib.h>
#include <ctype.h>
#include <sstream>
#include <iostream>

using namespace std;

//Different compilers read numeric constants differently
#ifdef __GNUC__
#  define ULL10TO8  100000000ULL
#  define ULL10TO16 10000000000000000ULL
#else
#  define ULL10TO8  100000000
#  define ULL10TO16 10000000000000000
#endif

const uInt32 Max32bit = 42*ULL10TO8+94967295;
const uInt64 Max64bit = 1844*ULL10TO16+67440737*ULL10TO8+9551616;

string tolower(const string &a)
{
	string res(a);
	for(string::iterator i=res.begin(); i!=res.end(); i++)
		*i=tolower(*i);
	return res;
}

uInt32 str_to_u32(const string &str)
//returns an int from a string
{
	istringstream i(str);
	uInt32 x;
	i >> x;
	return x;
}

uInt64 str_to_u64(const string &str)
// returns an Int64 from a string - doesn't work for strings longer than 20 digits
{
	uInt64 tail=0;
	uInt64 middle=0;
	uInt64 head=0;

	if(str.length()<=8)
		tail = str_to_u32(str.substr(0,str.length()));

	if((str.length() <= 16) & (str.length()>8))
	{
		tail = str_to_u32(str.substr(str.length() - 8,8));
		middle = str_to_u32(str.substr(0,str.length() - 8));
	}
		
	if((str.length()>16) & (str.length()<= 20))
	{
		tail = str_to_u32(str.substr(str.length() - 8,8));
		middle = str_to_u32(str.substr(str.length() - 16,8));
		head = str_to_u32(str.substr(0,str.length()-16));
	}

	if(str.length()>20)
	{
		cerr << "Error: String is longer than an Int64 can hold." << endl;
		return 0;
	}

	uInt64 num = tail + (middle * ULL10TO8) + (head * ULL10TO16);
	return num;

}

string u32_to_str(uInt32 num,int width)
{
	ostringstream o;
	o.fill('0');
	o.width(width);
	o << num;
	return o.str();
}

string u16_to_str(uInt16 num)
{
	ostringstream o;
	o << num;
	return o.str();
}

string u64_to_str(uInt64 num)
{
	string res;
	uInt32  over;
	int    width=0;

	if(num>=ULL10TO16)
	{
		over = num/ULL10TO16;
		num %= ULL10TO16;
		res += u32_to_str(over,width);
		width=8;
	}

	if(num>=ULL10TO8)
	{
		over = num/ULL10TO8;
		num %= ULL10TO8;
		res += u32_to_str(over,width);
		width=8;
	}

	res += u32_to_str(num,width);

	return res;
}

double str_to_dbl(const std::string &str)
{
	istringstream i(str);
	double x;
	i >> x;
	return x;
}

std::string dbl_to_str(double num)
{
	ostringstream o;
	o << num;
	return o.str();
}

uInt16 dbl_to_range16(double in, double in_min, double in_max, uInt16 out_min,
	uInt16 out_max)
{
	if(in<in_min)
		return out_min;
	if(in>in_max)
		return out_max;
	return static_cast<uInt16>((out_max-out_min)*(in-in_min)/(in_max-in_min)+out_min);
}

uInt32 dbl_to_range32(double in, double in_min, double in_max, uInt32 out_min,
	uInt32 out_max)
{
	if(in<in_min)
		return out_min;
	if(in>in_max)
		return out_max;
	return static_cast<uInt32>((out_max-out_min)*(in-in_min)/(in_max-in_min)+out_min);
}

string u8_to_hex(uInt8 num)
{
	string res;
	uInt8   nibble;

	nibble=(num&0xf0)>>4;
	if(nibble>=10)
		res='a'+nibble-10;
	else
		res='0'+nibble;

	nibble=num&0x0f;
	if(nibble>=10)
		res+='a'+nibble-10;
	else
		res+='0'+nibble;

	return res;
}

string u16_to_hex(uInt16 num)
{
	string res;

	res+=u8_to_hex((num&0xff00)>>8);
	res+=u8_to_hex(num&0x00ff);

	return res;
}

string u32_to_hex(uInt32 num)
{
	string res;

	res=u8_to_hex((num&0xff000000)>>24);
	res+=u8_to_hex((num&0x00ff0000)>>16);
	res+=u8_to_hex((num&0x0000ff00)>>8);
	res+=u8_to_hex(num&0x000000ff);

	return res;
}

string u64_to_hex(Int64 num)
{
	string res;

	res=u32_to_hex(num>>32);
	res+=u32_to_hex(num&Max32bit);

	return res;
}

//This converts the first two characters of str into a number, assuming
//a hexadecimal format. If the string is shorter or the characters are
//not hexadecimal, return either zero or the vlaue of the first character
//only (if the second character is not hex).
uInt8 hex_to_u8(const string &str)
{
	uInt8 res=0;
	char c;

	if(str.length()>=1) {
		c=tolower(str[0]);
		if(c>='0' && c <='9')
			res=static_cast<uInt8>(c-'0');
		else if(c>='a' && c <='f')
			res=static_cast<uInt8>(c-'a')+10;
		else
			return 0;
	}

	if(str.length()>=2) {
		c=tolower(str[1]);
		if(c>='0' && c <='9')
			res=16*res+static_cast<uInt8>(c-'0');
		else if(c>='a' && c <='f')
			res=16*res+static_cast<uInt8>(c-'a')+10;
		else
			return res;
	}

	return res;
}

//Creates the checksum of str.
//Here, the first two characters are dropped (they should be "Sx", x={0, 1, 2, 3, 4, 5...})
//Then all further entries are taken as hexadecimal values, summed byte-wise
//Finally, ~sum is returned as a 2-character hex value
string SREC_chksum(const string &str)
{
	uInt8 sum=0;

	for(unsigned i=2; i<str.length(); i+=2)
		sum+=hex_to_u8(str.substr(i,2));

	return u8_to_hex(~sum);
}