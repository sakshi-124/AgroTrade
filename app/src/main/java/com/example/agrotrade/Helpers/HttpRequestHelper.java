package com.example.agrotrade.Helpers;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class HttpRequestHelper {
    static InputStream is = null;
    static String json = "";

    public static final String BASE_URL = "http://service.techfusiontechnologies.com/agrofarm/console/service";
    //public static final String BASE_URL = "http://192.168.43.59/android/";
    //public static final String BASE_URL = "http://192.168.0.132/project/service";



    public String postRequest(String url, List<NameValuePair> params) {
        try {
            Log.d("==get request url==", BASE_URL + url);

            HttpPost httpPost = new HttpPost(BASE_URL + url);
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            HttpResponse httpResponse = new DefaultHttpClient().execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        Log.d("===json====", "" + json);
        return json;
    }

    public String getRequest(String url, List<NameValuePair> params) {
        try {
            Log.d("==post request url==", BASE_URL + url);

            HttpGet httpGet = new HttpGet(BASE_URL + url);
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Error inside getRequest", e.toString());
        }
        return json;
    }
}
