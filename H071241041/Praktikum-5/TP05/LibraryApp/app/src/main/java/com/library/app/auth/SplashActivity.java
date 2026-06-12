package com.library.app.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.library.app.MainActivity;
import com.library.app.R;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 2800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Apply dark mode before setContentView
        SharedPreferences prefs = getSharedPreferences("LibraryPrefs", MODE_PRIVATE);
        boolean isDarkMode = prefs.getBoolean("dark_mode", false);
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView ivLogo = findViewById(R.id.iv_splash_logo);
        TextView tvTitle = findViewById(R.id.tv_splash_title);
        TextView tvSubtitle = findViewById(R.id.tv_splash_subtitle);

        // Animate logo
        ScaleAnimation scaleAnim = new ScaleAnimation(
            0.5f, 1.0f, 0.5f, 1.0f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnim.setDuration(800);

        AlphaAnimation fadeAnim = new AlphaAnimation(0f, 1f);
        fadeAnim.setDuration(800);

        AnimationSet logoAnim = new AnimationSet(true);
        logoAnim.addAnimation(scaleAnim);
        logoAnim.addAnimation(fadeAnim);
        ivLogo.startAnimation(logoAnim);

        // Animate title with delay
        AlphaAnimation titleFade = new AlphaAnimation(0f, 1f);
        titleFade.setDuration(600);
        titleFade.setStartOffset(500);
        titleFade.setFillAfter(true);
        tvTitle.startAnimation(titleFade);

        AlphaAnimation subtitleFade = new AlphaAnimation(0f, 1f);
        subtitleFade.setDuration(600);
        subtitleFade.setStartOffset(800);
        subtitleFade.setFillAfter(true);
        tvSubtitle.startAnimation(subtitleFade);

        new Handler().postDelayed(() -> {
            boolean isLoggedIn = prefs.getBoolean("is_logged_in", false);
            Intent intent;
            if (isLoggedIn) {
                intent = new Intent(SplashActivity.this, MainActivity.class);
            } else {
                intent = new Intent(SplashActivity.this, LoginActivity.class);
            }
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, SPLASH_DELAY);
    }
}
