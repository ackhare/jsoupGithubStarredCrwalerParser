import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Main {
	public static void main(String[] args) throws IOException {
		int numberOfPages=0;
		String url="https://github.com/ackhare?tab=stars";
		 Document doc = Jsoup.connect(url).get();

		while(true)
		{
		 doc = Jsoup.connect(url).get();
		Elements giturl = doc.select("h3 > a");
		Elements nextLinks = doc.select("a.BtnGroup-item");
		
		Elements description=doc.select("div.d-block > div.py-1");
		Elements primaryTag=doc.select("div.d-block > div.text-gray > span.mr-3");
		Elements starsCurrent=doc.select("div.d-block > div.text-gray > span.mr-3").next("a.muted-link");
		String lastUpdated=doc.select("div.d-block > div.text-gray").attr("relative-time");
		for (Element link : giturl) {
		  String linkHref = link.attr("href");
		  String linkText = link.text();
		  
		  System.out.println("Text::"+linkText+", URL::"+linkHref);
		}
		 System.out.println("mmmmmmmmmmmmmmmmm");
		for (Element link : description) {
			  String linkHref = link.attr("href");
			  String linkText = link.text();
			  
			  System.out.println("Text::"+linkText);
			}
		
		 System.out.println("lololololololololo");
		for (Element link : primaryTag) {
			  String linkHref = link.attr("href");
			  String linkText = link.text();
			  
			  System.out.println("Text::"+linkText);
			}
		
		url=null;
		for (Element link : nextLinks) {
			 
			  String linkHref = link.attr("href");
			  String linkText = link.text();
			  if(linkText.equals("Next"))
			  {
				url=linkHref;
			}
		}
		if(url==null)
			break;
		
		}
	}
}
