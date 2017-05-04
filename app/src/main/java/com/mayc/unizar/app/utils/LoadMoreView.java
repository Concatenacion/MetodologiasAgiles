package com.mayc.unizar.app.utils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.mayc.unizar.app.R;
import com.mayc.unizar.app.types.HistoryInfo;
import com.mayc.unizar.app.views.History;
import com.mindorks.placeholderview.InfinitePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.infinite.LoadMore;

import java.util.List;

/**
 * Created by Portátil1 on 04/05/2017.
 */

@Layout(R.layout.load_more_view)
public class LoadMoreView {

    public static final int LOAD_VIEW_SET_COUNT = 6;

    private InfinitePlaceHolderView mLoadMoreView;
    private List<HistoryInfo> mFeedList;

    public LoadMoreView(InfinitePlaceHolderView loadMoreView, List<HistoryInfo> feedList) {
        this.mLoadMoreView = loadMoreView;
        this.mFeedList = feedList;
    }

    @LoadMore
    private void onLoadMore(){
        Log.d("DEBUG", "onLoadMore");
        new ForcedWaitedLoading();
    }

    class ForcedWaitedLoading implements Runnable{

        public ForcedWaitedLoading() {
            new Thread(this).start();
        }

        @Override
        public void run() {

            try {
                Thread.currentThread().sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            new Handler( Looper.getMainLooper()).post( new Runnable() {
                @Override
                public void run() {
                    int count = mLoadMoreView.getViewCount();
                    Log.d("DEBUG", "count " + count);
                    Log.d("DEBUG", "size " + mFeedList.size());
                    if(count==mFeedList.size()){
                        Log.d("DEBUG", "No cargar mas.");
                        mLoadMoreView.noMoreToLoad();
                    }

                    else {
                        for (int i = count;
                             i < (count - 1 + LoadMoreView.LOAD_VIEW_SET_COUNT) && mFeedList.size() > i;
                             i++) {
                            mLoadMoreView.addView( new History( mLoadMoreView.getContext(), mFeedList.get( i ) ) );

                            if (i == mFeedList.size() - 1) {
                                mLoadMoreView.noMoreToLoad();
                                break;
                            }
                        }
                    }
                    mLoadMoreView.loadingDone();
                }
            });
        }
    }
}
