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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khaledd
 */
public class Domain {
    
    public void countDomainFreq(String inputFile,String domainFile,int threshold)
    {
        BufferedReader br=null;
        PrintWriter domainwriter=null,pathwriter=null;
        String line = "";
        try {

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
                if(entry.getValue()>threshold)
                {
                    d3list.add(entry.getKey()+','+entry.getValue());
                }
            }
            
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
                domainwriter.println(d3list.get(i));
            }
            
            domainwriter.close();

            System.out.println("Write to file-->" + domainFile ); 
            }catch(Exception e){
                System.out.println(e);
            }
            System.out.println("Done");
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
                                       
                    String countstr[]=line.trim().split(",");
                 //   System.out.println("split(,)"+Arrays.toString(countstr));
                    
                    String nodestr[]=countstr[0].split("_");
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
    public void createReverseDomain(String inputFile,String domainFile)
    {
        BufferedReader br=null;
        PrintWriter domainwriter=null,pathwriter=null;
        String line = "";
        
	try {
                //PrintWriter writer = new PrintWriter("E:\\Thesis Data\\URLsForThesis\\allUrlwords.text", "UTF-8");
                br = new BufferedReader(new FileReader(inputFile));
                domainwriter = new PrintWriter(domainFile, "UTF-8");
                
                
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                   
                    String node[]=line.split(",");
                    domainwriter.println(reverseDomains(node[0])+","+node[1]);

                }
                
                domainwriter.close();
                //writer.close();
                System.out.println("Write to file-->" + domainFile ); 
            }catch(Exception e){
                System.out.println(e);
            }
            System.out.println("Done");
    }
    public String reverseDomains(String str)
    {
        String revDomain="";
        //str=str.replace("-","_");
        //str=str.replace(".","");
        String out[]= str.split("\\.");
        boolean www=false;
        for(int j=out.length-1;j>=0;j--)
        {
            if(out[j].trim().equals("WWW")||out[j].trim().equals("www"))
            {
                www=true;
            }
            else
            {

                revDomain+=out[j]+"_";
                
            }
        }
        if(revDomain.endsWith("_"))
        {
            //revDomain = revDomain.substring(0,revDomain.length() - 1);  
        }
        if(www)
        {
            revDomain = revDomain.substring(0,revDomain.length() - 1);
            revDomain+="_www_";
            www=false;
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
}
