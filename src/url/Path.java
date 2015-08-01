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
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
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
    public void preSunbrust(String inputFile,String outputFile)
    {
        BufferedReader br=null;
        PrintWriter domainwriter=null,pathwriter=null;
        String line = "";
        
        Map<String,Integer> map = new HashMap<>();
        List<Sunbrust> sunbrustList = new ArrayList<Sunbrust>();
	try {
                
                br = new BufferedReader(new FileReader(inputFile));
                domainwriter = new PrintWriter(outputFile, "UTF-8");
                
                int sequence=1;
                while ((line = br.readLine()) != null) {
                  //  System.out.println(line);
                    String countstr[]=line.trim().split(",");
                 //   System.out.println("split(,)"+Arrays.toString(countstr));
                    String nodestr[]=countstr[0].split("#");
                   // System.out.println("split(-)"+Arrays.toString(nodestr));
                    int stage=1;
                    
                    for(int i=0;i<nodestr.length-1;i++)
                    {
                        Sunbrust sunbrust = new Sunbrust();
                        sunbrust.setSequence(sequence);
                        sunbrust.setStage(stage);
               //         System.out.println("node: "+nodestr[i]);
                        sunbrust.setNode(nodestr[i]);
                        sunbrust.setValue(0);
                        sunbrustList.add(sunbrust);
                        stage++;
                    }
                    Sunbrust sunbrust = new Sunbrust();
                    sunbrust.setSequence(sequence);
                    sunbrust.setStage(stage);
                    //System.out.println("node: "+nodestr[nodestr.length-1]);
                    sunbrust.setNode(nodestr[nodestr.length-1]);
                    sunbrust.setValue(Integer.parseInt(countstr[1]));
                    sunbrustList.add(sunbrust);
                    
                    sequence++;
                     //domainwriter.println();
                }
                for(int i =0;i<sunbrustList.size();i++)
                {
                    
                    //System.out.println(sunbrustList.get(i).getSequence()+","+sunbrustList.get(i).getStage()+","+sunbrustList.get(i).getNode()+","+sunbrustList.get(i).getValue());
                    domainwriter.println(sunbrustList.get(i).getSequence()+","+sunbrustList.get(i).getStage()+","+sunbrustList.get(i).getNode()+","+sunbrustList.get(i).getValue());
                }
        
                domainwriter.close();
                
                System.out.println("Write to file-->" + outputFile ); 
            }catch(Exception e){
                System.out.println(e);
            }
            System.out.println("Done");
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
    
    public void getUrlReverseDomain(String inputFile,String domainFile)
    {
        BufferedReader br=null;
        PrintWriter domainwriter=null,pathwriter=null;
        String line = "";
        
        
	try {
     //           PrintWriter writer = new PrintWriter("E:\\Thesis Data\\URLsForThesis\\allUrlwords.text", "UTF-8");
                br = new BufferedReader(new FileReader(inputFile));
                domainwriter = new PrintWriter(domainFile, "UTF-8");
                
                int count=0;
                while ((line = br.readLine()) != null) {
                    
                     String urlpath=reverseDomains(line);
                     if(!urlpath.trim().isEmpty())
                     {
                        domainwriter.println(urlpath);
                        count++;
                       
                     }

                     
                }
                
                domainwriter.close();
       //         writer.close();
                System.out.println("Write to file-->" + domainFile ); 
            }catch(Exception e){
                System.out.println(e);
            }
            System.out.println("Done");
    }
    public String reverseDomains(String str)
    {
        String revDomain="";
        try {
            
            UrlValidator defaultValidator = new UrlValidator(UrlValidator.ALLOW_2_SLASHES);     
            //System.out.println("URL: "+str);
            if(defaultValidator.isValid("http://"+str))
            {
            
                URL host_path = new URL("http://"+str);

                String host=host_path.getHost().toLowerCase();
                host=host.replace(",", "");
                String path=host_path.getPath();
    
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

                        revDomain+="\""+out[j]+"\""+"#";

                    }
                }
                if(revDomain.endsWith("#"))
                {
                    revDomain = revDomain.substring(0,revDomain.length() - 1);
                }
                //System.out.println("Host reverse: "+revDomain);
                if(www)
                {
                    revDomain+="#"+"\""+"www"+"\"";
                    www=false;
                }
                revDomain+=":";
                String path_split[]=path.split("[/]");
                //System.out.println("Path Split: "+ Arrays.toString(path_split));
                for(int i=1;i<path_split.length;i++)
                {

                   revDomain+="\""+path_split[i].replace(",","")+"\""+"#";
                }
             
                if(revDomain.endsWith("#"))
                {
                    revDomain = revDomain.substring(0,revDomain.length() - 1);
                }
                
                
                //System.out.println("URL: "+str);
                //System.out.println("reverse URL: "+revDomain);
            }
            else
            {
                System.out.println("Not valid: "+str);
            }
     
        } catch (MalformedURLException ex) {
            //Logger.getLogger(URLprocess_path.class.getName()).log(Level.SEVERE, null, ex);
        }
        return revDomain;
    }
    
    public void urlHierarchy(String inputFile,String outputFile,int threshold)
    {
        BufferedReader br=null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(inputFile));
            PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
            while((line = br.readLine()) != null){
        
                String hierarchy_path[]=line.split(":"); //"ae"-"infob",88:"XzkCQ7",1 //"ae"-"tabul",3:null,3
                String domain=hierarchy_path[0]; //"ac"-"cultm",231
                String path_freq=hierarchy_path[1];//"VYD9pj",1
                
                String path[]=path_freq.split(","); //[0=>"VYD9pj", 1=1]
                String path_node[]=path[0].split("#");
                List<String> url_hierarchy_list = new ArrayList<String>();
                String url_hierarchy="";
                
                
                for(int i=0;i<path_node.length;i++)
                {
                    
                    url_hierarchy +=path_node[i]+"#";
                                                                           
                    writer.println(domain+":"+url_hierarchy.substring(0,url_hierarchy.length() - 1)+","+path[1]);
                    
                }
                
                
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
    public void pathFrequency(String inputFile,String outputFile,int threshold)
    {
        
        ArrayList<String> d3list = new ArrayList<String>();
        Map<String, Integer> wordMap = getWordCount(inputFile);
        List<Map.Entry<String, Integer>> list = sortByValue(wordMap);
        
        try {
            PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
            for(Map.Entry<String, Integer> entry:list){
                
                if(entry.getValue()>threshold)
                {
                    d3list.add(entry.getKey()+','+entry.getValue());
                   // d3list.add(entry.getKey().replace(".", "-"));
                }
            }
            Collections.sort(d3list,String.CASE_INSENSITIVE_ORDER);            
            
            //Collections.reverse(d3list);
            for(int i=0;i<d3list.size()-1;i++)
            {
                writer.println(d3list.get(i));
            }
            writer.close();
            System.out.println("Write to file-->" + outputFile ); 
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            
        }
        

    }
    public void domainFrequency(String inputFile,String outputFile,int threshold)
    {
        List<String> domain_freq_path_freq = getDomainCount(inputFile,threshold);
        try {
            PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
            for(String path:domain_freq_path_freq)
            {
                writer.println(path);
            }
            writer.close();
            System.out.println("Write to file-->" + outputFile ); 
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            
        }
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
   public  List<String> getDomainCount(String inputFile,int threshold){
 
        BufferedReader br=null;
        String line = "";
        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        List<String> url_path=new ArrayList<String>();
        try {
            br = new BufferedReader(new FileReader(inputFile));
            while((line = br.readLine()) != null){
                String domain_path[]=line.split(":"); //"com"-"twitpic":"6h9bd1"
                
                if(wordMap.containsKey(domain_path[0])){
                    wordMap.put(domain_path[0], wordMap.get(domain_path[0])+1);
                } else {
                    wordMap.put(domain_path[0], 1);
                }
            }
            br.close();
            br = new BufferedReader(new FileReader(inputFile));
            while((line = br.readLine()) != null){
                String domain_path[]=line.split(":"); //"ws"-"gmane":-"QXzCjV",1
                
                    if(wordMap.containsKey(domain_path[0]))
                    {
                       // System.out.println("domain theshold: "+wordMap.get(domain_path[0])+" threshold"+threshold);
                        if(wordMap.get(domain_path[0])>threshold)
                        {
                            
                            
                            if(domain_path.length==1)
                            {
                                url_path.add(domain_path[0]+","+wordMap.get(domain_path[0])+":null");
                                //System.out.println(domain_path[0]+","+wordMap.get(domain_path[0])+":null");
                            }
                            else{
                                url_path.add(domain_path[0]+","+wordMap.get(domain_path[0])+":"+domain_path[1]);
                                //System.out.println(domain_path[0]+","+wordMap.get(domain_path[0])+":"+domain_path[1]);
                            }
                            
                        }
                        
                    }
                	
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{if(br != null) br.close();}catch(Exception ex){}
        }
        return url_path;
   }
   public void cleanPath(String inputFile,String outputFile)
   {
       BufferedReader br=null;
       String line = "";
       try {
            br = new BufferedReader(new FileReader(inputFile));
            PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
            int count=0;
            while((line = br.readLine()) != null){          
                
                line =line.replace("\"", "");
                String nullvalue[]=line.split(":"); //au#com#news,116:business#www,8
                String remove_www="";
                boolean www=false;
                String domain[]=nullvalue[0].split(",");//"ac"#"cultm",237
                if(domain[0].contains("#www")) //"ac"#"cultm"
                {
                    remove_www=domain[0].replace("#www",""); //"ac"#"cultm"
                    www=true;
                }
                else
                {
                    remove_www=domain[0]; //"ac"#"cultm"
                    www=false;
                }
                
                
                String nullstring[]=nullvalue[1].split(","); //"qrTCOj",1
                if(nullstring[0].equals("null"))
                {
                    
                    if(www)
                    {
                        line=remove_www+"#www#end,"+domain[1];
                    }
                    else
                    {
                        line=remove_www+"#end,"+domain[1];
                    }
                }
                else
                {
                    if(www)
                    {
                        line=remove_www+":"+nullstring[0]+"#www#end,"+nullstring[1];
                    }
                    else{
                        line=remove_www+":"+nullstring[0]+"#end,"+nullstring[1];
                    }
                }
                
                line =line.replace(":", "#");
                writer.println(line);
                
            }
            writer.close();
            System.out.println("Write to file-->" + outputFile ); 
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{if(br != null) br.close();}catch(Exception ex){}
        }
       
   }
   public void urlHierarchyFreq(String inputFile,String outputFile,int threshold)
   {
        BufferedReader br=null;
        String line = "";
        ArrayList<String> d3list = new ArrayList<String>();
        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        Map<String, Integer> pathMap = new HashMap<String, Integer>();
        try {
            br = new BufferedReader(new FileReader(inputFile));
            
            int count=0;
            while((line = br.readLine()) != null){          //"at"-"ac"-"univie"-"publizistik",4:"institut"-"aktuelles"-"news-einzelansicht",1
                
                String domain_part[]=line.split(":");       //"at"-"ac"-"univie"-"publizistik",4
                
                String domain_freq=domain_part[0];          //"at"-"ac"-"univie"-"publizistik",4
                
                //String domain_cut[]=domain_freq.split(",");
                //String domain=domain_cut[0];                //"at"-"ac"-"univie"-"publizistik"
                
                
                String path_cut[]=domain_part[1].split(",");         //"institut"-"aktuelles"-"news-einzelansicht",1
                String path= path_cut[0];
                int path_freq= Integer.parseInt(path_cut[1]);
                /*System.out.println("domain part arr "+ Arrays.toString(domain_part));
                System.out.println("domain part "+domain_freq);
                
                System.out.println("path cut"+ Arrays.toString(path_cut));
                System.out.println("path part "+path);*/
                
                
                String key = domain_freq+":"+path;
                
                
                count++;
                if(count>100)
                {
                   // break;
                }
                if(wordMap.containsKey(key)){
                    wordMap.put(key, wordMap.get(key)+1);
                    //System.out.println("key: "+key+" count: "+wordMap.get(key));
                } else {
                    wordMap.put(key, 1);
                    pathMap.put(key, path_freq);
                }
            }
            try {
            PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
            
            List<Map.Entry<String, Integer>> list = sortByValue(wordMap);
            for(Map.Entry<String, Integer> entry:list){
                
                int sum=entry.getValue()+pathMap.get(entry.getKey());
                
                //System.out.println("sum"+sum+" theshold:"+threshold);
                
                if(sum>threshold)
                {
                   // System.out.println("url: "+entry.getKey()+','+sum);
                    d3list.add(entry.getKey()+','+sum);
                   
                }
            }
            Collections.sort(d3list,String.CASE_INSENSITIVE_ORDER);            
            
            //Collections.reverse(d3list);
            for(int i=0;i<d3list.size()-1;i++)
            {
                writer.println(d3list.get(i));
            }
            writer.close();
            System.out.println("Write to file-->" + outputFile ); 
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {

            }
        
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{if(br != null) br.close();}catch(Exception ex){}
        }
       
   }
}
