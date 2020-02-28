package com.example.volleyileyoutubedataapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    TextView textViewGelenVeri;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewGelenVeri=findViewById(R.id.textviewGelenVeri);
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        String url="https://www.googleapis.com/youtube/v3/activities?part=snippet%2CcontentDetails&channelId="+Api.CHANNEL_ID+"&maxResults=25&key="+Api.API_KEY;

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArrayItems=response.getJSONArray("items");
                    for(int i=0;i<jsonArrayItems.length();i++){
                        JSONObject jsonObject= jsonArrayItems.getJSONObject(i);
                        String videoId=jsonObject.getString("id");
                        JSONObject jsonObjectSnippet=jsonObject.getJSONObject("snippet");
                        String videoTitle=jsonObjectSnippet.getString("title");
                        String videoDescription=jsonObjectSnippet.getString("description");
                        JSONObject jsonObjectVideoThumb=jsonObjectSnippet.getJSONObject("thumbnails");
                        JSONObject jsonObjectVideoImage=jsonObjectVideoThumb.getJSONObject("default");
                        String videoImage=jsonObjectVideoImage.getString("url");

                        textViewGelenVeri.append("\nid:"+videoId+"\nTitle:"+videoTitle+"\nURL:"+videoImage);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("LogMesaj :",e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);





    }
}
