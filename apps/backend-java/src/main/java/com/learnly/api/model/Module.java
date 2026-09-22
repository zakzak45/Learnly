package com.learnly.api.model;

public class Module {

    private String title;
    private String description; // short blurb on what this module covers
    private String resourceUrl; // link to the external free resource
    private String provider;    // e.g. "freeCodeCamp", "Coursera (audit)", "YouTube"
    private int order;

    public Module() {}

    public Module(String title, String description, String resourceUrl, String provider, int order) {
        this.title = title;
        this.description = description;
        this.resourceUrl = resourceUrl;
        this.provider = provider;
        this.order = order;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getResourceUrl() { return resourceUrl; }
    public void setResourceUrl(String resourceUrl) { this.resourceUrl = resourceUrl; }
    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }
    public int getOrder() { return order; }
    public void setOrder(int order) { this.order = order; }
}
