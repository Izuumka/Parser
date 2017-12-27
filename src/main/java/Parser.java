import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

class Parser {
    private Elements elements;
    public Parser(String url) throws IOException {
        this.elements = this.getElements(url);
        this.Parse_coment();
    }

    private Elements getElements(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        return document.select("div.g-i-tile-i-title.clearfix");
    }

    public ArrayList<Document> Parse_name_info() throws IOException {
        ArrayList<Document> name = new ArrayList<Document>();

            for (Element info : this.elements) {
                Document document = Jsoup.connect(info.select("a").attr("href")).get();
                name.add(document);
            }
        return name;
    }
    public void Parse_coment() throws  IOException{
        int count = 1;
        for (Document info : this.Parse_name_info()) {
            int min_size = 0;
            Document document = Jsoup.connect(info.select("div.detail-review-more").select("a").attr("href")).get();
            Elements authors = document.select("span.pp-review-author-name");  // All commentators
            Elements comments = document.select("div.pp-review-text-i"); //All comments
            System.out.println(authors.size() + " reviews for " + info.select("h1.detail-title").text());

            for (Element comment : comments) {
                String com_size = String.valueOf(comment);
                int size = com_size.length() - 41;
                if (count == 1) {
                    min_size = size;
                }
                if (size > min_size) {
                    min_size = size;
                }
            }

            System.out.println("The smallest comment was " + min_size + " characters long");
            count++;
        }
        }
    }
