/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package url;

import ExpandShortURL.ExanpandingShortURLs;
import ExpandShortURL.SeperateShortLongURLs;

/**
 *
 * @author khaledd
 */
public class ThreadshortURLExpand extends Thread{
   private Thread t;
   private String threadName;
   ExanpandingShortURLs  PD;
   private String inputfile;
   private String mappedfile;
   private String expandedfile;
   
   
   ThreadshortURLExpand( String name,  ExanpandingShortURLs pd,String inputFile,String outExpandedFile,String mappedFile){
       threadName = name;
       PD = pd;
       inputfile=inputFile;
       mappedfile=mappedFile;
       expandedfile=outExpandedFile;
       
   }
   public void run() {
     
        PD.exapandShortURLs(inputfile,expandedfile,mappedfile);
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
