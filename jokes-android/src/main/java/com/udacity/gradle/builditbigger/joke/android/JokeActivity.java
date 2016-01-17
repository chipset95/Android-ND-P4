package com.udacity.gradle.builditbigger.joke.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String INTENT_EXTRA_JOKE = "the_joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        ((TextView) findViewById(R.id.joke_text_view)).setText(getIntent().getStringExtra(INTENT_EXTRA_JOKE));
    }
}
