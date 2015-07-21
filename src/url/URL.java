/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package url;

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
        // TODO code application logic here
        String inputFolder="E:\\Thesis Data\\URLProcessing\\";
        String domainFolder="K:\\URL Analysis\\Data\\URLs_2010-2013\\All_URLs\\Domain\\";
        String pathFolder="K:\\URL Analysis\\Data\\URLs_2010-2013\\All_URLs\\Path\\";
        
        String allUrls="allUrls.csv";
        String domainUrls="domainUrl.csv";
        String pathUrls="pathUrl.csv";
        
        /*seperate domain and path file from URL*/
       // DomainPathFile createUrlFiles= new DomainPathFile();
       // createUrlFiles.createDomainPathFile(inputFolder+allUrls, domainFolder+domainUrls, pathFolder+pathUrls);
        
        /*create domain frequencty file*/
        /*String domainFreq="DomainFreq.csv";
        Domain domain = new Domain();
        domain.countDomainFreq(domainFolder+domainUrls,domainFolder+domainFreq,1);
        
        String domainD3File="DomainD3Freq.csv";
        domain.createReverseDomain(domainFolder+domainFreq, domainFreq+domainD3File);*/
        
        
        /*create path reverse file and count frequency for D3 visualization*/
        Path path =new Path();
        String pathReverse="PathFreq.csv";
        //path.createPathReverseFreq(pathFolder+pathUrls,pathFolder+pathReverse);
        
        
        String pathFreq="pathD3Freq.csv";
        
       // path.createPathFreq(pathFolder+pathReverse, pathFolder+pathFreq);
        
        
        
        FindOptimalPath obj = new FindOptimalPath();
        List<Node> node;
        
        node=obj.readFile(pathFolder+pathFreq);
        int threshold=250;
        pathFolder=pathFolder+"\\thresholds\\";
        obj.printMap(node,20,pathFolder+"path_"+threshold+".csv",threshold,pathFolder+"freq_"+threshold+".csv",pathFolder+"domain_path_"+threshold+".csv");
        
        
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
                domain.countDomainFreq(outputfolder+"domain_urls.csv",outputfolder+"domain_freq.csv",0);
                domain.createReverseDomain(outputfolder+"domain_freq.csv", outputfolder+"output_domain.csv");
        
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
            if(category=="domain")
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
            if(category=="domain")
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
    public void domainURL()
    {
        
    }
    public void pathURL()
    {
        
    }
    
    
    
}
