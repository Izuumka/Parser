import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Document document = Jsoup.connect("https://bt.rozetka.com.ua/ua/washing_machines/c80124/filter/").get();
        Elements links = document.select("div.g-i-tile-i-title.clearfix");
        for (Element element : links) {
            Document doc2 = Jsoup.connect(element.select("a").attr("href")).get();
            String title = doc2.select("h1.detail-title").text();
            Document comments = Jsoup.connect(doc2.select("a").attr("href")).get();
            String comment = comments.select("article.pp-review-i").text();
            System.out.println(title);
            System.out.println(comment);
        }
    }
}

