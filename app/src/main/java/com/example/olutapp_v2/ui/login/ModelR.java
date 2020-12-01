package com.example.olutapp_v2.ui.login;

public class ModelR {

    String Name, Images;

    ModelR() {


    }

    public ModelR(String Namer, String Imagesr) {
        this.Name = Namer;
        this.Images = Imagesr;
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
}
