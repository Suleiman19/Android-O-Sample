package com.iamsuleiman.androido;

import android.app.PictureInPictureParams;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Rational;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class PipActivity extends AppCompatActivity {

    private ImageButton mBtnDown;
    private Button button;
    private View mockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pip_test);

        mockView = findViewById(R.id.pip_view);
        mBtnDown = findViewById(R.id.btn_minimize);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mBtnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (android.os.Build.VERSION.SDK_INT >= 26) {
                    //Trigger PiP mode
                    try {
//                        float aspectRatio = (float) mockView.getWidth() / mockView.getHeight();
                        Rational rational = new Rational(mockView.getWidth(), mockView.getHeight());

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
            }
        });
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);

        if (isInPictureInPictureMode) {
            mBtnDown.setVisibility(View.GONE);
        } else {
            mBtnDown.setVisibility(View.VISIBLE);
        }

    }
}
