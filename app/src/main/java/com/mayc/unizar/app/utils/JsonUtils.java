package com.mayc.unizar.app.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mayc.unizar.app.types.HistoryInfo;
import com.mayc.unizar.app.types.Item;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = "JsonUtils";

    public static List<Item> loadProfiles(Context context){
        try{
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            JSONArray array = new JSONArray(loadJSONFromAsset(context, "profiles.json"));
            List<Item> itemList = new ArrayList<>();
            for(int i=0;i<array.length();i++){
                Item item = gson.fromJson(array.getString(i), Item.class);
                itemList.add(item);
            }
            return itemList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /*
     *  Parser para obtener las historias almacenadas
     */
    public static List<HistoryInfo> loadInfiniteFeeds(Context context){
        try{
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            JSONArray array = new JSONArray(loadJSONFromAsset(context, "histories.json"));
            List<HistoryInfo> feedList = new ArrayList<>();
            for(int i=0;i<array.length();i++){
                HistoryInfo feed = gson.fromJson(array.getString(i), HistoryInfo.class);
                feedList.add(feed);
            }
            return feedList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private static String loadJSONFromAsset(Context context, String jsonFileName) {
        String json = null;
        InputStream is=null;
        try {
            AssetManager manager = context.getAssets();
            Log.d(TAG,"path "+jsonFileName);
            is = manager.open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}