/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package url;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import org.apache.commons.validator.routines.UrlValidator;

/**
 *
 * @author khaledd
 */
public class DomainPathFile {
    
    public void createDomainPathFile(String inputFile,String domainFile,String domainPathFile)
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
                        java.net.URL host_path = new java.net.URL(line);
                        String host=host_path.getHost();
                        String path=host_path.getPath();
                        domainwriter.println(host);
                        pathwriter.println(host+path);
                    }        

                }
                
                domainwriter.close();
                pathwriter.close();
                System.out.println("Write to file-->" + domainFile +" File: "+domainPathFile); 
            }catch(Exception e){
                System.out.println(e);
            }
            System.out.println("Done");
    }
}
