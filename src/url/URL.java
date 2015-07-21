/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package url;

import ExpandShortURL.ExanpandingShortURLs;
import ExpandShortURL.MergeFilesData;
import ExpandShortURL.SeperateShortLongURLs;
import URLTree.FindOptimalPath;
import URLTree.Node;
import java.util.List;

/**
 *
 * @author khaledd
 */
public class URL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        URL obj = new URL();
        
        obj.defineDataSet(1,"domain",0);
        
        
    }
    public void mergeFiles(String inputFile,String outputFile)
    {
        MergeFilesData merge = new MergeFilesData();
        merge.mergeFiles(inputFile, outputFile);
    }
    public void urlSeperation(String inputFile,String longURL,String shortURL)
    {
        SeperateShortLongURLs sepURL= new SeperateShortLongURLs();
        sepURL.seperateURLs(inputFile, shortURL, longURL);
    }
    public void urlExpand(String inputFile,String outputFile,int start,int end)
    {
        ExanpandingShortURLs expURL = new ExanpandingShortURLs();
        expURL.exapandShortURLs(inputFile, outputFile, start, end);
        
    }
    public void defineDataSet(int selectData, String category,int threshold){
        
        String Dir="K:\\URL Analysis\\Data\\";
        if(selectData==1)
        {
            String data2013=Dir+"URLs_2010-2013\\";
            if(category=="domain")
            {
                String outputfolder=data2013+"All_URLs\\Domain\\";
                Domain domain = new Domain();
                domain.countDomainFreq(outputfolder+"domain_urls.csv",outputfolder+"domain_freq.csv",15);
                domain.createReverseDomain(outputfolder+"domain_freq.csv", outputfolder+"output_domain.csv");
                domain.preSunbrust(outputfolder+"output_domain.csv",outputfolder+"output.csv");
        
            }
            else if(category=="path")
            {
                String outputfolder=data2013+"All_URLs\\Path\\";
                FindOptimalPath obj = new FindOptimalPath();
                List<Node> node;
        
                node=obj.readFile(outputfolder+"path_urls.csv");
                
                String pathFolder=outputfolder+"\\thresholds\\";
                obj.printMap(node,20,pathFolder+"path_"+threshold+".csv",threshold,pathFolder+"freq_"+threshold+".csv",pathFolder+"ouptut_path_"+threshold+".csv");
                
                
            }
            else if(category=="de")
            {
                
            }
        }
        else if(selectData==2)
        {
            String dataR2014=Dir+"URLs_2014\\";
            if(category=="merge")
            {
                
                String outputfolder=dataR2014+"All_URLs\\All_URLS\\";
                mergeFiles(outputfolder,outputfolder+"allURLs_R2014.csv");
                
            }
            else if(category=="urlseperation")
            {
                
            }
            else if(category=="urlexpand")
            {
                
            }
            else if(category=="domain")
            {
                
            }
            else if(category=="path")
            {
                
            }
            else if(category=="de")
            {
                
            }
            

        }
        else if(selectData==3)
        {
            String dataU2014=Dir+"URLs_Users_2014\\";
            if(category=="merge")
            {
                
            }
            else if(category=="urlseperation")
            {
                
            }
            else if(category=="urlexpand")
            {
                
            }
            else if(category=="domain")
            {
                
            }
            else if(category=="path")
            {
                
            }
            else if(category=="de")
            {
                
            }

        }
        
    }
    
    
    
}
