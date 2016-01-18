package com.udacity.gradle.builditbigger.joke.show;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.joke.teller.jokeApi.JokeApi;

import java.io.IOException;

/**
 * Developer: chipset
 * Package : com.udacity.gradle.builditbigger.joke.android
 * Project : ProjectFour
 * Date : 17/1/16
 */
public class JokeFetchTestTask extends AsyncTask<String, Void, String> {
    private OnJokeFetchedListener listener;
    private JokeApi jokeApi;

    public JokeFetchTestTask(OnJokeFetchedListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        if (jokeApi == null) {
            jokeApi = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setApplicationName(params[0])
                    .setRootUrl(params[1])
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    })
                    .build();
        }
        try {
            return jokeApi.jokeMe().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        listener.onJokeFetched(joke);
    }

    public interface OnJokeFetchedListener {
        void onJokeFetched(String joke);
    }
}

