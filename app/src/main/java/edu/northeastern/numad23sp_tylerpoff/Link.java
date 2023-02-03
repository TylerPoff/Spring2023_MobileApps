package edu.northeastern.numad23sp_tylerpoff;

public class Link {
    private final String name;
    private final String url;

    public Link(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return this.name;
    }
    public String getUrl() {
        return this.url;
    }
}
