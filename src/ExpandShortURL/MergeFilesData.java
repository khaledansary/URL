/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExpandShortURL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author khaledd
 */
public class MergeFilesData {
    
    public void mergeFiles(String inputFile, String outputFile)
    {
        BufferedReader br=null;
        PrintWriter writer=null;
        String line =
                "";
       
	try {
          
                br = new BufferedReader(new FileReader(inputFile));
                writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile, true)));
                int count=0;
                while ((line = br.readLine()) != null) {
                    count++;
                    writer.println(line);
                }
                
                writer.close();
                System.out.println("Write to file-->" + outputFile +"Total File: "+count); 
            }catch(Exception e){
                System.out.println(e);
            }
            System.out.println("Done");
    }
}
