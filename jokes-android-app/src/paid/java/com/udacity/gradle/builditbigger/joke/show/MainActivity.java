package com.udacity.gradle.builditbigger.joke.show;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.udacity.gradle.builditbigger.joke.android.JokeActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.get_joke_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JokeFetchTask(MainActivity.this, new JokeFetchTask.OnJokeFetchedListener() {
                    @Override
                    public void onJokeFetched(String joke) {
                        startActivity(new Intent(MainActivity.this, JokeActivity.class)
                                .putExtra(JokeActivity.INTENT_EXTRA_JOKE, joke));
                    }
                }).execute(getString(R.string.app_name), getString(R.string.root_url));
            }
        });
    }
}
