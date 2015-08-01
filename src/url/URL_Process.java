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
import java.io.File;
import java.util.List;

/**
 *
 * @author khaledd
 */
public class URL_Process {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
        URL_Process obj = new URL_Process();
        //String inputFolder="K:\\URL Analysis\\Data\\URLs_Users_2014\\All_URLs\\All_URLS\\13\\short\\";
        //String outputFolder="K:\\URL Analysis\\Data\\URLs_Users_2014\\All_URLs\\All_URLS\\13\\expanded\\";
        
        /*String inputFolder="E:\\twitter_url_frequency_2014_researcher\\01\\";
        String outputFolder="K:\\URL Analysis\\Data\\URLs_2014_corrected\\All_URLs\\All_URLS\\01\\";
        */
        
        
        
        //String inputFolder="K:\\URL Analysis\\Data\\URLs_Users_2014\\All_URLs\\All_URLS\\13\\short\\";
        //String outputFolder="K:\\URL Analysis\\Data\\URLs_Users_2014\\All_URLs\\All_URLS\\13\\expanded\\";
        
        /*String inputFolder="K:\\URL Analysis\\Data\\URLs_2014\\All_URLs\\All_URLS\\12\\expanded\\unshorted\\re_short\\";
        String outputFolder="K:\\URL Analysis\\Data\\URLs_2014\\All_URLs\\All_URLS\\12\\expanded\\unshorted\\re_expand\\re_unshorted\\";*/
        //obj.seperateLongShortURLs(inputFolder,outputFolder);
        //obj.exandShortURLs(inputFolder,outputFolder);
        
        
        /*String input_data_folder="K:\\URL Analysis\\Data\\URLs_2010-2013\\All_URLs\\Path\\";
        String output_sunbust_folder="K:\\URL Analysis\\Visualization\\URLs_2010-2013\\All_URLs\\Path\\Path_Sunbust_2013\\";
        String output_sunbust_selection_folder="K:\\URL Analysis\\Visualization\\URLs_2010-2013\\All_URLs\\Path\\Sunbust_Selection\\";*/
        
        
        //obj.createSunbustCsv(input_data_folder, output_sunbust_folder, output_sunbust_selection_folder, "path", 0);
        
        ///////Separete Short and RegularURLs
       /* SeperateShortLongURLs sepURL= new SeperateShortLongURLs();
        String input_data_folder="K:\\URL Analysis\\Data\\URLs_2010-2013\\All_URLs\\All_URLS\\";
        String output_data_folder="K:\\URL Analysis\\Data\\URLs_2010-2013\\All_URLs\\All_URLS\\";
        
        
        sepURL.seperateURLs_normal(input_data_folder+"allUrls.csv", output_data_folder+"short.csv", output_data_folder+"long.csv");*/
        
