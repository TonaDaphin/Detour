package com.example.detour2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Category1Activity extends AppCompatActivity {
    private TextView arch100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category1);

        arch100 = (TextView) findViewById(R.id.arch100);

        arch100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category1Activity.this, Level1Activity.class);
                startActivity(intent);
            }
        });
    }
}
