package com.udacity.gradle.builditbigger.joke.show;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.joke.android.JokeActivity;


public class MainActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));

        mAdView = (AdView) findViewById(R.id.adView);

        findViewById(R.id.get_joke_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    execute();
                }
            }
        });

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                mInterstitialAd.loadAd(requestNewAdd());
                execute();
            }
        });
    }

    private AdRequest requestNewAdd() {
        return new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("5BAE3B06309154461850E81BBBC0CB74")
                .build();
    }

    private void execute() {
        new JokeFetchTask(MainActivity.this, new JokeFetchTask.OnJokeFetchedListener() {
            @Override
            public void onJokeFetched(String joke) {
                startActivity(new Intent(MainActivity.this, JokeActivity.class)
                        .putExtra(JokeActivity.INTENT_EXTRA_JOKE, joke));
            }
        }).execute(getString(R.string.app_name), getString(R.string.root_url));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdView.loadAd(requestNewAdd());
        mInterstitialAd.loadAd(requestNewAdd());
    }
}
