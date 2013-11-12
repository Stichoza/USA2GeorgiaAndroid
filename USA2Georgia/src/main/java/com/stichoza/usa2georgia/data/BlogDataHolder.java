package com.stichoza.usa2georgia.data;

/**
 * Created by Stichoza on 12/11/13.
 */
public class BlogDataHolder {

    private String url;
    private String text;

    public BlogDataHolder(String url, String text){
        this.url = url;
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }
}
