/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package url;

import ExpandShortURL.SeperateShortLongURLs;

/**
 *
 * @author khaledd
 */
public class ThreadClass extends Thread{
   private Thread t;
   private String threadName;
   SeperateShortLongURLs  PD;
   String inputfile;
   String outLongfile;
   String outShortfile;

   ThreadClass( String name,  SeperateShortLongURLs pd,String inputFile,String outLongFile,String outShortFile){
       threadName = name;
       PD = pd;
       inputfile=inputFile;
       outLongfile=outLongFile;
       outShortfile=outShortFile;
   }
   public void run() {
     
        PD.seperateURLs(inputfile,outLongfile,outShortfile);
        System.out.println("Thread " +  threadName + " exiting.");
   }

   public void start ()
   {
      System.out.println("Starting " +  threadName );
      if (t == null)
      {
         t = new Thread (this, threadName);
         t.start ();
      }
   }
}
