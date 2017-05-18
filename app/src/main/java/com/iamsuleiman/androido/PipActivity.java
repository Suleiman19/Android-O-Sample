package com.iamsuleiman.androido;

import android.app.PictureInPictureArgs;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

public class PipActivity extends AppCompatActivity {


    private final PictureInPictureArgs mPictureInPictureArgs = new PictureInPictureArgs();

    private LinearLayout mLayoutContent;
    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pip);

        mLayoutContent = (LinearLayout) findViewById(R.id.pip_content);
        mVideoView = (VideoView) findViewById(R.id.pip_videoview);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();
        String path = "android.resource://" + getPackageName() + "/" + R.raw.sample;

        mVideoView.setVideoURI(Uri.parse(path));
//        mVideoView.start();

        Button button = (Button) findViewById(R.id.pip_btn);
        button.setOnClickListener(view -> {
            try {

                // maintain aspect ratio while minimizing.
                float aspectRatio = (float) mVideoView.getWidth() / mVideoView.getHeight();
                mPictureInPictureArgs.setAspectRatio(aspectRatio);
                enterPictureInPictureMode(mPictureInPictureArgs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }


    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);

        if (!isInPictureInPictureMode) {
            getApplication().startActivity(new Intent(this, getClass())
                    .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));

        }
    }


//    public void setAdjustViewBounds(boolean adjustViewBounds) {
//        if (mAdjustViewBounds == adjustViewBounds) {
//            return;
//        }
//        mAdjustViewBounds = adjustViewBounds;
//        if (adjustViewBounds) {
//            setBackground(null);
//        } else {
//            setBackgroundColor(Color.BLACK);
//        }
//        requestLayout();
//    }
}
