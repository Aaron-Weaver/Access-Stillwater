package com.teaman.data.rest;

import android.os.AsyncTask;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Dilancuan on 3/16/2016.
 */
public class OkHttpHandler extends AsyncTask<String, Void, byte[]> {

    OkHttpClient client = new OkHttpClient();

    @Override
    protected byte[] doInBackground(String... params) {

        Request.Builder builder = new Request.Builder();
        builder.url(params[0]);

        Request request = builder.build();

        try {

            Response response = client.newCall(request).execute();
            return response.body().bytes();

        } catch (Exception e) {
            Log.d("OkHttpHandler", e.getMessage());
        }

        return null;
    }
}
