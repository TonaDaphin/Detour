package com.example.detour2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Level1Activity extends AppCompatActivity {
    private LinearLayout layoutDjoser;
    private LinearLayout clueArch100;
    private LinearLayout passLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);

        layoutDjoser = (LinearLayout)findViewById(R.id.layoutDjoser);
        clueArch100 = (LinearLayout)findViewById(R.id.clueArch100);
        passLayout = (LinearLayout)findViewById(R.id.passLayout);

        layoutDjoser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Level1Activity.this, AdvertActivity.class);
                        startActivity(intent);
            }
        });

        clueArch100.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Level1Activity.this, ClueActivity.class);
                startActivity(intent);
            }
        });

        passLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Level1Activity.this, Category1Activity.class);
                startActivity(intent);
            }
        });

    }
}
