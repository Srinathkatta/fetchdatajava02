package org.example;



import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        URL url = null;
        String geturl = "https://api.zippopotam.us/us/33162";
        HttpURLConnection connection = null;
        int responseCode = 0;
        try {
            url = new URL(geturl);
            connection = (HttpURLConnection) url.openConnection();
            responseCode = connection.getResponseCode();
        } catch (Exception e) {
            System.out.println("URL connection problem");
        }
        if(responseCode == 200){
            BufferedReader in = null;
            StringBuilder apiData = new StringBuilder();
            String readline = null;
            try {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            while(true){
                try {
                    if (!((readline = in.readLine()) != null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                apiData.append(readline);
            }
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            JSONObject jsonObject = new JSONObject(apiData.toString());

            System.out.println(jsonObject.get("post code"));
            System.out.println(jsonObject.get("country"));
            System.out.println(jsonObject.get("country abbreviation"));
            System.out.println(jsonObject.get("places"));
        }
        else
            System.out.println("API call could not be made!!!");
    }
}