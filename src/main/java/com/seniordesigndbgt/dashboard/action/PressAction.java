package com.seniordesigndbgt.dashboard.action;

import com.seniordesigndbgt.dashboard.analytics.AnalyzerFactory;
import com.seniordesigndbgt.dashboard.model.Press;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class PressAction {

    public PressAction() {}

    public static String getBodyContent(Press article) throws IOException {
        String url = article.getUrl();
        String source = article.getSource();
        Document doc = Jsoup.connect(url).get();
        String text = "";
        switch (source) {
            case "Reuters":
                Elements body = doc.select("#articleText");
                text = body.text();
                break;
            case "Bloomberg":
                text = "";
                break;
        }
        return text;
    }
}
