import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class Main {
	public static void main(String[] args) throws IOException {
		String url = "https://github.com/ackhare?tab=stars";
		Document doc = Jsoup.connect(url).get();

		while (true) {
			doc = Jsoup.connect(url).get();

			Elements nextLinks = doc.select("a.BtnGroup-item");
			Elements description = doc.select("div.d-block");
			for (Element link : description) {
				List<Node> nodes = link.childNodes().stream().filter(o -> o instanceof Element)
						.collect(Collectors.toList());
				for (int i = 0; i <= nodes.size(); i++) {
					if (i == 0)
						System.out.println(removeMiddleSpaces(
								Jsoup.parse(nodes.get(0).toString()).select("h3 > a").first().text()));
					if (i == 1)
						try {
							System.out.println(Jsoup.parse(nodes.get(1).toString()).select("p").first().text());
						} catch (NullPointerException e) {
							System.out.println("No description");
						}
					if (i == 2) {
						List childNodes = nodes.stream().reduce((first, second) -> second).get().childNodes().stream()
								.filter(o -> o instanceof Element).collect(Collectors.toList());
						for (int j = 0; j <= childNodes.size(); j++) {
							System.out.println("child node length " + childNodes.size());
							if (j == 0) {
								try {
									System.out.println(Jsoup.parse(childNodes.get(j).toString()).select("span").next()
											.first().text().trim());
								} catch (NullPointerException e) {
									System.out.println("No Language information here");
								}
							}
							if (j == 1)
								try {
									System.out.println(Jsoup.parse(childNodes.get(j).toString()).select("a.muted-link")
											.first().text());
								} catch (NullPointerException e) {
									System.out.println(
											"issue is that no language information so star info is one above ");
									System.out.println(Jsoup.parse(childNodes.get(0).toString()).select("a.muted-link")
											.first().text());

								}
							if (j == 2) {
								if (childNodes.size() == 4) {
									try {
										System.out.println(Jsoup.parse(childNodes.get(j).toString())
												.select("a.muted-link").first().text());
									} catch (NullPointerException e) {
										System.out.println("No fork information");
									}
								} else if (childNodes.size() == 3)
									System.out.println(Jsoup.parse(childNodes.get(j).toString()).select("relative-time")
											.first().text());
							}

							if (j == 3) {
								if (childNodes.size() == 4)
									System.out.println(Jsoup.parse(childNodes.get(j).toString()).select("relative-time")
											.first().text());
							}
						}
					}
				}
				List<Node> node = nodes.stream().reduce((first, second) -> second).get().childNodes();
			}

			url = null;
			for (Element link : nextLinks) {

				String linkHref = link.attr("href");
				String linkText = link.text();
				if (linkText.equals("Next")) {
					url = linkHref;
				}
			}
			if (url == null)
				break;

		}
	}

	static String removeMiddleSpaces(String text) {
		return Arrays.asList(text.split("/")).stream().map(x -> x.trim()).collect(Collectors.joining("/"));
	}
}
