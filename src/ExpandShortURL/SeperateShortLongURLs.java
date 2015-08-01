/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExpandShortURL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.URL;

/**
 *
 * @author khaledd
 */
public class SeperateShortLongURLs {
    
     static int count_total_urls=0;
     static int count_total_short_urls=0;
     static int count_total_long_urls=0;
    
     public static void main(String[] args) {
        
     
        SeperateShortLongURLs obj= new SeperateShortLongURLs();
        //String arr[]={"01","02","04","05","06","07","08","09","10","11","12","13"};
        
        
       
        
        /*for(int i=0;i<arr.length;i++)
        {
            String inputFolder="E:\\Thesis Data\\twitter_url_frequency_2014_sample\\"+arr[i]+"\\";
            String outputShortFolder="K:\\URL Analysis\\Data\\URLs_Users_2014\\All_URLs\\All_URLS\\"+arr[i]+"\\short\\";
            String outputLongFolder="K:\\URL Analysis\\Data\\URLs_Users_2014\\All_URLs\\All_URLS\\"+arr[i]+"\\long\\";
        
            obj.mappingFolder(inputFolder,outputShortFolder,outputLongFolder);
        }
        System.out.println("total url:"+count_total_urls);
        System.out.println("total short url: "+count_total_short_urls);
        System.out.println("total long url:"+count_total_long_urls);*/
         
        /*String[] domains = {"bit.ly","t.co","goo.gl","lnkd.in","flip.it","ow.ly","wp.me","buff.ly","dlvr.it","nyti.ms",
            "shar.es","wellc.me","0rz.tw","1link.in","2.gp","2big.at","2tu.us","3.ly","307.to","4ms.me","4url.cc","7.ly",
            "a.gg","a.nf","aa.cx","ad.vu","adf.ly","afx.cc","amzn.to","ar.gy","arst.ch","atu.ca","azc.cc","b23.ru","b2l.me",
            "bacn.me","bcool.bz","binged.it","bizj.us","bloat.me","bravo.ly","bsa.ly","chilp.it","chzb.gr","cl.lk","cl.ly","clck.ru",
            "cli.gs","cliccami.info","clickthru.ca","clop.in","conta.cc","cort.as","cot.ag","crks.me","ctvr.us","cutt.us","dai.ly","dfl8.me",
            "disq.us","dld.bz","do.my","dopen.us","fa.by","fav.me","fbshare.me","ff.im","fff.to","fire.to","firsturl.de","flic.kr","flq.us",
            "fly2.ws","fon.gs","freak.to","fuzzy.to","fwd4.me","g.ro.lt","gizmo.do","gl.am","go.usa.gov","gurl.es","hex.io","hmm.ph","href.in",
            "htxt.it","huff.to","hurl.me","hurl.ws","ilix.in","is.gd","its.my","ix.lt","j.mp","kl.am","klck.me","korta.nu","lat.ms","liip.to","linkbun.ch",
            "liurl.cn","ln-s.ru","lnk.gd","lnk.ms","lru.jp","lt.tl","lurl.no","macte.ch","mash.to","merky.de","migre.me","minurl.fr","mke.me","moby.to","mrte.ch",
            "myloc.me","myurl.in","n.pr","nbc.co","nblo.gs","nn.nf","not.my","nsfw.in","nxy.in","o-x.fr","oc1.us","om.ly","omf.gd","onforb.es","orz.se","ping.fm","pli.gs",
            "pnt.me","politi.co","post.ly","pp.gg","profile.to","qte.me","qu.tc","qy.fi","r.im","rb6.me","read.bi","readthis.ca","redir.ec","redirects.ca","retwt.me","ri.ms",
            "rickroll.it","riz.gd","rt.nu","ru.ly","rww.tw","s4c.in","s7y.us","safe.mn","sdut.us","shink.de","short.ie","short.to","shortlinks.co.uk","shout.to","show.my",
            "shrt.fr","shrt.st","slate.me","smsh.me","smurl.name","sn.im","sp2.ro","srs.li","su.pr","surl.co.uk","surl.hu","t.cn","ta.gd","tbd.ly","tcrn.ch","tgr.me",
            "tgr.ph","tiny.cc","tiny.ly","tiny.pl","tinylink.in","tinyuri.ca","tk.","tl.gd","tmi.me","tnw.to","to.","to.ly","togoto.us","totc.us","toysr.us","tpm.ly",
            "tr.im","tra.kz","trunc.it","u.nu","ub0.cc","ulu.lu","updating.me","ur1.ca","url.az","url.co.uk","url.ie","url360.me","url4.eu","urlenco.de","urli.nl","urls.im",
            "urlx.ie","usat.ly","use.my","vb.ly","vgn.am","vl.am","vm.lc","w55.de","wapo.st","wipi.es","x.vu","xrl.in","xrl.us","xurl.es","xurl.jp","y.ahoo.it","ye.pe","yep.it",
            "yhoo.it","youtu.be","z0p.de","zi.ma","zi.mu","zud.me","zurl.ws","zz.gd","zzang.kr","ulu.ly","paper.li","soc.li","macrumo.rs","htn.to","ti.me","bbc.in","fw.to",
            "vrge.co","cnnmon.ie","cir.ca","rol.st","reut.rs","lnc.hr","ars.to","thndr.it","itpp.me","qntm.co","wbur.fm","owl.li","instagr.am","fb.me","nfr.no","twb.io","aka.ms",
            "yj.pn","eqent.me","rww.to","mbist.ro","prsm.tc","msft.it","ustre.am","tnw.co","ubm.io","zite.to","b0ing.me","theatln.tc","troy.hn","redd.it","kck.st","rsa.im","g.co",
            "oreil.ly","eurone.ws","f24.my","fam.ag","tmblr.co","dailym.ai","wef.ch","es.pn","reg.cx","mun.do","pops.ci","pitch.pe","mod.my","prn.to","fancy.to","mrk.to",
            "deck.ly","linkd.in","cbsloc.al","ift.tt","snlg.ht","dnlr.hn","wh.gov","c4a.me","frc.st","planca.st","yfrog.us","imrn.me","ads.tt","qr.ae","ptab.it",
            "v2.dk","xal.no","shz.am","engt.co","geor.gr","nyr.kr","qwapo.es","twky.in","techre.vu","buswk.co"
                    
            }; 
        
        for(int i=0;i<domains.length;i++)
        {
            

              System.out.println("\""+domains[i]+"\",");  
            

        }*/
    }
    public void mappingFolder(String inputFolder,String outputShortFolder,String outputLongFolder)
    {
        final File folder = new File(inputFolder);
        
        
        
        
         for (final File fileEntry : folder.listFiles()) {
            
            seperateURLs(inputFolder+fileEntry.getName(), outputShortFolder+"shortURLs"+fileEntry.getName(),outputLongFolder+"longURLs"+fileEntry.getName());
            
        }
    }
 
