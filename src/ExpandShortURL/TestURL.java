/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExpandShortURL;

import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

/**
 *
 * @author khaledd
 */
public class TestURL {
   
    public static void main(String[] args) {
        String url="http://bit.ly/MOL1X3";
        
         
        System.out.println(expandUrl(url));
    }
    public static String expandUrl(String shortenedUrl)  {
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
    
}
