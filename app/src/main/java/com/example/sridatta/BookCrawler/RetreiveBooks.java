package com.example.sridatta.BookCrawler;

/**
 * Created by sridatta on 27-11-2016.
 */

//under construction
    // class to retrieve the info to set to the cards/ recyclerView

public class RetreiveBooks {


    private String title;
    private String author;
    private String code;
    private String publisher;

    public RetreiveBooks(){

    }

    public RetreiveBooks(String publisher, String code, String author, String title) {
        this.publisher = publisher;
        this.code = code;
        this.author = author;
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
