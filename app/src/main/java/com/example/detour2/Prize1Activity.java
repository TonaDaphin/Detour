 package com.example.detour2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

 public class Prize1Activity extends AppCompatActivity {
    private ImageView mCancelImg;
     private RelativeLayout relativeLayoutPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prize1);

        mCancelImg =(ImageView)findViewById(R.id.cancel);
        relativeLayoutPlay =(RelativeLayout) findViewById(R.id.relativeLayoutPlay);

        mCancelImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Prize1Activity.this, Category1Activity.class);
                startActivity(intent);
            }
        });

        relativeLayoutPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Prize1Activity.this, Category1Activity.class);
                startActivity(intent);
            }
        });
    }
}
