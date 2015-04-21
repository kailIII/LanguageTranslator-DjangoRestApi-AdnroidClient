package com.longtailapps.goslate.rest;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


public class Translate extends AsyncTask<String, String, String> {

    private String translated =null;
    private TextView textView;
    public Translate(TextView textView){
        this.textView = textView;
    }

    @Override
    protected String doInBackground(String... params) {

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("original", params[1]);
            jsonObject.put("language", params[2]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String response = null;
        try {
            response = Requests.sendPostJson(params[0], jsonObject.toString());
            JSONObject jsonResponse=new JSONObject(response);
            translated = jsonResponse.getString("translated");

        } catch (Exception e) {
            e.printStackTrace();
            response = "error";
        }
        Log.d("response", response);

        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        textView.setText(translated);
    }
}
