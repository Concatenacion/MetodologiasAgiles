package com.mayc.unizar.app.types;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Carga la informacion de una historia
 */
public class HistoryInfo {


    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("caption")
    @Expose
    private String caption;

    @SerializedName("Body")
    @Expose
    private String body;

    @SerializedName("time")
    @Expose
    private String time;



    public HistoryInfo(int id, String title, String imageUrl, String caption,  String body, String time){
        this.id=id;
        this.title = title;
        if(imageUrl==null)
            this.imageUrl="https://cdn1.iconfinder.com/data/icons/ios-7-style-metro-ui-icons/512/MetroUI_OS_Android.png";
        else this.imageUrl = imageUrl.substring(1,imageUrl.length()-1);
        this.caption=caption;
        this.body=body;
        this.time=time;
    }

    public HistoryInfo(int id){
        this.id=id;
    }

    public int getID() {return this.id;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }



}