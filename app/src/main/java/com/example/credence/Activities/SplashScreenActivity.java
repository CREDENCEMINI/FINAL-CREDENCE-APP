package com.example.credence.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.credence.R;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_srceen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.splashpage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            VideoView splashvid = findViewById(R.id.splashvid);
            splashvid.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.splashvid);
            splashvid.start();

            return insets;
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, LoginSignupPage.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME);
    }
}