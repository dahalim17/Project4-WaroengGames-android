package com.example.app;

public class ComingSoonGames {

    private String id;
    private String titleCS;
    private String genreCS;
    private String priceCS;
    private String releasedCS;
    private String imageCS;

    public ComingSoonGames(String id, String titleCS, String genreCS, String priceCS, String releasedCS, String imageCS) {
        this.id = id;
        this.titleCS = titleCS;
        this.genreCS = genreCS;
        this.priceCS = priceCS;
        this.releasedCS = releasedCS;
        this.imageCS = imageCS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitleCS() {
        return titleCS;
    }

    public void setTitleCS(String titleCS) {
        this.titleCS = titleCS;
    }

    public String getGenreCS() {
        return genreCS;
    }

    public void setGenreCS(String genreCS) {
        this.genreCS = genreCS;
    }

    public String getPriceCS() {
        return priceCS;
    }

    public void setPriceCS(String priceCS) {
        this.priceCS = priceCS;
    }

    public String getReleasedCS() {
        return releasedCS;
    }

    public void setReleasedCS(String releasedCS) {
        this.releasedCS = releasedCS;
    }

    public String getImageCS() {
        return imageCS;
    }

    public void setImageCS(String imageCS) {
        this.imageCS = imageCS;
    }
}

