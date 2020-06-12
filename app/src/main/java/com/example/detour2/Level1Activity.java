package com.example.detour2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Level1Activity extends AppCompatActivity {
    private LinearLayout mValyeLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);

        mValyeLinear = (LinearLayout)findViewById(R.id.valye);

        mValyeLinear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Level1Activity.this, Prize1Activity.class);
                        startActivity(intent);
            }
        });

    }
}
