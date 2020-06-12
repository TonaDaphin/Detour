package com.example.detour2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Category1Activity extends AppCompatActivity {
    private RelativeLayout mQuestionChangeRel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category1);

        mQuestionChangeRel = (RelativeLayout) findViewById(R.id.qtn_change);

        mQuestionChangeRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category1Activity.this, Level1Activity.class);
                startActivity(intent);
            }
        });
    }
}
