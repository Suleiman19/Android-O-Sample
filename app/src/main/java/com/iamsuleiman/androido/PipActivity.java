package com.iamsuleiman.androido;

import android.app.PictureInPictureArgs;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PipActivity extends AppCompatActivity {

    private ImageButton mBtnDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pip_test);


//        mBtnDown = (ImageButton) findViewById(R.id.pip_imgbtn_down);
//        Drawable tintedDrawable =
//                UiUtils.tintDrawable(
//                        ContextCompat.getDrawable(this, R.drawable.ic_keyboard_arrow_down_black_24dp),
//                        Color.WHITE);
//
//        mBtnDown.setImageDrawable(tintedDrawable);
//
//        VideoView mVideoView = (VideoView) findViewById(R.id.pip_videoview);
//        mVideoView.setMediaController(new MediaController(this));
//        mVideoView.requestFocus();
//        mVideoView.start();

        final View mockView = findViewById(R.id.pip_view);
        Button button = (Button) findViewById(R.id.btn_test);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Trigger PiP mode
                try {

                    float aspectRatio = (float) mockView.getWidth() / mockView.getHeight();

                    PictureInPictureArgs mPictureInPictureArgs = new PictureInPictureArgs();
                    mPictureInPictureArgs.setAspectRatio(aspectRatio);

                    enterPictureInPictureMode(mPictureInPictureArgs);

                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
    }
}