    public void seperateURLs(String inputFile,String shortUrlFile, String longURLFile){
        BufferedReader br=null;
        PrintWriter writer=null,longURL=null;
	String urlline = "";
        
	try {
            br = new BufferedReader(new FileReader(inputFile));
            writer = new PrintWriter(shortUrlFile,"UTF-8");
            
            longURL = new PrintWriter(longURLFile,"UTF-8");
            
            String[] domains = {"bit.ly","t.co","goo.gl","lnkd.in","flip.it","ow.ly","wp.me","buff.ly","dlvr.it","nyti.ms",
            "shar.es","wellc.me","0rz.tw","1link.in","2.gp","2big.at","2tu.us","3.ly","307.to","4ms.me","4url.cc","7.ly",
            "a.gg","a.nf","aa.cx","ad.vu","adf.ly","afx.cc","amzn.to","ar.gy","arst.ch","atu.ca","azc.cc","b23.ru","b2l.me",
            "bacn.me","bcool.bz","binged.it","bizj.us","bloat.me","bravo.ly","bsa.ly","chilp.it","chzb.gr","cl.lk","cl.ly","clck.ru",
            "cli.gs","cliccami.info","clickthru.ca","clop.in","conta.cc","cort.as","cot.ag","crks.me","ctvr.us","cutt.us","dai.ly","dfl8.me",
            "disq.us","dld.bz","do.my","dopen.us","fa.by","fav.me","fbshare.me","ff.im","fff.to","fire.to","firsturl.de","flic.kr","flq.us",
            "fly2.ws","fon.gs","freak.to","fuzzy.to","fwd4.me","g.ro.lt","gizmo.do","gl.am","go.usa.gov","gurl.es","hex.io","hmm.ph","href.in",
            "htxt.it","huff.to","hurl.me","hurl.ws","ilix.in","is.gd","its.my","ix.lt","j.mp","kl.am","klck.me","korta.nu","lat.ms","liip.to","linkbun.ch",
            "liurl.cn","ln-s.ru","lnk.gd","lnk.ms","lru.jp","lt.tl","lurl.no","macte.ch","mash.to","merky.de","migre.me","minurl.fr","mke.me","moby.to","mrte.ch",
            "myloc.me","myurl.in","n.pr","nbc.co","nblo.gs","nn.nf","not.my","nsfw.in","nxy.in","o-x.fr","oc1.us","om.ly","omf.gd","onforb.es","orz.se","ping.fm","pli.gs",
            "pnt.me","politi.co","post.ly","pp.gg","profile.to","qte.me","qu.tc","qy.fi","r.im","rb6.me","read.bi","readthis.ca","redir.ec","redirects.ca","retwt.me","ri.ms",
            "rickroll.it","riz.gd","rt.nu","ru.ly","rww.tw","s4c.in","s7y.us","safe.mn","sdut.us","shink.de","short.ie","short.to","shortlinks.co.uk","shout.to","show.my",
            "shrt.fr","shrt.st","slate.me","smsh.me","smurl.name","sn.im","sp2.ro","srs.li","su.pr","surl.co.uk","surl.hu","t.cn","ta.gd","tbd.ly","tcrn.ch","tgr.me",
            "tgr.ph","tiny.cc","tiny.ly","tiny.pl","tinylink.in","tinyuri.ca","tk.","tl.gd","tmi.me","tnw.to","to.","to.ly","togoto.us","totc.us","toysr.us","tpm.ly",
            "tr.im","tra.kz","trunc.it","u.nu","ub0.cc","ulu.lu","updating.me","ur1.ca","url.az","url.co.uk","url.ie","url360.me","url4.eu","urlenco.de","urli.nl","urls.im",
            "urlx.ie","usat.ly","use.my","vb.ly","vgn.am","vl.am","vm.lc","w55.de","wapo.st","wipi.es","x.vu","xrl.in","xrl.us","xurl.es","xurl.jp","y.ahoo.it","ye.pe","yep.it",
            "yhoo.it","youtu.be","z0p.de","zi.ma","zi.mu","zud.me","zurl.ws","zz.gd","zzang.kr","ulu.ly","paper.li","soc.li","macrumo.rs","htn.to","ti.me","bbc.in","fw.to",
            "vrge.co","cnnmon.ie","cir.ca","rol.st","reut.rs","lnc.hr","ars.to","thndr.it","itpp.me","qntm.co","wbur.fm","owl.li","instagr.am","fb.me","nfr.no","twb.io","aka.ms",
            "yj.pn","eqent.me","rww.to","mbist.ro","prsm.tc","msft.it","ustre.am","tnw.co","ubm.io","zite.to","b0ing.me","theatln.tc","troy.hn","redd.it","kck.st","rsa.im","g.co",
            "oreil.ly","eurone.ws","f24.my","fam.ag","tmblr.co","dailym.ai","wef.ch","es.pn","reg.cx","mun.do","pops.ci","pitch.pe","mod.my","prn.to","fancy.to","mrk.to",
            "deck.ly","linkd.in","cbsloc.al","ift.tt","snlg.ht","dnlr.hn","wh.gov","c4a.me","frc.st","planca.st","yfrog.us","imrn.me","ads.tt","qr.ae","ptab.it",
            "v2.dk","xal.no","shz.am","engt.co","geor.gr","nyr.kr","qwapo.es","twky.in","techre.vu","buswk.co"                  
            }; 
            int shortu=0;  
            int longu=0;
            int totalu=0;
            while ((urlline = br.readLine()) != null) {
               String str[]=urlline.split("\t");
               //URL aURL = new URL(str[1]);
               URL aURL = new URL(str[1]);
               String host=aURL.getHost();
               //System.out.println("host = " + aURL.getHost()); 
              // System.out.println("url"+urlline); 
               totalu++;
               count_total_urls++;
               int flag=0;
               for(String domain : domains){
                    if(host.equals(domain)){
                        shortu++;
                        count_total_short_urls++;
                        //writer.println(str[0]+"\t"+str[1]+"\t"+str[2]+"\t"+str[3]);
                        //writer.println(str[0]+"\t"+str[1]+"\t"+str[2]);
                        writer.println(str[0]+"\t"+str[1]);
                        
                         
                       // System.out.println("find shorturl"+str[0]);
                        flag=1;
                        break;
                        
                    }
                   
                }
                if(flag==0)
                {
                    longu++;
                    count_total_long_urls++;
                   // longURL.println(str[0]+"\t"+str[1]+"\t"+str[2]+"\t"+str[3]);
                    //longURL.println(str[0]+"\t"+str[1]+"\t"+str[2]);
                    longURL.println(str[0]+"\t"+str[1]);
                    
                    flag=0;
                }
               
               
            }
            System.out.println("File "+inputFile+" contains");
            System.out.println("total shorturl: "+shortu+" file write into ------>"+shortUrlFile);
            System.out.println("total longurl: "+longu+" file write into ------>"+longURLFile);
            System.out.println("total url: "+totalu);
            
            try {
                   writer.close();
                   longURL.close();
               } catch (Exception e) {
                       e.printStackTrace();
               }

        }catch(Exception e)
        {
                System.out.println(e);
        }
    }
    
