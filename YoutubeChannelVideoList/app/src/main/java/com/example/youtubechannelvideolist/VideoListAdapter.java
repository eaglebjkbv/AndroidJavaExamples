package com.example.youtubechannelvideolist;

import android.content.Context;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VideoListAdapter extends BaseAdapter {

    Context context;
    ArrayList<ModelVideo> modelVideoArrayList;

    public VideoListAdapter(Context context, ArrayList<ModelVideo> modelVideoArrayList) {
        this.context = context;
        this.modelVideoArrayList = modelVideoArrayList;
    }

    @Override
    public int getCount() {
        return modelVideoArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelVideoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v= LayoutInflater.from(context).inflate(R.layout.layout_video_listview_item,parent,false);

        TextView textViewVideoTitle=v.findViewById(R.id.textViewVideoTitle);
        TextView textViewVideoDescription=v.findViewById(R.id.textViewVideoDescription);
        TextView textViewVideoPublishedAt=v.findViewById(R.id.textViewVideoPublishedAt);
        ImageView imageviewVideoThumb=v.findViewById(R.id.imageViewVideoThumb);

        textViewVideoTitle.setText(modelVideoArrayList.get(position).getVideoTitle());
        textViewVideoDescription.setText(modelVideoArrayList.get(position).getVideoDescription());
        textViewVideoPublishedAt.setText(modelVideoArrayList.get(position).getVideoPublishedAt());

        Picasso.get().load(modelVideoArrayList.get(position).getVideoThumbnailUrl()).into(imageviewVideoThumb);




        return v;
    }
}
