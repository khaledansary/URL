/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExpandShortURL;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

/**
 *
 * @author khaledd
 */
public class ExanpandingShortURLs {
    
     public static void main(String[] args) {
        
     
        ExanpandingShortURLs obj= new ExanpandingShortURLs();
        
        String inputFolder="K:\\URL Analysis\\Data\\URLs_2014\\All_URLs\\All_URLS\\All_UnShortedURLs\\shortURLs";
        String outputFolder="K:\\URL Analysis\\Data\\URLs_2014\\All_URLs\\All_URLS\\All_UnShortedURLs\\re_unshortURLs";
        obj.exapandShortURLs(inputFolder, outputFolder,"");
     
    }
    
    public String expandUrl(String shortenedUrl)  {
     String expandedURL="";
     try{
     
        URL url = new URL(shortenedUrl);    
        // open connection
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY); 
        
        // stop following browser redirect
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(15000);
        // extract location header containing the actual destination URL
        expandedURL = httpURLConnection.getHeaderField("Location");
        httpURLConnection.disconnect();
         
        
     }catch(Exception e)
     {
         System.out.println(e);
     }
     return expandedURL;
    }
    public  void exapandShortURLs(String infile,String outfile,String mappedFile) {

        
        BufferedReader br=null;
        PrintWriter writer=null,writerMapped=null;
	String line = "";
	try {
            br = new BufferedReader(new FileReader(infile));
            writer = new PrintWriter(outfile, "UTF-8");
           // writerMapped = new PrintWriter(mappedFile, "UTF-8");
            int i=0;
            int count=0;

            int row=0;
            int flashflag=0;
            while ((line = br.readLine()) != null) {
                count++;
                
                /*if(count>50)
                {
                    break;
                }*/
                

                flashflag++;
                
                if(count>1000)
                {
                    System.out.println("start Row: "+row);
                    count=0;
                }
                row++;
                try
                {
                    String str[]=line.split("\t");
                    String expandedURL="";
                    int loop=1;
                    String temp="";
                    String url=str[0];
                    //System.out.println("shortURL: "+url);
                    while(true)
                    {

                        String validurl=expandUrl(url.trim());

                        if(validurl!=null && !validurl.isEmpty() && validurl.matches("^(https?|ftp)://.*$"))
                        {
                            loop++;
                            
                            

                            if(loop>4){
                                loop=1;
                                break;
                            }
                            if(temp.equals(validurl))
                            {


                                break;
                            }
                            else{
                                expandedURL = validurl;
                                url=expandedURL;
                                temp=url;
                  //              System.out.println("flag: 1"); 
                            }
                            //System.out.println("---" + expandedURL); 

                        }
                        else if(validurl==null || validurl.trim().equals("list") || validurl.isEmpty()){
                            //System.out.println("flag: 2"); 

                            expandedURL=url.trim();
                            break;

                        }
                        else
                        {
                            break;
                        }

                    }
                    

                    //System.out.println(line + "<----------- write ----------->" + expandedURL); 

                    if(!expandedURL.isEmpty())
                    {
                        writer.println(str[0]+"\t"+expandedURL);
                        //writer.println(str[0]+"\t"+expandedURL+"\t"+str[1]+"\t"+str[2]);
                        //writer.println(str[0]+"\t"+expandedURL+"\t"+str[1]+"\t"+str[2]);
                       // writerMapped.println(str[0]+"\t"+expandedURL);

                    }
                }catch(Exception e)
                {
                    System.out.println("err: "+e);
                }

            }
            try {
                        writer.flush();
                        writer.close();
                       // writerMapped.flush();
                        //writerMapped.close();
                } catch (Exception e) {
                        e.printStackTrace();
                }
            System.out.println("Write to file-->" + outfile); 
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
                if(writer!=null)
                {
                    try {
                                writer.flush();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
                }
                if(writerMapped!=null)
                {
                    try {
                          //  writerMapped.flush();
                           // writerMapped.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
                    
                }
	}
 
	System.out.println("Done");
  }
 
    
}
