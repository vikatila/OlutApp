package com.example.olutapp_v2.data.model;          // ModelR.java-luokkaa käytetään suositeltujen ravintoloiden tietojen hakemiseen tietokannasta.
                                                    //Tämän kautta saadaan tiedot "ravintolat-recycler" viewille
public class ModelR {

    String Name, Images, Review; // Haetaan nimi, kuvat ja arvosana

    ModelR() {


    }

    public ModelR(String Namer, String Imagesr, String Reviewr) {
        this.Name = Namer;
        this.Images = Imagesr;
        this.Review = Reviewr;
    }

    public String getName() {
        return Name;
    }                                   //get-set metodit

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