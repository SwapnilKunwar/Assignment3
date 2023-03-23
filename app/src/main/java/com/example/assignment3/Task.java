package com.example.assignment3;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Task extends AsyncTask<Void , Void ,String > {

    private TextView mResponseTextView;
    public String ss;
    public String gg;

    public  Task(String l , String s1) {
        ss=s1;
        gg=l;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String response = "";
        try {
            URL url = new URL("https://api.dictionaryapi.dev/api/v2/entries/en/"+ss);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            response = convertStreamToString(in);
            Log.d("response" , response);
        } catch (Exception e) {
            e.printStackTrace();
            response="nulle";
        }
        return response;

    }

    private String convertStreamToString(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String response) {
        // handle the API response here in the UI thread
        Log.d("Tag" , response);
        gg= gg + (response);
    }

}
