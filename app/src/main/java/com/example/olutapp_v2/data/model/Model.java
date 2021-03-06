package com.example.olutapp_v2.data.model;   // Model.java-luokkaa käytetään suositeltujen oluiden tietojen hakemiseen tietokannasta.
                                            //  Tämän kautta saadaan tiedot "oluet-recycler" viewille

public class Model {

    String Name, Images, Review; // Haetaan nimi, kuvat ja arvosana


    Model() {


    }

    public Model(String Name, String Images, String Review) {
        this.Name = Name;
        this.Images = Images;
        this.Review = Review;
    }



    public String getName() {
        return Name;
    }                               //get-set metodit

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
