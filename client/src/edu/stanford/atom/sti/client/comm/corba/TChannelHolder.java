package edu.stanford.atom.sti.client.comm.corba;

/**
* edu/stanford/atom/sti/client/comm/corba/TChannelHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./client.idl
* Tuesday, August 26, 2008 10:21:41 AM PDT
*/

public final class TChannelHolder implements org.omg.CORBA.portable.Streamable
{
  public edu.stanford.atom.sti.client.comm.corba.TChannel value = null;

  public TChannelHolder ()
  {
  }

  public TChannelHolder (edu.stanford.atom.sti.client.comm.corba.TChannel initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = edu.stanford.atom.sti.client.comm.corba.TChannelHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    edu.stanford.atom.sti.client.comm.corba.TChannelHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return edu.stanford.atom.sti.client.comm.corba.TChannelHelper.type ();
  }

}
