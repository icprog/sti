package edu.stanford.atom.sti.client.comm.corba;


/**
* Timing_Server/ParserOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from c:/code/Timing_Server/timingserver.idl
* Tuesday, April 29, 2008 9:27:00 PM PDT
*/

public interface ParserOperations 
{

  //Set this before parsing
    edu.stanford.atom.sti.client.comm.corba.TOverwritten[] overwritten ();

  //Set this before parsing
  void overwritten (edu.stanford.atom.sti.client.comm.corba.TOverwritten[] newOverwritten);
  boolean lockOnParse ();
  void lockOnParse (boolean newLockOnParse);

  //Both return true on error
  boolean parseFile (String filename);
  boolean parseString (String code);

  //Harvest the parsing result here:
  String outMsg ();
  String errMsg ();
  String mainFile ();
    edu.stanford.atom.sti.client.comm.corba.TChannel[] channels ();
  String[] files ();
    edu.stanford.atom.sti.client.comm.corba.TVariable[] variables ();
    edu.stanford.atom.sti.client.comm.corba.TEvent[] events ();
} // interface ParserOperations
