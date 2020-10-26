package com.example.detour2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.lang.Thread.sleep;

public class IntroActivity extends AppCompatActivity {

    private TextView apName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        apName = (TextView) findViewById(R.id.apName);

        Typeface typeface = ResourcesCompat.getFont(this,R.font.blacklist);
        apName.setTypeface(typeface);
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.myanim);
        apName.setAnimation(anim);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(IntroActivity.this,LogInActivity.class);
                startActivity(intent);
                IntroActivity.this.finish();
            }
        }).start();
    }
}
