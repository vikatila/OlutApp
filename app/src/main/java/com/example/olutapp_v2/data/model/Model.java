package com.example.olutapp_v2.data.model;

public class Model {

    String Name, Images, Review; //reviewiä varten haetaan arvosana tietokannasta

    Model() {


    }

    public Model(String Name, String Images, String Review) {     // String "Review" arvostelun hakuun tietokannasta
        this.Name = Name;
        this.Images = Images;
        this.Review = Review;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImages() {
        return Images;
    }

    public void setImages(String images) {
        Images = images;
    }

    public String getReview() { return Review; }

    public void setReview(String review) { Review = review; }
}
