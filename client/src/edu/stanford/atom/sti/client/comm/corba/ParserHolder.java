package edu.stanford.atom.sti.client.comm.corba;

/**
* edu/stanford/atom/sti/client/comm/corba/ParserHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./client.idl
* Tuesday, August 26, 2008 10:21:42 AM PDT
*/

public final class ParserHolder implements org.omg.CORBA.portable.Streamable
{
  public edu.stanford.atom.sti.client.comm.corba.Parser value = null;

  public ParserHolder ()
  {
  }

  public ParserHolder (edu.stanford.atom.sti.client.comm.corba.Parser initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = edu.stanford.atom.sti.client.comm.corba.ParserHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    edu.stanford.atom.sti.client.comm.corba.ParserHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return edu.stanford.atom.sti.client.comm.corba.ParserHelper.type ();
  }

}
