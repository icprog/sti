package edu.stanford.atom.sti.client.comm.corba;


/**
* Timing_Server/TEventSeqHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from c:/code/Timing_Server/timingserver.idl
* Tuesday, April 29, 2008 9:27:00 PM PDT
*/

public final class TEventSeqHolder implements org.omg.CORBA.portable.Streamable
{
  public edu.stanford.atom.sti.client.comm.corba.TEvent value[] = null;

  public TEventSeqHolder ()
  {
  }

  public TEventSeqHolder (edu.stanford.atom.sti.client.comm.corba.TEvent[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = edu.stanford.atom.sti.client.comm.corba.TEventSeqHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
        edu.stanford.atom.sti.client.comm.corba.TEventSeqHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return edu.stanford.atom.sti.client.comm.corba.TEventSeqHelper.type ();
  }

}