        //Domain
        /*String input_data_folder="K:\\URL Analysis\\Data\\URLs_2010-2013\\All_URLs\\Domain\\";
        String output_sunbust_folder="K:\\URL Analysis\\Visualization\\URLs_2010-2013\\All_URLs\\Domain\\Domain_Sunbust_2013\\";
        String output_sunbust_selection_folder="K:\\URL Analysis\\Visualization\\URLs_2010-2013\\All_URLs\\Domain\\Sunbust_Selection\\";*/
        
        
        String input_data_folder="K:\\URL Analysis\\Data\\URLs_2010-2013\\All_URLs\\Path\\";
        String output_sunbust_folder="K:\\URL Analysis\\Visualization\\URLs_2010-2013\\All_URLs\\Path\\Path_Sunbust_2013\\";
        String output_sunbust_selection_folder="K:\\URL Analysis\\Visualization\\URLs_2010-2013\\All_URLs\\Path\\Sunbust_Selection\\";
        obj.createSunbustCsv(input_data_folder, output_sunbust_folder, output_sunbust_selection_folder, "path", 15);
        
        
        
    }

    
    public void listFilesForFolder(String dir,File folder,String outputFile) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(dir,fileEntry,outputFile);
            } else {
                mergeFiles(dir+fileEntry.getName(),outputFile);
                System.out.println(fileEntry.getName());
            }
        }
    }

    public void mergeFiles(String inputFile,String outputFile)
    {
        
        MergeFilesData merge = new MergeFilesData();
        //merge.mergeFiles(inputFile, outputFile);
    }
    
    public void seperateLongShortURLs(String inputFolder,String outputFolder)
    {
        final File folder = new File(inputFolder);
        
        SeperateShortLongURLs sepURL= new SeperateShortLongURLs();
        
        
         for (final File fileEntry : folder.listFiles()) {
            
            //mergeFiles(inputFolder+fileEntry.getName(),outputFolderoutputFile);
            ThreadClass t1 = new ThreadClass(inputFolder+fileEntry.getName(),sepURL,inputFolder+fileEntry.getName(),outputFolder+"short\\shortURLs"+fileEntry.getName(),outputFolder+"long\\longURLs"+fileEntry.getName());
            t1.start();

            try {
                t1.join();
              //  t2.join();
             } catch( Exception e) {
                System.out.println("Interrupted");
             }
            
            
        }
        

    }
    public void exandShortURLs(String inputFolder,String outputFolder)
    {
        final File folder = new File(inputFolder);
        
        ExanpandingShortURLs expandURL= new ExanpandingShortURLs();
        
        
         for (final File fileEntry : folder.listFiles()) {
            
            //mergeFiles(inputFolder+fileEntry.getName(),outputFolderoutputFile);
           // ThreadshortURLExpand t1 = new ThreadshortURLExpand(inputFolder+fileEntry.getName(),expandURL,inputFolder+fileEntry.getName(),outputFolder+"unshorted\\unshortedURLs"+fileEntry.getName(),outputFolder+"mapped\\mapping_"+fileEntry.getName());
              ThreadshortURLExpand t1 = new ThreadshortURLExpand(inputFolder+fileEntry.getName(),expandURL,inputFolder+fileEntry.getName(),outputFolder+fileEntry.getName(),outputFolder+fileEntry.getName());
            t1.start();

            try {
                t1.join();
              //  t2.join();
             } catch( Exception e) {
                System.out.println("Interrupted");
             }
            
            
        }
        

    }
    public void createSunbustCsv(String input_data_dir,String sunbust_outdir,String sunbust_selection_outdir,String type,int threshold)
    {
        if(type=="domain")
        {
            Domain domain = new Domain();
            
            
            domain.countDomainFreq(input_data_dir+"domain_urls.csv",input_data_dir+"domain_freq"+threshold+".csv",threshold);
            domain.createReverseDomain(input_data_dir+"domain_freq"+threshold+".csv", sunbust_outdir+"output_domain"+threshold+".csv");
            domain.preSunbrust(sunbust_outdir+"output_domain"+threshold+".csv",sunbust_selection_outdir+"output"+threshold+".csv");        
        }
        else if(type=="path")
        {
            Path path = new Path();
            int domain_threshold=0;
            int path_threshold=5;
            
            path.getUrlReverseDomain(input_data_dir+"path_urls.csv",input_data_dir+"path_reverse"+domain_threshold+"_"+path_threshold+".csv");
            path.domainFrequency(input_data_dir+"path_reverse"+domain_threshold+"_"+path_threshold+".csv",input_data_dir+"domain_feq"+domain_threshold+"_"+path_threshold+".csv",domain_threshold);             
            path.pathFrequency(input_data_dir+"domain_feq"+domain_threshold+"_"+path_threshold+".csv",input_data_dir+"path_feq"+domain_threshold+"_"+path_threshold+".csv",0);
            path.urlHierarchy(input_data_dir+"path_feq"+domain_threshold+"_"+path_threshold+".csv", input_data_dir+"path_hierarchy"+domain_threshold+"_"+path_threshold+".csv", 0);
            path.urlHierarchyFreq(input_data_dir+"path_hierarchy"+domain_threshold+"_"+path_threshold+".csv", input_data_dir+"path_hierarchy_feq"+domain_threshold+"_"+path_threshold+".csv", path_threshold);
            path.cleanPath(input_data_dir+"path_hierarchy_feq"+domain_threshold+"_"+path_threshold+".csv", sunbust_outdir+"path_output"+domain_threshold+"_"+path_threshold+".csv");
            path.preSunbrust(sunbust_outdir+"path_output"+domain_threshold+"_"+path_threshold+".csv", sunbust_selection_outdir+"path_sunburst_output"+domain_threshold+"_"+path_threshold+".csv");
           
            /* String DirLoc=input_data_dir;
            FindOptimalPath obj = new FindOptimalPath();
            List<Node> node;
            node=obj.readFile(input_data_dir+"path_feq.csv");
                       
            obj.printMap(node,20,DirLoc+"path_"+threshold+".csv",threshold,sunbust_outdir+"freq_"+threshold+".csv",sunbust_selection_outdir+"domain_path_"+threshold+".csv");
            */
            
            
            /*FindOptimalPath optimalPath = new FindOptimalPath();
            
            List<Node> node;
          
            node=optimalPath.readFile(input_data_dir+"path_feq.csv");
            optimalPath.printMap(node,20,sunbust_outdir+"path_"+threshold+".csv",threshold,sunbust_outdir+"output_sunburst"+threshold+".csv",sunbust_selection_outdir+"output_sunburst_selection"+threshold+".csv");
            */
        }
    }
    
}
/*
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
        }
        else if(selectData==3)
        {
            String dataU2014=Dir+"URLs_Users_2014\\";
            if(category=="merge")
            {
            }
            else if(category=="urlseperation")
            {
                String outputfolder=dataU2014+"All_URLs\\All_URLS\\";
                String inputfolder="E:\\Thesis Data\\twitter_url_frequency_2014_sample\\";
                
                //urlSeperation(inputfolder+inputfile,outputfolder+"longURLs"+inputfile,outputfolder+"shortURLs"+inputfile);
                
                SeperateShortLongURLs sepURL= new SeperateShortLongURLs();
                String inputfile="part-r-00000";
                ThreadClass t1 = new ThreadClass(inputfile,sepURL,inputfolder+inputfile,outputfolder+"shortURLs"+inputfile,outputfolder+"longURLs"+inputfile);
               // ThreadClass t2 = new ThreadClass(inputfile,sepURL,inputfolder+inputfile,outputfolder+"longURLs"+inputfile,outputfolder+"shortURLs"+inputfile);
                
                t1.start();
                
                try {
                    t1.join();
                  //  t2.join();
                 } catch( Exception e) {
                    System.out.println("Interrupted");
                 }
                //sepURL.seperateURLs(inputFile, shortURL, longURL);
                
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
    


*/