package edu.stanford.atom.sti.client.comm.corba;


/**
* Timing_Server/TChannelSeqHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from c:/code/Timing_Server/timingserver.idl
* Tuesday, April 29, 2008 9:27:00 PM PDT
*/

abstract public class TChannelSeqHelper
{
  private static String  _id = "IDL:Timing_Server/TChannelSeq:1.0";

  public static void insert (org.omg.CORBA.Any a, edu.stanford.atom.sti.client.comm.corba.TChannel[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static edu.stanford.atom.sti.client.comm.corba.TChannel[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = edu.stanford.atom.sti.client.comm.corba.TChannelHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (edu.stanford.atom.sti.client.comm.corba.TChannelSeqHelper.id (), "TChannelSeq", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static edu.stanford.atom.sti.client.comm.corba.TChannel[] read (org.omg.CORBA.portable.InputStream istream)
  {
        edu.stanford.atom.sti.client.comm.corba.TChannel value[] = null;
    int _len0 = istream.read_long ();
    value = new edu.stanford.atom.sti.client.comm.corba.TChannel[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = edu.stanford.atom.sti.client.comm.corba.TChannelHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, edu.stanford.atom.sti.client.comm.corba.TChannel[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
        edu.stanford.atom.sti.client.comm.corba.TChannelHelper.write (ostream, value[_i0]);
  }

}
