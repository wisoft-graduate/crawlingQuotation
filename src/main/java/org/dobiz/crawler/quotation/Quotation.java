package org.dobiz.crawler.quotation;

import org.dobiz.crawler.author.Author;

public class Quotation {
    private final String context;

    public Quotation(String context) {
        this.context = context;
    }

    public String show() {
        return ", context: " + context;
    }
}
