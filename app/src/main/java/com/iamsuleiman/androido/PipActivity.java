package com.iamsuleiman.androido;

import android.animation.ObjectAnimator;
import android.app.PictureInPictureArgs;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.transition.TransitionManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.autofill.AutofillManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class PipActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = PipActivity.class.getSimpleName();

    private final PictureInPictureArgs mPictureInPictureArgs = new PictureInPictureArgs();

    private LinearLayout mLayoutContent;
    private VideoView mVideoView;
    private LinearLayout mLayoutPlayerControls;
    private View mPlayerOverlay;
    private ImageButton mBtnPlay, mBtnRewind, mBtnForward;
    private Button mBtnPip;

    private RelativeLayout mFramePlayerParent;

    private boolean playerControlsVisible = true;

    private Surface mSurface;

    private Drawable mDrawablePlay;
    private Drawable mDrawablePause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pip);

        mDrawablePlay = fetchDrawable(R.drawable.ic_play_circle_filled_black_24dp);
        mDrawablePause = fetchDrawable(R.drawable.ic_pause_circle_filled_black_24dp);

        mFramePlayerParent = (RelativeLayout) findViewById(R.id.pip_video_layout_parent);
        mLayoutContent = (LinearLayout) findViewById(R.id.pip_content);
        mVideoView = (VideoView) findViewById(R.id.pip_videoview);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.sample;
        mVideoView.setVideoURI(Uri.parse(path));

        mLayoutPlayerControls = (LinearLayout) findViewById(R.id.pip_video_controls);
        mPlayerOverlay = findViewById(R.id.pip_video_overlay);

        mBtnPlay = (ImageButton) findViewById(R.id.pip_btn_play);
        mBtnRewind = (ImageButton) findViewById(R.id.pip_btn_rewind);
        mBtnForward = (ImageButton) findViewById(R.id.pip_btn_forward);
        mBtnPip = (Button) findViewById(R.id.pip_btn);

        mFramePlayerParent.setOnClickListener(this);
        mBtnPlay.setOnClickListener(this);
        mBtnForward.setOnClickListener(this);
        mBtnRewind.setOnClickListener(this);
        mBtnPip.setOnClickListener(this);

    }


    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);

        if (!isInPictureInPictureMode) {
            getApplication().startActivity(new Intent(this, getClass())
                    .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
        } else {

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pip_video_layout_parent:
                playerControlsVisible = !playerControlsVisible;
                togglePlayerControls(playerControlsVisible);
                break;

            case R.id.pip_btn:
                // maintain aspect ratio while minimizing.
                float aspectRatio = (float) mVideoView.getWidth() / mVideoView.getHeight();
                mPictureInPictureArgs.setAspectRatio(aspectRatio);
                mSurface = mVideoView.getHolder().getSurface();
                enterPictureInPictureMode(mPictureInPictureArgs);
                break;

            case R.id.pip_btn_rewind:
                break;

            case R.id.pip_btn_play:
                toggleVideoPlay();
                break;

            case R.id.pip_btn_forward:
                break;
        }
    }

    private void toggleVideoPlay() {
        //play button visible
        if (mBtnPlay.getDrawable().equals(mDrawablePlay)) {
            mBtnPlay.setImageDrawable(mDrawablePause);
            mVideoView.start();

        } else {
            mBtnPlay.setImageDrawable(mDrawablePlay);
            if (mVideoView.isPlaying()) mVideoView.pause();
        }
    }


    private void togglePlayerControls(boolean show) {
        Log.d(TAG, "togglePlayerControls: show? " + show);
        // smooth fade out player controls
        TransitionManager.beginDelayedTransition(mLayoutPlayerControls);
        ObjectAnimator.ofFloat(mPlayerOverlay, "alpha",
                mPlayerOverlay.getVisibility() == View.VISIBLE ? 0 : 0.4F)
                .setDuration(300)
                .start();

        mLayoutPlayerControls.setVisibility(show ? View.VISIBLE : View.GONE);
        mPlayerOverlay.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private Drawable fetchDrawable(@DrawableRes int id) {
        return ContextCompat.getDrawable(PipActivity.this, id);
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
