package org.dobiz.crawler;

import org.dobiz.crawler.author.Author;
import org.dobiz.crawler.quotation.Quotation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

public class SimpleCrawler {
    public static void main(String[] args) {
        String url = "http://www.ucheol.com/wise/wise_01.html";
        Map<Author, Quotation> authorQuotationMap = new SimpleCrawler().crawlingQuote(url);
        authorQuotationMap.forEach(((author, quotation) -> System.out.println(author.show() + quotation.show())));
    }

    public Map<Author, Quotation> crawlingQuote(final String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            return extractQuotes(doc);
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    private Map<Author, Quotation> extractQuotes(final Document document) {
        HashMap<Author, Quotation> hashMap = new HashMap<>();
        Elements quoteElements = document.select("font[color=#006699]");
        String quoteText = findQuoteText(quoteElements);

        String[] strings = quoteText.split("●");
        for (String string : strings) {
            if (!string.isEmpty()) { // 문자열이 비어있지 않은 경우에만 작업 수행
                String context = string.split("☞")[0].split("-")[0].trim();
                String authorName = string.split("☞")[0].split("-")[1].trim();
                Author author = new Author(authorName);
                Quotation quotation = new Quotation(context);
                hashMap.putIfAbsent(author, quotation);
            }
        }
        return hashMap;
    }

    private String findQuoteText(final Elements elements) {
        for (Element element : elements) {
            Element parent = element.parent(); // '●'를 포함하는 font 태그의 부모 요소
            String quoteText = parent.text(); // 부모 요소의 전체 텍스트
            if (quoteText.contains("●")) return quoteText;
        }
        return "";
    }

}

