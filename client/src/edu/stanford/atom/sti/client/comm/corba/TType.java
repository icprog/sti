package edu.stanford.atom.sti.client.comm.corba;


/**
* edu/stanford/atom/sti/client/comm/corba/TType.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./client.idl
* Tuesday, August 26, 2008 10:21:41 AM PDT
*/

public class TType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 4;
  private static edu.stanford.atom.sti.client.comm.corba.TType[] __array = new edu.stanford.atom.sti.client.comm.corba.TType [__size];

  public static final int _TypeNumber = 0;
  public static final edu.stanford.atom.sti.client.comm.corba.TType TypeNumber = new edu.stanford.atom.sti.client.comm.corba.TType(_TypeNumber);
  public static final int _TypeString = 1;
  public static final edu.stanford.atom.sti.client.comm.corba.TType TypeString = new edu.stanford.atom.sti.client.comm.corba.TType(_TypeString);
  public static final int _TypeChannel = 2;
  public static final edu.stanford.atom.sti.client.comm.corba.TType TypeChannel = new edu.stanford.atom.sti.client.comm.corba.TType(_TypeChannel);
  public static final int _TypeObject = 3;
  public static final edu.stanford.atom.sti.client.comm.corba.TType TypeObject = new edu.stanford.atom.sti.client.comm.corba.TType(_TypeObject);

  public int value ()
  {
    return __value;
  }

  public static edu.stanford.atom.sti.client.comm.corba.TType from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  protected TType (int value)
  {
    __value = value;
    __array[__value] = this;
  }
} // class TType
