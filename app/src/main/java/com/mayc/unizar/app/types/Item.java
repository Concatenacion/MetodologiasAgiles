package com.mayc.unizar.app.types;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("url")
    @Expose
    private String imageUrl;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("option1")
    @Expose
    private String option1;

    @SerializedName("nextOption1")
    @Expose
    private Integer nextOption1;

    @SerializedName("option2")
    @Expose
    private String option2;

    @SerializedName("nextOption2")
    @Expose
    private Integer nextOption2;

    @SerializedName("description")
    @Expose
    private String description;

    public Item(int id, String name, String imageUrl, String description,  String option1, int nextOption1, String option2, int nextOption2){
        this.name = name;
        this.imageUrl = imageUrl.substring(1,imageUrl.length()-1);
        this.id=id;
        this.option1=option1;
        this.nextOption1=nextOption1;
        this.option2=option2;
        this.nextOption2=nextOption2;
        this.description=description;
    }
    public Item(int id){
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl.substring(1,imageUrl.length()-1);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public Integer getNextOption1() {
        return nextOption1;
    }

    public void setNextOption1(Integer nextOption1) {
        this.nextOption1 = nextOption1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public Integer getNextOption2() {
        return nextOption2;
    }

    public void setNextOption2(Integer nextOption2) {
        this.nextOption2 = nextOption2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}