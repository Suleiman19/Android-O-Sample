package com.iamsuleiman.androido;


import android.app.PictureInPictureParams;
import android.app.RemoteAction;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Rational;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

public class PipActivity extends AppCompatActivity {
    private static final String TAG = PipActivity.class.getSimpleName();

    private ImageButton mBtnPip;
    private FrameLayout mFramePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pip_test);

        mFramePlayer = findViewById(R.id.frame_mock_player);
        mBtnPip = findViewById(R.id.btn_minimize);

        mBtnPip.setOnClickListener(view -> {
            if (android.os.Build.VERSION.SDK_INT >= 26) {
                //Trigger PiP mode
                try {
                    Rational rational = new Rational(mFramePlayer.getWidth(), mFramePlayer.getHeight());

                    PictureInPictureParams mParams =
                            new PictureInPictureParams.Builder()
                                    .setAspectRatio(rational)
                                    .build();

                    enterPictureInPictureMode(mParams);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(PipActivity.this, "API 26 needed to perform PiP", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);

        if (!isInPictureInPictureMode) {
            // Restore your (player) UI
            mBtnPip.setVisibility(View.VISIBLE);
//            getApplication().startActivity(new Intent(this, getClass())
//                    .addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT));
        } else {
            // Hide all UI controls except video
            mBtnPip.setVisibility(View.GONE);
        }
    }
}
