package edu.stanford.atom.sti.client.comm.corba;


/**
* Timing_Server/ControlOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from c:/code/Timing_Server/timingserver.idl
* Tuesday, April 29, 2008 9:27:01 PM PDT
*/

public interface ControlOperations 
{

  //Is it valid to call methods on the readonly expSeq ???
    edu.stanford.atom.sti.client.comm.corba.ExpSequence expSeq ();
  String errMsg ();
    edu.stanford.atom.sti.client.comm.corba.TStatus status ();
  void reset ();
  void setDirect ();

  //Dataformat unknown!
  void runSingle ();

  //Uses Parser.overwritten as it currently is.
  void runSequence ();

  //Sets Parser.overwritten from Control.expSeq
  void _continue ();

  //Jumps to next entry in expSeq
  void stop ();
} // interface ControlOperations
