package com.example.app;

public class Games {

    private String id;
    private String title;
    private String genre;
    private String price;
    private String image;


    public Games(){}

    public Games(String id, String title, String genre, String price, String image) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
