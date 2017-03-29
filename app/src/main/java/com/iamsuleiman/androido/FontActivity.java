package com.iamsuleiman.androido;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FontActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font);

        TextView mTextIntro = (TextView) findViewById(R.id.font_txt_intro);

        Typeface typefaceLato = getResources().getFont(R.font.lato_bold);
        mTextIntro.setTypeface(typefaceLato, Typeface.ITALIC);
    }
}
