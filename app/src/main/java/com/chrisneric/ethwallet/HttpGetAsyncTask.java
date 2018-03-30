package com.chrisneric.ethwallet;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpGetAsyncTask extends AsyncTask<String, Void, InputStream> {

    protected OnUpdateListener listener;

    public void setUpdateListener(OnUpdateListener listener) {
        this.listener = listener;
    }

    // This is a function that we are overriding from AsyncTask. It takes Strings as parameters because that is what we defined for the parameters of our async task
    @Override
    protected InputStream doInBackground(String... params) {
        HttpsURLConnection urlConnection = null;

        try {
            // get the url from the string passed in
            URL url = new URL(params[0]);
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("GET");

            int statusCode = urlConnection.getResponseCode();

            if (statusCode == 200) {
                return new BufferedInputStream(urlConnection.getInputStream());
            } else {
                // Status code is not 200
                // Do something to handle the error
            }

        } catch (Exception e) {
//            Log.d(TAG, e.getLocalizedMessage());
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(InputStream result) {
        if (listener != null) {
            listener.onUpdate(result);
        }
    }

    public interface OnUpdateListener {
        void onUpdate(InputStream obj);
    }
}
