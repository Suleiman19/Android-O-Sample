package com.iamsuleiman.androido;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.provider.FontRequest;
import android.support.v4.provider.FontsContractCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FontActivity extends AppCompatActivity {
    private static final String TAG = FontActivity.class.getSimpleName();

    private TextView mTextIntro;

    private Handler mHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font);

        mTextIntro = findViewById(R.id.font_txt_intro);


        if (android.os.Build.VERSION.SDK_INT >= 26) {
            Typeface typefaceLato = getResources().getFont(R.font.lato_bold);
            mTextIntro.setTypeface(typefaceLato, Typeface.ITALIC);
        }

        FontRequest fontRequest = new FontRequest(
                "com.google.android.gms.fonts",
                "com.google.android.gms",
                "PT_Sans",
                R.array.com_google_android_gms_fonts_certs);

        FontsContractCompat.FontRequestCallback callback = new FontsContractCompat.FontRequestCallback() {
            @Override
            public void onTypefaceRequestFailed(int reason) {
                Log.e(TAG, "onTypefaceRequestFailed: %d" + reason);
            }

            @Override
            public void onTypefaceRetrieved(Typeface typeface) {
                Log.i(TAG, "onTypefaceRetrieved: ");
                mTextIntro.setTypeface(typeface);
            }
        };


        Log.d(TAG, "onCreate: requesting font...");
        FontsContractCompat.
                requestFont(this, fontRequest, callback, getHandlerThreadHandler());

    }

    private Handler getHandlerThreadHandler() {
        if (mHandler == null) {
            HandlerThread handlerThread = new HandlerThread("fonts");
            handlerThread.start();
            mHandler = new Handler(handlerThread.getLooper());
        }
        return mHandler;
    }
}
