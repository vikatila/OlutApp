package com.example.olutapp_v2.ui.login;

public class ModelR {

    String Name, Images, Review; //reviewiä varten haetaan arvosana tietokannasta;

    ModelR() {


    }

    public ModelR(String Namer, String Imagesr, String Reviewr) {     // String "Review" arvostelun hakuun tietokannasta) {
        this.Name = Namer;
        this.Images = Imagesr;
        this.Review = Reviewr;
    }

    public String getName() {
        return Name;
    }

    public void setName(String namer) {
        Name = namer;
    }

    public String getImages() {
        return Images;
    }

    public void setImages(String imagesr) {
        Images = imagesr;
    }

    public String getReview() { return Review; }

    public void setReview(String reviewr) { Review = reviewr; }
}