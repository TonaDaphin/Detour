package com.example.detour2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdvertActivity extends AppCompatActivity {
    private ImageView cancelImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert);

        cancelImg = (ImageView) findViewById(R.id.cancel);

        cancelImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AdvertActivity.this, Category1Activity.class);
                startActivity(intent);
            }
        });

    }
}
