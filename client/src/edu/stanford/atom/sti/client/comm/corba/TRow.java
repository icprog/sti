package edu.stanford.atom.sti.client.comm.corba;


/**
* edu/stanford/atom/sti/client/comm/corba/TRow.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./client.idl
* Tuesday, August 26, 2008 10:21:42 AM PDT
*/

public final class TRow implements org.omg.CORBA.portable.IDLEntity
{
  public String val[] = null;
  public boolean done = false;

  public TRow ()
  {
  } // ctor

  public TRow (String[] _val, boolean _done)
  {
    val = _val;
    done = _done;
  } // ctor

} // class TRow
