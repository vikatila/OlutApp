package com.example.olutapp_v2.ui.login;

public class Model {

    String Name, Images;

    Model() {


    }

    public Model(String Name, String Images) {
        this.Name = Name;
        this.Images = Images;
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
}
