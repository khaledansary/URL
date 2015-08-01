/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExpandShortURL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author khaledd
 */
public class MergeFilesData {
    
    public static void main(String[] args) {
        
     
        MergeFilesData obj= new MergeFilesData();
        
        
        
        String inputFolder1="K:\\URL Analysis\\Data\\URLs_2014\\All_URLs\\All_URLS\\13\\unshorted_final\\";
        //String inputFolder2="K:\\URL Analysis\\Data\\URLs_2014\\All_URLs\\All_URLS\\13\\expanded\\unshorted\\re_expand\\re_unshorted\\";
        String outputFolder="K:\\URL Analysis\\Data\\URLs_2014\\All_URLs\\All_URLS\\All_UnShortedURLs\\";
        
        //obj.mergingFolder(inputFolder1, "",outputFolder);
        
        inputFolder1="K:\\URL Analysis\\Data\\URLs_2010-2013\\All_URLs\\All_URLS\\expanded40001.csv";
        outputFolder="K:\\URL Analysis\\Data\\URLs_2010-2013\\All_URLs\\All_URLS\\sperated_logURL.csv";
        obj.seperateFiles_id_url(inputFolder1, outputFolder);
     
    }
    public void seperateFiles_id_url(String inputFile1, String outputFile)
    {
        BufferedReader br1=null,br2=null;
        PrintWriter writer=null;
        String line =     "";
       
	try {
          
                br1 = new BufferedReader(new FileReader(inputFile1));
              //  br2 = new BufferedReader(new FileReader(inputFile2));
                writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile, true)));
                int count=0;
                while ((line = br1.readLine()) != null) {
                    String str[]=line.split(" ");
                    count++;
                    writer.println(str[1]);
                }
                
                writer.close();
                br1.close();
                System.out.println("Write to file-->" + outputFile +"Total File: "+count); 
            }catch(Exception e){
                System.out.println(e);
            }
            System.out.println("Done");
    }
    public void mergeFiles(String inputFile1,String inputFile2, String outputFile)
    {
        BufferedReader br1=null,br2=null;
        PrintWriter writer=null;
        String line =     "";
       
	try {
          
                br1 = new BufferedReader(new FileReader(inputFile1));
              //  br2 = new BufferedReader(new FileReader(inputFile2));
                writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile, true)));
                int count=0;
                while ((line = br1.readLine()) != null) {
                    String str[]=line.split("\t");
                    count++;
                    writer.println(str[0]+"\t"+str[1]);
                }
                /*while ((line = br2.readLine()) != null) {
                    String str[]=line.split("\t");
                    count++;
                    writer.println(str[0]+"\t"+str[1]);
                }*/
                
                writer.close();
                br1.close();
                System.out.println("Write to file-->" + outputFile +"Total File: "+count); 
            }catch(Exception e){
                System.out.println(e);
            }
            System.out.println("Done");
    }
     public void mergingFolder(String inputFolder1,String inputFolder2,String outputFolder)
    {
        final File folder = new File(inputFolder1);
        
         for (final File fileEntry : folder.listFiles()) {
            
             mergeFiles(inputFolder1+fileEntry.getName(),inputFolder2+fileEntry.getName(), outputFolder+"short_long_mapped_urls");
            //mergeTwoFiles(inputFolder1+fileEntry.getName(),inputFolder2+"unshortedURLs"+fileEntry.getName(), outputFolder+"unshortedURLs"+fileEntry.getName());
            
        }
    }
    public void mergeTwoFiles(String inputFile1,String inputFile2, String outputFile)
    {
        BufferedReader br1=null,br2=null;
        PrintWriter writer=null;
        String line1 ="",line2 ="";
       
	try { 
          
                br1 = new BufferedReader(new FileReader(inputFile1));
                br2 = new BufferedReader(new FileReader(inputFile2));
                writer = new PrintWriter(outputFile,"UTF-8");
                int count=0;
                while ((line1 = br1.readLine()) != null && (line2 = br2.readLine()) != null) {
                    count++;
                   // System.out.println(line1+"\t"+line2);
                    writer.println(line1+"\t"+line2);
                }
                
                writer.close();
                br1.close();
                br2.close();
                System.out.println("Write to file-->" + outputFile +"Total File: "+count); 
            }catch(Exception e){
                System.out.println(e);
            }
            System.out.println("Done");
    }
}
