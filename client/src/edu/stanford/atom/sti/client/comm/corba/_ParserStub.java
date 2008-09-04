package edu.stanford.atom.sti.client.comm.corba;


/**
* edu/stanford/atom/sti/client/comm/corba/_ParserStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./client.idl
* Tuesday, August 26, 2008 10:21:42 AM PDT
*/

public class _ParserStub extends org.omg.CORBA.portable.ObjectImpl implements edu.stanford.atom.sti.client.comm.corba.Parser
{


  //Set this before parsing
  public edu.stanford.atom.sti.client.comm.corba.TOverwritten[] overwritten ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("_get_overwritten", true);
                $in = _invoke ($out);
                edu.stanford.atom.sti.client.comm.corba.TOverwritten $result[] = edu.stanford.atom.sti.client.comm.corba.TOverwrittenSeqHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return overwritten (        );
            } finally {
                _releaseReply ($in);
            }
  } // overwritten


  //Set this before parsing
  public void overwritten (edu.stanford.atom.sti.client.comm.corba.TOverwritten[] newOverwritten)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("_set_overwritten", true);
                edu.stanford.atom.sti.client.comm.corba.TOverwrittenSeqHelper.write ($out, newOverwritten);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                overwritten (newOverwritten        );
            } finally {
                _releaseReply ($in);
            }
  } // overwritten

  public boolean lockOnParse ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("_get_lockOnParse", true);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return lockOnParse (        );
            } finally {
                _releaseReply ($in);
            }
  } // lockOnParse

  public void lockOnParse (boolean newLockOnParse)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("_set_lockOnParse", true);
                $out.write_boolean (newLockOnParse);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                lockOnParse (newLockOnParse        );
            } finally {
                _releaseReply ($in);
            }
  } // lockOnParse


  //Both return true on error
  public boolean parseFile (String filename)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("parseFile", true);
                $out.write_string (filename);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return parseFile (filename        );
            } finally {
                _releaseReply ($in);
            }
  } // parseFile

  public boolean parseString (String code)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("parseString", true);
                $out.write_string (code);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return parseString (code        );
            } finally {
                _releaseReply ($in);
            }
  } // parseString


  //Harvest the parsing result here:
  public String outMsg ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("_get_outMsg", true);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return outMsg (        );
            } finally {
                _releaseReply ($in);
            }
  } // outMsg

  public String errMsg ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("_get_errMsg", true);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return errMsg (        );
            } finally {
                _releaseReply ($in);
            }
  } // errMsg

  public String mainFile ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("_get_mainFile", true);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return mainFile (        );
            } finally {
                _releaseReply ($in);
            }
  } // mainFile

  public edu.stanford.atom.sti.client.comm.corba.TChannel[] channels ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("_get_channels", true);
                $in = _invoke ($out);
                edu.stanford.atom.sti.client.comm.corba.TChannel $result[] = edu.stanford.atom.sti.client.comm.corba.TChannelSeqHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return channels (        );
            } finally {
                _releaseReply ($in);
            }
  } // channels

  public String[] files ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("_get_files", true);
                $in = _invoke ($out);
                String $result[] = edu.stanford.atom.sti.client.comm.corba.TStringSeqHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return files (        );
            } finally {
                _releaseReply ($in);
            }
  } // files

  public edu.stanford.atom.sti.client.comm.corba.TVariable[] variables ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("_get_variables", true);
                $in = _invoke ($out);
                edu.stanford.atom.sti.client.comm.corba.TVariable $result[] = edu.stanford.atom.sti.client.comm.corba.TVariableSeqHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return variables (        );
            } finally {
                _releaseReply ($in);
            }
  } // variables

  public edu.stanford.atom.sti.client.comm.corba.TEvent[] events ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("_get_events", true);
                $in = _invoke ($out);
                edu.stanford.atom.sti.client.comm.corba.TEvent $result[] = edu.stanford.atom.sti.client.comm.corba.TEventSeqHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return events (        );
            } finally {
                _releaseReply ($in);
            }
  } // events

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:STI_Client_Server/Parser:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.Object obj = org.omg.CORBA.ORB.init (args, props).string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     String str = org.omg.CORBA.ORB.init (args, props).object_to_string (this);
     s.writeUTF (str);
  }
} // class _ParserStub
