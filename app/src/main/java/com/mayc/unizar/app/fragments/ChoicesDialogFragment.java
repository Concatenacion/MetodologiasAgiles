package com.mayc.unizar.app.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mayc.unizar.app.MenuActivity;
import com.mayc.unizar.app.R;
import com.mayc.unizar.app.adapters.DbAdapter;
import com.mayc.unizar.app.factories.ItemFactory;
import com.mayc.unizar.app.types.Item;


/**
 * Created by josnick on 01/05/2017.
 */

public class ChoicesDialogFragment extends DialogFragment {
    public static final String ARG_STORY_NAME= "STORY_NAME";
    private int storyID;
    private String storyName;
    private View cView;
    private Item[] lastChoices;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        cView = inflater.inflate( R.layout.delete_story_dialog, container, false);
        getDialog().setTitle("Last choices");

        this.storyID=getArguments().getInt(CardFragment.ARG_STORY_ID,-1);
        this.storyName=getArguments().getString("STORY_NAME");
        this.lastChoices= (Item[]) getArguments().getSerializable( "Choices" );
        return cView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Do you want see last choices of story "+getArguments().getString("STORY_NAME")+"?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    //load new view dialog

                Bundle b = new Bundle();
                b.putInt(CardFragment.ARG_STORY_ID,getArguments().getInt(CardFragment.ARG_STORY_ID,-1) );
                b.putSerializable( "Choices" ,(Item[]) getArguments().getSerializable( "Choices" ) );
                ChoicesViewDialogFragment dialogFragment = new ChoicesViewDialogFragment ();
                dialogFragment.setArguments( b );
                dialogFragment.show( MenuActivity.fm, "Last choices view");
                dismiss();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        return builder.create();
    }
}
