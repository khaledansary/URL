/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package url;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.URL;
import org.apache.commons.validator.routines.UrlValidator;

/**
 *
 * @author khaledd
 */
public class DomainPathSeperation {
    
    public static void main(String[] args) {
        DomainPathSeperation obj=new DomainPathSeperation();
        String all_URL="K:\\URL Analysis\\Data\\URLs_2010-2013\\All_URLs\\All_URLS\\allUrls.csv";
        String domain_URL="K:\\URL Analysis\\Data\\URLs_2010-2013\\All_URLs\\Domain\\domain_URLs.csv";
        String path_URL="K:\\URL Analysis\\Data\\URLs_2010-2013\\All_URLs\\Path\\path_URLs.csv";
        
        
        obj.getUrlDomainPath(all_URL,domain_URL, path_URL);
        
    }
    
    public void getUrlDomainPath(String inputFile,String domainFile,String domainPathFile)
    {
        BufferedReader br=null;
        PrintWriter domainwriter=null,pathwriter=null;
        String line = "";
        
        UrlValidator defaultValidator = new UrlValidator(UrlValidator.ALLOW_2_SLASHES); 
	try {
          
                br = new BufferedReader(new FileReader(inputFile));
                domainwriter = new PrintWriter(domainFile, "UTF-8");
                pathwriter = new PrintWriter(domainPathFile, "UTF-8");
                
                while ((line = br.readLine()) != null) {
                    
                    if(defaultValidator.isValid(line))
                    {
                        URL host_path = new URL(line);
                        String host=host_path.getHost();
                        String path=host_path.getPath();
                        domainwriter.println(host);
                        pathwriter.println(host+path);
                    }        

                }
                
                domainwriter.close();
                pathwriter.close();
                System.out.println("Write to file-->" + domainFile +"domain+path File: "+domainPathFile); 
            }catch(Exception e){
                System.out.println(e);
            }
            System.out.println("Done");
    }
    
}
