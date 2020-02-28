package com.example.youtubechannelvideolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context context=this;
    ArrayList<ModelVideo> modelVideoArrayList=new ArrayList<>();
    ModelVideo myVideo;
    VideoListAdapter adapter;
    ListView listViewVideoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewVideoList=findViewById(R.id.listviewVideoList);


        RequestQueue requestQueue=Volley.newRequestQueue(context);
        String url=MyApi.getUrl();



        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArrayItems=response.getJSONArray("items");
                    for(int i=0;i<jsonArrayItems.length();i++){
                        JSONObject jsonObject=jsonArrayItems.getJSONObject(i);

                        JSONObject jsonObjectSnippet=jsonObject.getJSONObject("snippet");
                        String videoTitle=jsonObjectSnippet.getString("title");
                        String videoDescription=jsonObjectSnippet.getString("description");
                        String videoPublishedAt=jsonObjectSnippet.getString("publishedAt");
                        JSONObject jsonObjectObjectVideoThumb=jsonObjectSnippet.getJSONObject("thumbnails");
                        JSONObject jsonObjectObjectVideoImageUrl=jsonObjectObjectVideoThumb.getJSONObject("default");
                        String videoThumbUrl=jsonObjectObjectVideoImageUrl.getString("url");
                        JSONObject jsonObjectContentDetails=jsonObject.getJSONObject("contentDetails");
                        JSONObject jsonObjectUpload=jsonObjectContentDetails.getJSONObject("upload");
                        String videoId=jsonObjectUpload.getString("videoId");
                        modelVideoArrayList.add(new ModelVideo(videoId,videoTitle,videoDescription,videoPublishedAt,videoThumbUrl));
                    }
                    adapter=new VideoListAdapter(context,modelVideoArrayList);
                    listViewVideoList.setAdapter(adapter);

                } catch (JSONException e) {
                    Toast.makeText(context, "Try Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "OnError : "+error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);

        listViewVideoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ModelVideo modelVideo=(ModelVideo)adapter.getItem(position);
                String vId=modelVideo.getVideoId();
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + vId));
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=" + vId));
                try {
                    context.startActivity(appIntent);
                } catch (ActivityNotFoundException ex) {
                    context.startActivity(webIntent);
                }

                //Toast.makeText(context, modelVideo.getVideoId(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
