package com.yahoo.plumbus.ui.derp;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.yahoo.plumbus.R;
import com.yahoo.plumbus.ui.BaseFragment;

/**
 * Created by yxiang on 11/27/16.
 */

public class DerpFragment extends BaseFragment {

    VideoView mVideoView;
    View mVideoActivator;
    Handler mHandler = new Handler();
    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mVideoView != null) {
                mVideoView.pause();
                mVideoView.seekTo(5000);
            }
        }
    };
    public static DerpFragment newInstance() {
        DerpFragment f = new DerpFragment();
        return f;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_derp;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View layout = super.onCreateView(inflater, container, savedInstanceState);
        mVideoView = (VideoView) layout.findViewById(R.id.video);
        mVideoActivator = layout.findViewById(R.id.derp_btn);
        mVideoView.setMediaController(new MediaController(getContext()));
        mVideoView.setVideoPath("android.resource://" + "com.yahoo.plumbus" + "/" + R.raw.derping);
        mVideoView.seekTo(5000);
        mVideoView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                        mVideoView.start();
                        mHandler.removeCallbacks(mRunnable);
                        mHandler.postDelayed(mRunnable, 5000);
                        break;
                }
                return true;
            }
        });
        mVideoActivator.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return false;
            }
        });
        mVideoView.setScaleX(2.3f);
        mVideoView.setScaleY(2.3f);
        return layout;
    }

}