    public void seperateURLs_normal(String inputFile,String shortUrlFile, String longURLFile){
        BufferedReader br=null;
        PrintWriter writer=null,longURL=null;
	String urlline = "";
        
	try {
            br = new BufferedReader(new FileReader(inputFile));
            writer = new PrintWriter(shortUrlFile,"UTF-8");
            
            longURL = new PrintWriter(longURLFile,"UTF-8");
            
            String[] domains = {"bit.ly","t.co","goo.gl","lnkd.in","flip.it","ow.ly","wp.me","buff.ly","dlvr.it","nyti.ms",
            "shar.es","wellc.me","0rz.tw","1link.in","2.gp","2big.at","2tu.us","3.ly","307.to","4ms.me","4url.cc","7.ly",
            "a.gg","a.nf","aa.cx","ad.vu","adf.ly","afx.cc","amzn.to","ar.gy","arst.ch","atu.ca","azc.cc","b23.ru","b2l.me",
            "bacn.me","bcool.bz","binged.it","bizj.us","bloat.me","bravo.ly","bsa.ly","chilp.it","chzb.gr","cl.lk","cl.ly","clck.ru",
            "cli.gs","cliccami.info","clickthru.ca","clop.in","conta.cc","cort.as","cot.ag","crks.me","ctvr.us","cutt.us","dai.ly","dfl8.me",
            "disq.us","dld.bz","do.my","dopen.us","fa.by","fav.me","fbshare.me","ff.im","fff.to","fire.to","firsturl.de","flic.kr","flq.us",
            "fly2.ws","fon.gs","freak.to","fuzzy.to","fwd4.me","g.ro.lt","gizmo.do","gl.am","go.usa.gov","gurl.es","hex.io","hmm.ph","href.in",
            "htxt.it","huff.to","hurl.me","hurl.ws","ilix.in","is.gd","its.my","ix.lt","j.mp","kl.am","klck.me","korta.nu","lat.ms","liip.to","linkbun.ch",
            "liurl.cn","ln-s.ru","lnk.gd","lnk.ms","lru.jp","lt.tl","lurl.no","macte.ch","mash.to","merky.de","migre.me","minurl.fr","mke.me","moby.to","mrte.ch",
            "myloc.me","myurl.in","n.pr","nbc.co","nblo.gs","nn.nf","not.my","nsfw.in","nxy.in","o-x.fr","oc1.us","om.ly","omf.gd","onforb.es","orz.se","ping.fm","pli.gs",
            "pnt.me","politi.co","post.ly","pp.gg","profile.to","qte.me","qu.tc","qy.fi","r.im","rb6.me","read.bi","readthis.ca","redir.ec","redirects.ca","retwt.me","ri.ms",
            "rickroll.it","riz.gd","rt.nu","ru.ly","rww.tw","s4c.in","s7y.us","safe.mn","sdut.us","shink.de","short.ie","short.to","shortlinks.co.uk","shout.to","show.my",
            "shrt.fr","shrt.st","slate.me","smsh.me","smurl.name","sn.im","sp2.ro","srs.li","su.pr","surl.co.uk","surl.hu","t.cn","ta.gd","tbd.ly","tcrn.ch","tgr.me",
            "tgr.ph","tiny.cc","tiny.ly","tiny.pl","tinylink.in","tinyuri.ca","tk.","tl.gd","tmi.me","tnw.to","to.","to.ly","togoto.us","totc.us","toysr.us","tpm.ly",
            "tr.im","tra.kz","trunc.it","u.nu","ub0.cc","ulu.lu","updating.me","ur1.ca","url.az","url.co.uk","url.ie","url360.me","url4.eu","urlenco.de","urli.nl","urls.im",
            "urlx.ie","usat.ly","use.my","vb.ly","vgn.am","vl.am","vm.lc","w55.de","wapo.st","wipi.es","x.vu","xrl.in","xrl.us","xurl.es","xurl.jp","y.ahoo.it","ye.pe","yep.it",
            "yhoo.it","youtu.be","z0p.de","zi.ma","zi.mu","zud.me","zurl.ws","zz.gd","zzang.kr","ulu.ly","paper.li","soc.li","macrumo.rs","htn.to","ti.me","bbc.in","fw.to",
            "vrge.co","cnnmon.ie","cir.ca","rol.st","reut.rs","lnc.hr","ars.to","thndr.it","itpp.me","qntm.co","wbur.fm","owl.li","instagr.am","fb.me","nfr.no","twb.io","aka.ms",
            "yj.pn","eqent.me","rww.to","mbist.ro","prsm.tc","msft.it","ustre.am","tnw.co","ubm.io","zite.to","b0ing.me","theatln.tc","troy.hn","redd.it","kck.st","rsa.im","g.co",
            "oreil.ly","eurone.ws","f24.my","fam.ag","tmblr.co","dailym.ai","wef.ch","es.pn","reg.cx","mun.do","pops.ci","pitch.pe","mod.my","prn.to","fancy.to","mrk.to",
            "deck.ly","linkd.in","cbsloc.al","ift.tt","snlg.ht","dnlr.hn","wh.gov","c4a.me","frc.st","planca.st","yfrog.us","imrn.me","ads.tt","qr.ae","ptab.it",
            "v2.dk","xal.no","shz.am","engt.co","geor.gr","nyr.kr","qwapo.es","twky.in","techre.vu","buswk.co"                  
            }; 
            int shortu=0;  
            int longu=0;
            int totalu=0;
            while ((urlline = br.readLine()) != null) {
               
               //URL aURL = new URL(str[1]);
              try{
                    URL aURL = new URL(urlline);
                    String host=aURL.getHost();
                    //System.out.println("host = " + aURL.getHost()); 
                   // System.out.println("url"+urlline); 
                    totalu++;
                    count_total_urls++;
                    int flag=0;
                    for(String domain : domains){
                         if(host.equals(domain)){
                             shortu++;
                             count_total_short_urls++;
                             //writer.println(str[0]+"\t"+str[1]+"\t"+str[2]+"\t"+str[3]);
                             //writer.println(str[0]+"\t"+str[1]+"\t"+str[2]);
                             writer.println(urlline);


                            // System.out.println("find shorturl"+str[0]);
                             flag=1;
                             break;

                         }

                     }
                     if(flag==0)
                     {
                         longu++;
                         count_total_long_urls++;
                        // longURL.println(str[0]+"\t"+str[1]+"\t"+str[2]+"\t"+str[3]);
                         //longURL.println(str[0]+"\t"+str[1]+"\t"+str[2]);
                         longURL.println(urlline);

                         flag=0;
                     }


                }catch(Exception e)
                {
                        System.out.println(e);
                }
            }
                 System.out.println("File "+inputFile+" contains");
                 System.out.println("total shorturl: "+shortu+" file write into ------>"+shortUrlFile);
                 System.out.println("total longurl: "+longu+" file write into ------>"+longURLFile);
                 System.out.println("total url: "+totalu);
            
            try {
                   writer.close();
                   longURL.close();
               } catch (Exception e) {
                       e.printStackTrace();
               }

        }catch(Exception e)
        {
                System.out.println(e);
        }
    }
}
