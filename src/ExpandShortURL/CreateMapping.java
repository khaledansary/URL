/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExpandShortURL;

import static ExpandShortURL.TestURL.expandUrl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import url.ThreadClass;

/**
 *
 * @author khaledd
 */
public class CreateMapping {
    
    public static void main(String[] args) {
        
     
        CreateMapping obj= new CreateMapping();
        
        String inputFolder="K:\\URL Analysis\\Data\\URLs_2014\\All_URLs\\All_URLS\\12\\expanded\\unshorted\\";
        String outputFolder="K:\\URL Analysis\\Data\\URLs_2014\\All_URLs\\All_URLS\\12\\expanded\\unshorted\\unshorted\\";
        obj.mappingFolder(inputFolder, outputFolder);
     
    }
    public void mappingFolder(String inputFolder,String outputFolder)
    {
        final File folder = new File(inputFolder);
        
        
        
        
         for (final File fileEntry : folder.listFiles()) {
            
            mappingShortLongURL(inputFolder+fileEntry.getName(), outputFolder+fileEntry.getName());
            
        }
    }
    public void mappingShortLongURL(String inputFile, String mappingFile)
    {
        BufferedReader br=null;
        PrintWriter writer=null;
	String line = "";
        try{
            br = new BufferedReader(new FileReader(inputFile));
            writer = new PrintWriter(mappingFile,"UTF-8");
            while ((line = br.readLine()) != null) {
               String str[]=line.split("\t");
               
               writer.println(str[0]+"\t"+str[3]+"\t"+str[4]+"\t"+str[5]);
               
            }
            writer.close();
        }catch(Exception e)
        {
            System.out.println(e);
        }
     
    }
}
