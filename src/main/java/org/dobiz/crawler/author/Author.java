package org.dobiz.crawler.author;

import java.util.Objects;

public class Author {
    private final String name;

    public Author(String name) {
        this.name = name;
    }

    public String show() {
        return "author: " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}
