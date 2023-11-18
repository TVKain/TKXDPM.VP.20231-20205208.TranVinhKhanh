package com.hust.aims.model.media;

import java.util.Date;
import java.util.List;

public class DVD extends Media {
    private String director;
    private String studio;
    private Integer runtime;
    private Date releaseDate;
    private String language;
    private String genre;
    private List<Subtitle> subtitleList;

    public DVD(Integer id, Double price, Integer value, String title, String category, String description, Integer quantity, Double weight, boolean supportRushOrder) {
        super(id, price, value, title, category, description, quantity, weight, supportRushOrder);
    }


    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Subtitle> getSubtitleList() {
        return subtitleList;
    }

    public void setSubtitleList(List<Subtitle> subtitleList) {
        this.subtitleList = subtitleList;
    }
}
