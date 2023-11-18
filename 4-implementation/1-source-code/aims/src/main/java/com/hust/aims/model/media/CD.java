package com.hust.aims.model.media;

import java.util.Date;
import java.util.List;

public class CD extends Media {
    private String artist;
    private String label;
    private String genre;
    private Date releaseDate;

    private List<Track> trackList;

    public CD(Integer id, Double price, Integer value, String title, String category, String description, Integer quantity, Double weight, boolean supportRushOrder) {
        super(id, price, value, title, category, description, quantity, weight, supportRushOrder);
    }


    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
