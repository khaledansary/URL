/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package url;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.validator.routines.UrlValidator;

/**
 *
 * @author khaledd
 */
public class Path {
 
    public void createPathFreq(String inputFile,String domainFile)
    {
        BufferedReader br=null;
        PrintWriter domainwriter=null,pathwriter=null;
        String line = "";
        
	try {
     //           PrintWriter writer = new PrintWriter("E:\\Thesis Data\\URLsForThesis\\allUrlwords.text", "UTF-8");
                br = new BufferedReader(new FileReader(inputFile));
                domainwriter = new PrintWriter(domainFile, "UTF-8");
                
                Map<String, Integer> wordMap = new HashMap<String, Integer>();
                ArrayList<String> d3list = new ArrayList<String>();

                while ((line = br.readLine()) != null) {
                    if(wordMap.containsKey(line)){
                        wordMap.put(line, wordMap.get(line)+1);
                    } else {
                        wordMap.put(line, 1);
                    }   
                }                
                List<Map.Entry<String, Integer>> list = sortByValue(wordMap);
                
                
                for(Map.Entry<String, Integer> entry:list){
                    d3list.add(entry.getKey()+','+entry.getValue());
                
                }
               Collections.sort(d3list,String.CASE_INSENSITIVE_ORDER);  
               
               for(int i=0;i<d3list.size()-1;i++)
               {
                    domainwriter.println(d3list.get(i));
               }
               domainwriter.close();
               System.out.println("Write to file-->" + domainFile ); 
            }catch(Exception e){
                System.out.println(e);
            }
            System.out.println("Done");
    }
    public void createPathReverseFreq(String inputFile,String domainFile){
        BufferedReader br=null;
        PrintWriter domainwriter=null,pathwriter=null;
        String line = "";
        
	try {
                
                br = new BufferedReader(new FileReader(inputFile));
                domainwriter = new PrintWriter(domainFile, "UTF-8");
                
                
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                   
                   
                    domainwriter.println(reversePath(line));

                }
                
                domainwriter.close();
                
                System.out.println("Write to file-->" + domainFile ); 
            }catch(Exception e){
                System.out.println(e);
            }
            System.out.println("Done");
    }
    public String reversePath(String str)
    {
        String revDomain="";
        try {
            
            UrlValidator defaultValidator = new UrlValidator(UrlValidator.ALLOW_2_SLASHES);     
            System.out.println("URL: "+str);
            if(defaultValidator.isValid("http://"+str))
            {
            
                java.net.URL host_path = new java.net.URL("http://"+str);

                String host=host_path.getHost();
                String path=host_path.getPath();
                host=host.replace("-","_");
                path = path.replaceAll("-", "_");
                path = path.replaceAll(",", "");
                path = path.replaceAll("\\.", "");

                String out[]= host.split("\\.");
                boolean www=false;

              //  System.out.println("Host: "+host);
               // System.out.println("Path: "+path);
                for(int j=out.length-1;j>=0;j--)
                {
                    if(out[j].trim().equals("WWW")||out[j].trim().equals("www"))
                    {
                        www=true;
                    }
                    else
                    {

                        revDomain+=out[j]+"-";

                    }
                }
                if(revDomain.endsWith("-"))
                {
                    revDomain = revDomain.substring(0,revDomain.length() - 1);
                }
                //System.out.println("Host reverse: "+revDomain);
                String path_split[]=path.split("[/]");
                //System.out.println("Path Split: "+ Arrays.toString(path_split));
                if(path_split.length>3)
                {
                    for(int i=1;i<path_split.length-1;i++)
                    {

                        revDomain+="-"+path_split[i];
                    }
                }
                else
                {
                    for(int i=1;i<path_split.length;i++)
                    {

                        revDomain+="-"+path_split[i];
                    }
                }

                //revDomain+=path.replaceAll("[/\\\\]", "-");
                if(revDomain.endsWith("-"))
                {
                    revDomain = revDomain.substring(0,revDomain.length() - 1);
                }
                //System.out.println("Full reverse URL: "+revDomain);
                if(www)
                {
                    revDomain+="-www";
                    www=false;
                }
            }
            else
            {
                System.out.println("Not valid: "+str);
            }
     
        } catch (MalformedURLException ex) {
            System.out.println(ex);
        }
        return revDomain;
    }
    public  Map<String, Integer> getWordCount(String inputFile){
 
        BufferedReader br=null;
        String line = "";
        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        try {
            br = new BufferedReader(new FileReader(inputFile));
            while((line = br.readLine()) != null){
                if(wordMap.containsKey(line)){
                    wordMap.put(line, wordMap.get(line)+1);
                } else {
                    wordMap.put(line, 1);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{if(br != null) br.close();}catch(Exception ex){}
        }
        return wordMap;
   }
    public List<Map.Entry<String, Integer>> sortByValue(Map<String, Integer> wordMap){
        Set<Map.Entry<String, Integer>> set = wordMap.entrySet();
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        return list;
    }
    public void finalFrequency(String inputFile,String outputFile)
    {
        BufferedReader br=null;
        String line = "";
        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        try {
            br = new BufferedReader(new FileReader(inputFile));
            while((line = br.readLine()) != null){
                String[] nodeStr=new String[2];
                StringTokenizer st = new StringTokenizer(line,",");
                int k=0;
                while (st.hasMoreTokens()) {
                  nodeStr[k]=st.nextToken();
                  k++;
                }
                if(wordMap.containsKey(nodeStr[0])){
                    wordMap.put(nodeStr[0], wordMap.get(nodeStr[0])+Integer.parseInt(nodeStr[1]));
                } else {
                    wordMap.put(nodeStr[0], Integer.parseInt(nodeStr[1]));
                }
            }
            List<Map.Entry<String, Integer>> list = sortByValue(wordMap);
            ArrayList<String> d3list = new ArrayList<String>();
            PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
            for(Map.Entry<String, Integer> entry:list){
                
                
                    d3list.add(entry.getKey()+','+entry.getValue());
                   // d3list.add(entry.getKey().replace(".", "-"));
                
            }
            //Collections.sort(d3list,String.CASE_INSENSITIVE_ORDER); 
            Comparator<String> x = new Comparator<String>()
            {
                @Override
                public int compare(String o1, String o2)
                {
                    String str1[]=o1.split(",");
                    String str2[]=o2.split(",");
                    if(str1[0].length() > str2[0].length())
                        return -1;

                    if(str2[0].length() > str1[0].length())
                        return 1;

                    return 0;
                }
            };

            Collections.sort(d3list,  x);
            
           
            for(int i=0;i<d3list.size()-1;i++)
            {
                writer.println(d3list.get(i));
            }
            writer.close();
            System.out.println("Write to file-->" + outputFile ); 
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{if(br != null) br.close();}catch(Exception ex){}
        }
    
    }
    

}
