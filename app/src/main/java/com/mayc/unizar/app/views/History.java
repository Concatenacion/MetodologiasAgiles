package com.mayc.unizar.app.views;

import android.app.Activity;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.mayc.unizar.app.MenuActivity;
import com.mayc.unizar.app.R;
import com.mayc.unizar.app.fragments.CardFragment;
import com.mayc.unizar.app.fragments.DefaultFragment;
import com.mayc.unizar.app.fragments.DeleteStoryDialogFragment;
import com.mayc.unizar.app.types.HistoryInfo;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.LongClick;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;


@NonReusable
@Layout(R.layout.history_view)
public class History {

    @View(R.id.titleTxt)
    private TextView titleTxt;

    @View(R.id.captionTxt)
    private TextView captionTxt;

    @View(R.id.timeTxt)
    private TextView timeTxt;

    @View(R.id.imageView)
    private ImageView imageView;

    private HistoryInfo mInfo;
    private Context mContext;

    public History(Context context, HistoryInfo info) {
        mContext = context;
        mInfo = info;
    }

    @Resolve
    private void onResolved() {
        titleTxt.setText(mInfo.getTitle());
        captionTxt.setText(mInfo.getCaption());
        timeTxt.setText(mInfo.getTime());
        Glide.with(mContext).load(mInfo.getImageUrl()).into(imageView);
    }


    @Click(R.id.StoryCard)
    private void onClick(){
        //llamar cardFragment
        Fragment f = new DefaultFragment();
        Bundle b = new Bundle();
        b.putInt(CardFragment.ARG_STORY_ID,mInfo.getID() );
        f=new CardFragment();
        f.setArguments( b );
        MenuActivity.fm.beginTransaction().replace(R.id.default_fragment,f).commit();
        DrawerLayout drawer = (DrawerLayout) new MenuActivity().findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @LongClick(R.id.StoryCard)
    private void onLongClick(){
        Bundle b = new Bundle();
        b.putInt(CardFragment.ARG_STORY_ID,mInfo.getID() );
        b.putString("STORY_NAME",mInfo.getTitle() );
        DeleteStoryDialogFragment dialogFragment = new DeleteStoryDialogFragment ();
       dialogFragment.setArguments( b );
       dialogFragment.show(MenuActivity.fm, "Borrar historia");
    }
}