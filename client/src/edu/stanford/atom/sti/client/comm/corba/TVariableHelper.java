package edu.stanford.atom.sti.client.comm.corba;


/**
* edu/stanford/atom/sti/client/comm/corba/TVariableHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./client.idl
* Tuesday, August 26, 2008 10:21:41 AM PDT
*/

abstract public class TVariableHelper
{
  private static String  _id = "IDL:STI_Client_Server/TVariable/TVariable:1.0";

  public static void insert (org.omg.CORBA.Any a, edu.stanford.atom.sti.client.comm.corba.TVariable that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static edu.stanford.atom.sti.client.comm.corba.TVariable extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [3];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "name",
            _tcOf_members0,
            null);
          _tcOf_members0 = edu.stanford.atom.sti.client.comm.corba.TVarMixedHelper.type ();
          _members0[1] = new org.omg.CORBA.StructMember (
            "value",
            _tcOf_members0,
            null);
          _tcOf_members0 = edu.stanford.atom.sti.client.comm.corba.TPositionHelper.type ();
          _members0[2] = new org.omg.CORBA.StructMember (
            "pos",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (edu.stanford.atom.sti.client.comm.corba.TVariableHelper.id (), "TVariable", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static edu.stanford.atom.sti.client.comm.corba.TVariable read (org.omg.CORBA.portable.InputStream istream)
  {
    edu.stanford.atom.sti.client.comm.corba.TVariable value = new edu.stanford.atom.sti.client.comm.corba.TVariable ();
    value.name = istream.read_string ();
    value.value = edu.stanford.atom.sti.client.comm.corba.TVarMixedHelper.read (istream);
    value.pos = edu.stanford.atom.sti.client.comm.corba.TPositionHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, edu.stanford.atom.sti.client.comm.corba.TVariable value)
  {
    ostream.write_string (value.name);
    edu.stanford.atom.sti.client.comm.corba.TVarMixedHelper.write (ostream, value.value);
    edu.stanford.atom.sti.client.comm.corba.TPositionHelper.write (ostream, value.pos);
  }

}
