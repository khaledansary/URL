/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExpandShortURL;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author khaledd
 */
public class URL_Expanding_API {
    private final String USER_AGENT = "Mozilla/5.0";
    public static void main(String[] args) {
        
        String inputFolder="K:\\URL Analysis\\Data\\URLs_2014\\All_URLs\\All_URLS\\All_UnShortedURLs\\shortURLsshort_long_mapped_urls";
        String outputShortFolder="K:\\URL Analysis\\Data\\URLs_2014\\All_URLs\\All_URLS\\All_UnShortedURLs\\re_unshorted_urls";
        URL_Expanding_API obj= new URL_Expanding_API();
        obj.expandURL(inputFolder, outputShortFolder);
        
        
     
    }
    public void expandURL(String inputFile, String outputFile)
    {
        BufferedReader br=null;
        PrintWriter writer=null;
        String line =     "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
	System.out.println(dateFormat.format(cal.getTime()));  
       
	try {
          
                br = new BufferedReader(new FileReader(inputFile));
              
                writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile, true)));
                int count=0,reset_print=0;
                while ((line = br.readLine()) != null) {
                    String str[]=line.split("\t");
                    count++;
                    reset_print++;
                   
                    writer.println(str[0]+"\t"+sendGet(str[0]));
                    
                    if(reset_print>1000)
                    {
                        System.out.println(count);
                        System.out.println(dateFormat.format(cal.getTime()));
                        reset_print=0;
                    }
                   
                }
                
                
                writer.close();
                br.close();
                System.out.println("Write to file-->" + outputFile +"Total File: "+count); 
            }catch(Exception e){
                System.out.println(e);
            }
            System.out.println("Done");
    }
    private String sendGet(String urltext)  {
        String expandUrl="";
        try{
		String url = "http://expandurl.appspot.com/expand?url="+urltext;
 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
                con.setRequestProperty("Accept", "application/json");
                
		BufferedReader br = new BufferedReader(new InputStreamReader(
			(con.getInputStream())));
 
		String output;
		
		while ((output = br.readLine()) != null) {
			//System.out.println(output);
                        expandUrl=parse(output);
                        
                        
		}
 
		con.disconnect();
            }
        catch(Exception e){
            System.out.println(e);
        }
        return expandUrl;
 
    }
     public String parse(String jsonLine) {
        JsonElement jelement = new JsonParser().parse(jsonLine);
        JsonObject  jobject = jelement.getAsJsonObject();
               
        String result = jobject.get("end_url").toString();
        return result;
    }
 

}
