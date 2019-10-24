import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
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
		
		Elements description=doc.select("div.d-block");
		Elements primaryTag=doc.select("div.d-block > div.text-gray > span.mr-3");
		Elements starsCurrent=doc.select("div.d-block > div.text-gray > span.mr-3").next("a.muted-link");
		String lastUpdated=doc.select("div.d-block > div.text-gray").attr("relative-time");
//		for (Element link : giturl) {
//		  String linkHref = link.attr("href");
//		  String linkText = link.text();
//		  
//		  System.out.println("Text::"+linkText+", URL::"+linkHref);
//		}
		 System.out.println("mmmmmmmmmmmmmmmmm");
		for (Element link : description) {
			  String linkHref = link.attr("href");
			  String linkText = link.text();
			  List<Node> nodes=link.childNodes().stream().filter(o-> o instanceof Element).collect(Collectors.toList());
			  for(int i=0;i<=nodes.size();i++)
			  {
			  if(i==0)
				  System.out.println(Jsoup.parse(nodes.get(0).toString()).select("h3 > a").first().text());
			  if(i==1)
				  System.out.println(Jsoup.parse(nodes.get(1).toString()).select("p").first().text());
			  if(i==2)
			  {
				  List childNodes=nodes.stream().reduce((first, second) -> second).get().childNodes().stream().filter(o-> o instanceof Element).collect(Collectors.toList());
				  for(int j=0;j<=childNodes.size();j++)
				  {
					  if(j==0)
					  System.out.println(Jsoup.parse(childNodes.get(j).toString()).select("span").next().first().text());
					  if(j==1)
						  System.out.println(Jsoup.parse(childNodes.get(j).toString()).select("a.muted-link").first().text());
					  if(j==2)
						  System.out.println(Jsoup.parse(childNodes.get(j).toString()).select("a.muted-link").first().text());
					  if(j==3)
						  System.out.println(Jsoup.parse(childNodes.get(j).toString()).select("relative-time").first().text());

				  }
			  }
			  }
			
		  
			  List<Node> node =nodes.stream().reduce((first, second) -> second).get().childNodes();
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
