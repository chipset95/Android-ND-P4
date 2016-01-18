package com.udacity.gradle.builditbigger.joke.show;

import android.app.ProgressDialog;
import android.content.Context;
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
public class JokeFetchTask extends AsyncTask<Void, Void, String> {
    private ProgressDialog dialog;
    private OnJokeFetchedListener listener;
    private JokeApi jokeApi;

    public JokeFetchTask(Context context, OnJokeFetchedListener listener) {
        dialog = new ProgressDialog(context);
        dialog.setMessage("Please Wait");
        dialog.setCancelable(true);
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (!dialog.isShowing())
            dialog.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        if (jokeApi == null) {
            jokeApi = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.3.2:8080/_ah/api/")
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
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        listener.onJokeFetched(joke);
        if (dialog.isShowing())
            dialog.hide();
    }

    public interface OnJokeFetchedListener {
        void onJokeFetched(String joke);
    }
}

