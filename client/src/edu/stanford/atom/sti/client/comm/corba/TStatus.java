package edu.stanford.atom.sti.client.comm.corba;


/**
* Timing_Server/TStatus.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from c:/code/Timing_Server/timingserver.idl
* Tuesday, April 29, 2008 9:27:00 PM PDT
*/

public final class TStatus implements org.omg.CORBA.portable.IDLEntity
{
  public edu.stanford.atom.sti.client.comm.corba.TStatusLevel level = null;
  public double curTime = (double)0;
  public int curEvent = (int)0;
  public short curCycle = (short)0;

  public TStatus ()
  {
  } // ctor

  public TStatus (edu.stanford.atom.sti.client.comm.corba.TStatusLevel _level, double _curTime, int _curEvent, short _curCycle)
  {
    level = _level;
    curTime = _curTime;
    curEvent = _curEvent;
    curCycle = _curCycle;
  } // ctor

} // class TStatus
