/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package URLTree;

import com.google.common.base.Joiner;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import java.awt.BorderLayout;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import url.Path;

/**
 *
 * @author khaledd
 */
public class FindOptimalPath {
    
    public static void main(String[] args)  {
 
       /* String DirLoc="E:\\Thesis Data\\URLsForThesis\\URLv\\Path\\";
        FindOptimalPath obj = new FindOptimalPath();
        List<Node> node;
        node=obj.readFile(DirLoc+"domainFrequenciesPath_final.csv");
	int threshold=50;
        obj.printMap(node,20,DirLoc+"path_"+threshold+".csv",threshold,DirLoc+"freq_"+threshold+".csv",DirLoc+"domain_path_"+threshold+".csv");*/
       /* node=obj.readFile(DirLoc+"domainFrequencies.csv");
	int threshold=5;
        obj.printMap(node,20,DirLoc+"domain_"+threshold+".csv",threshold,DirLoc+"visit-sequences"+threshold+".csv",DirLoc+"domain_d3_"+threshold+".csv");*/
         
     }
    
    public  List<Node> readFile(String inputFile)
    {
        BufferedReader br=null;
        PrintWriter domainwriter=null,pathwriter=null;
        String file1 = "";
        
        List<Node> nodeList = new ArrayList<Node>();
       
	try {
                br = new BufferedReader(new FileReader(inputFile));
        //        pathwriter = new PrintWriter(pathFile, "UTF-8");
                
                while ((file1 = br.readLine()) != null) {
                    
                    //System.out.println(file1);
                    String path[]=file1.split(",");
                    String nodes[]=path[0].split("-");
                    
                    nodeList.add(new Node(nodes,Integer.parseInt(path[1].trim())));
                    System.out.println(Arrays.toString(path));
                     
                }
                
                br.close();
            }catch(Exception e){
                System.out.println(e);
            }
            System.out.println("Read Done");
            return nodeList;
    }
    public void printMap(List<Node> node,int stage,String outputFile,int threshold,String ouptputFrequencyFile,String filed3)
    {
        
        Map<String,Integer> countSimilarNode = new HashMap<String, Integer>();
        String nodeMatrix[][] = new String[node.size()][stage];
        for (int i =0;i<node.size();i++) {
            
            
            //System.out.println(Arrays.toString(node.get(i).getNodeArr()));
            String arr[]=node.get(i).getNodeArr();
            
            for(int j=0;j<arr.length;j++)
            {   
                if(j<stage)
                {
                    nodeMatrix[i][j]=arr[j];
                }
            }

            
                        
        }
        List<MergeSimilarNode> similarNode = new ArrayList<MergeSimilarNode>();
        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        
        for(int i=0;i<node.size();i++)
        {
            for(int j=0;j<stage;j++)
            {
                if(nodeMatrix[i][j]!=null)
                {
                     
                    FreqWords nodeFreq= new FreqWords();
                    System.out.print("["+i+j+"]: "+nodeMatrix[i][j]);
                    if(wordMap.containsKey(nodeMatrix[i][j]+","+j)){
                        wordMap.put(nodeMatrix[i][j]+","+j, wordMap.get(nodeMatrix[i][j]+","+j)+1);
                    } else {
                        
                        wordMap.put(nodeMatrix[i][j]+","+j, 1);
                    }
                    
                }
            }
            System.out.println();
        }
        List<FreqWords> freq= new ArrayList<FreqWords>();
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            
            FreqWords fwords= new FreqWords();
            String key = entry.getKey();
            Integer value = entry.getValue();
            //String nodeStr[]=key.split(",");
            String[] nodeStr=new String[2];
            StringTokenizer st = new StringTokenizer(key,",");
            int k=0;
            while (st.hasMoreTokens()) {
              nodeStr[k]=st.nextToken();
              k++;
            }
            fwords.setNode(nodeStr[0]);
            System.out.println("node freq: "+nodeStr[1]);
            if(nodeStr[1]!=null)
            {
                fwords.setFreq(Integer.parseInt(nodeStr[1]));
            }
            else
            {
                System.out.println("null value at"+nodeStr[0]+ ": "+nodeStr[1]);
            }
            
            fwords.setValue(value);
            freq.add(fwords);
            
            
        }
        System.out.println("Done Matrix");
        //System.out.println();
        PrintWriter writer=null;
        List<String> urlList=new ArrayList<String>();
        List<FilterURL> varifiedList= new ArrayList<FilterURL>();
        try {
            
            writer = new PrintWriter(outputFile, "UTF-8");
            
            for(int i=0;i<node.size();i++)
            {
                String urlArr[]=new String[stage];
                
                int flag=0;
                int sum=0;
                for(int j=0;j<stage;j++)
                {
                    if(nodeMatrix[i][j]!=null)
                    {
                        for(FreqWords words:freq)
                        {
                            //System.out.println();
                            //if(words.getNode().equals("ca"))
                           // System.out.println("words: "+words.getNode()+" i: "+i+"j: "+j+"  value: "+nodeMatrix[i][j]+" word Freq: "+ words.getFreq());
                            if(words.getNode().equals(nodeMatrix[i][j]))
                            {
                                //if(words.getFreq()!=0)
                                //{
                                    if(words.getFreq()==j )
                                    {
                                        int count=node.get(i).getCount();
                                            //url=url.append(nodeMatrix[i][j]).append("(").append(value+node.get(i).getCount()).append(")-");
                                       

                                        if(threshold>words.getValue())
                                        {
                                           
                                            flag=1;
                                            break;
                                        }
                                        else
                                        {
                                            sum+=count;
                                            urlArr[j]=nodeMatrix[i][j];
                                           // System.out.println("i: "+i+"j: "+j+" found: "+urlArr[j]);

                                            break;

                                            //System.out.print(nodeMatrix[i][j]+"("+value+")");
                                        }
                                    }
                                //}
                                
                            }
                            

                        }
                        
                        

                    }
                   if(flag==1)
                    {
                        flag=0;
                        break;
                    }
                   
                }
                
                //String urldata=StringUtils.join(urlArr,"-");                
                String urldata=Joiner.on("-").skipNulls().join(urlArr).trim();
                //System.out.println("Mearge URL"+urldata);
                               
                if(urldata.endsWith("-"))
                {
                    urldata = urldata.substring(0,urldata.length() - 1);
                }
                urldata=StringUtils.stripEnd(urldata, null);
               // System.out.print(urldata);
                if(!urldata.isEmpty())
                {
                    if(urldata.contains("-"))
                    {
                       // writer.println(urldata+","+sum);
                        
                        FilterURL filter = new FilterURL();
                        filter.setReversedURL(urldata);
                        filter.setCount(sum);
                        varifiedList.add(filter);
                    }
                }
                
                //System.out.println(i);
                // System.out.println(i);
               
            }
            //varifiedSequece(varifiedList,writer);
            for(int i=0;i<varifiedList.size()-1;i++)
            {
                if(varifiedList.get(i).getReversedURL().contains(varifiedList.get(i+1).getReversedURL()))
                {

                }
                else
                {
                    if(threshold!=0)
                    {
                        String nodesName[]=varifiedList.get(i).getReversedURL().split("-");
                        if(nodesName.length==2)
                        {
                            try{
                                String url2Domain="http://"+nodesName[1]+"."+nodesName[0];
                                URL url = new URL(url2Domain);    
                                // open connection
                                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY); 

                                // stop following browser redirect
                                httpURLConnection.setInstanceFollowRedirects(false);
                                httpURLConnection.setConnectTimeout(15000);
                                httpURLConnection.setReadTimeout(15000);
                                // extract location header containing the actual destination URL
                                String expandedURL = httpURLConnection.getHeaderField("Location");
                                httpURLConnection.disconnect();
                                if(expandedURL!=null)
                                {
                                    System.out.println("Correct: "+expandedURL);
                                    writer.println(varifiedList.get(i).getReversedURL()+","+varifiedList.get(i).getCount());
                                }


                             }catch(Exception e)
                             {
                                 System.out.println("Incorrect: "+e);
                             }
                        }
                        else{
                            writer.println(varifiedList.get(i).getReversedURL()+","+varifiedList.get(i).getCount());
                        }
                    }
                    else
                    {
                        writer.println(varifiedList.get(i).getReversedURL()+","+varifiedList.get(i).getCount());
                    }

                }

            }
            
            
        }catch(Exception e){
                e.printStackTrace();
                System.out.println(e);
        }finally {
            if (writer != null) {
               writer.close(); // **** closing it flushes it and reclaims resources ****
            }
         }
        System.out.println("Write into File->"+outputFile);    
        
        Path obj = new Path();
        obj.finalFrequency(outputFile,ouptputFrequencyFile);
       // obj.preSunbrust(ouptputFrequencyFile,filed3);
               
    }
    
    public void varifiedSequece(List<FilterURL> varifiedList,PrintWriter writer)
    {
        for(int i=0;i<varifiedList.size()-1;i++)
        {
            if(varifiedList.get(i).getReversedURL().contains(varifiedList.get(i+1).getReversedURL()))
            {
                
            }
            else
            {
                String nodesName[]=varifiedList.get(i).getReversedURL().split("-");
                if(nodesName.length==2)
                {
                    try{
                        String url2Domain="http://"+nodesName[1]+"."+nodesName[0];
                        URL url = new URL(url2Domain);    
                        // open connection
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY); 

                        // stop following browser redirect
                        httpURLConnection.setInstanceFollowRedirects(false);
                        httpURLConnection.setConnectTimeout(15000);
                        httpURLConnection.setReadTimeout(15000);
                        // extract location header containing the actual destination URL
                        String expandedURL = httpURLConnection.getHeaderField("Location");
                        httpURLConnection.disconnect();
                        if(expandedURL!=null)
                        {
                            System.out.println("Correct: "+expandedURL);
                            writer.println(varifiedList.get(i).getReversedURL()+","+varifiedList.get(i).getCount());
                        }


                     }catch(Exception e)
                     {
                         System.out.println("Incorrect: "+e);
                     }
                }
                else{
                    writer.println(varifiedList.get(i).getReversedURL()+","+varifiedList.get(i).getCount());
                }
                       
            }
            
        }
    }
}
