package com.eaglebjkbv.yandexmapkit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

public class MainActivity extends AppCompatActivity {
    private MapView mapview;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MapKitFactory.setApiKey(MyApi.API_KEY);
        MapKitFactory.initialize(context);
        setContentView(R.layout.activity_main);
        mapview = findViewById (R. id.mapview);
        mapview.getMap().move(new CameraPosition(new Point(55.751574,37.573856), 11.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH,0),null);
    }
    @Override
    protected void onStop() {
        super.onStop();
        mapview.onStop();
        MapKitFactory.getInstance().onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapview.onStart();
        MapKitFactory.getInstance().onStart();
    }
}
