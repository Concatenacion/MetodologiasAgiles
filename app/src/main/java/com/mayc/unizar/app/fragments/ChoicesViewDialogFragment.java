package com.mayc.unizar.app.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mayc.unizar.app.R;
import com.mayc.unizar.app.types.Item;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by josnick on 01/05/2017.
 */

public class ChoicesViewDialogFragment extends DialogFragment {

    private Button next;
    private TextView nameAgeTxt,locationNameTxt,option2,option1;
    private ImageView profileImageView;



    public static final String ARG_STORY_NAME= "STORY_NAME";
    private int storyID;
    private int count;
    private String storyName;
    private View cView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        cView = inflater.inflate( R.layout.last_choices_dialog, container, false);
        getDialog().setTitle("Last choices");
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        count=0;
        final int limit=((Item[]) getArguments().getSerializable( "Choices" )).length;
        initialize();
        loadCardInfo( count );

        next.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if(count==(limit-1))
                    dismiss();
                else
                    loadCardInfo( count );

            }
        } );


        return cView;
    }

    private void loadCardInfo(int n){
        Item i=((Item[]) getArguments().getSerializable( "Choices" ))[count];
        nameAgeTxt.setText( i.getName());
        locationNameTxt.setText( i.getDescription() );
        option2.setText( i.getOption2() );
        option1.setText( i.getOption1()  );
        Glide.with(cView.getContext()).load(i.getImageUrl()).into(profileImageView);

    }

    private void initialize(){
        nameAgeTxt =(TextView) cView.findViewById( R.id.nameAgeTxt );
        locationNameTxt =(TextView) cView.findViewById( R.id.locationNameTxt );
        option2 =(TextView) cView.findViewById( R.id.option2 );
        option1 =(TextView) cView.findViewById( R.id.option1 );
        profileImageView= (ImageView)  cView.findViewById( R.id.profileImageView );
        next=(Button) cView.findViewById( R.id.nextButton );


    }

}
