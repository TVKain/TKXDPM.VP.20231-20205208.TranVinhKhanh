package com.hust.aims.model.media;

import java.util.Date;

public class Book extends Media {
    private String author;
    private Integer pageNumber;
    private String publisher;
    private Date publishDate;
    private String language;
    private String category;

    public Book(Integer id, Double price, Integer value, String title, String category, String description, Integer quantity, Double weight, boolean supportRushOrder) {
        super(id, price, value, title, category, description, quantity, weight, supportRushOrder);
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }
}
