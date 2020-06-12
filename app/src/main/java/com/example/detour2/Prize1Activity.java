 package com.example.detour2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

 public class Prize1Activity extends AppCompatActivity {
    private ImageView mCancelImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prize1);

        mCancelImg =(ImageView)findViewById(R.id.cancel);

        mCancelImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Prize1Activity.this, Category1Activity.class);
                startActivity(intent);
            }
        });
    }
}
