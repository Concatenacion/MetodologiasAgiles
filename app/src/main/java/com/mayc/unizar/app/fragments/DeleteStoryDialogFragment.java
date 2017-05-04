package com.mayc.unizar.app.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mayc.unizar.app.MenuActivity;
import com.mayc.unizar.app.R;
import com.mayc.unizar.app.adapters.DbAdapter;


/**
 * Created by josnick on 01/05/2017.
 */

public class DeleteStoryDialogFragment extends DialogFragment {
    public static final String ARG_STORY_NAME= "STORY_NAME";
    private int storyID;
    private String storyName;
    private View cView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        cView = inflater.inflate( R.layout.delete_story_dialog, container, false);
        getDialog().setTitle("Delete dialog");
        this.storyID=getArguments().getInt(CardFragment.ARG_STORY_ID,-1);
        this.storyName=getArguments().getString( ARG_STORY_NAME );
        return cView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete story");
        builder.setMessage("Do you want to delete the story "+storyName+"?");

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DbAdapter mDb = new DbAdapter(cView.getContext());
                mDb.removeHistoria( storyID );
                //reload stories fragment manager
                Fragment   f=new ManageStoriesFragment();
                MenuActivity.fm.beginTransaction().replace(R.id.default_fragment,f).commit();
                DrawerLayout drawer = (DrawerLayout) new MenuActivity().findViewById(R.id.drawer_layout);
                drawer.closeDrawer( GravityCompat.START);


            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        return builder.create();
    }
}
