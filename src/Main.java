import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Main {
	public static void main(String[] args) throws IOException {
		String url="https://github.com/ackhare?tab=stars";
		 Document doc = Jsoup.connect(url).get();
//		 for (Element link : resultsH3) {
//			  String linkHref = link.attr("href");
//			  String linkText = link.text();
//			  System.out.println("Text::"+linkText+", URL::"+linkHref);
//			}
		for(int i=0;i<=10;i++)
		{
		 doc = Jsoup.connect(url).get();
		Elements resultsH3 = doc.select("h3 > a");
		Elements nnn = doc.select("a.BtnGroup-item");
		 System.out.println(nnn.attr("href"));
		for (Element link : resultsH3) {
		  String linkHref = link.attr("href");
		  String linkText = link.text();
		  System.out.println("Text::"+linkText+", URL::"+linkHref);
		}
		for (Element link : nnn) {
			  String linkHref = link.attr("href");
			  String linkText = link.text();
			  if(linkText.equals("Next"))
				url=linkHref;
			}
		
		}
	}
}
