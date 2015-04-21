package com.longtailapps.goslate.rest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Requests {

    private static final int TIMEOUT = 1000;

    public static String sendPostJson(String url, String urlParameters) throws Exception{

        URL obj=new URL(url);
        HttpURLConnection con=(HttpURLConnection)obj.openConnection();
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod("POST");
        con.setConnectTimeout(TIMEOUT);
        con.setDoInput(true);
        DataOutputStream wr=new DataOutputStream(con.getOutputStream());
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
        writer.write(urlParameters);
        writer.close();
        wr.flush();
        wr.close();
        int responseCode=con.getResponseCode();
        if(!(responseCode==200 || responseCode==204)){
            throw new Exception("Server not responses");
        }
        BufferedReader in=new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response=new StringBuffer();
        while ((inputLine=in.readLine())!=null) {
            response.append(inputLine);

        }
        in.close();
        return response.toString();
    }

}
