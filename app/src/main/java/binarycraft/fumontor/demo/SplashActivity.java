package binarycraft.fumontor.demo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import binarycraft.fumontor.demo.Utils.ApplicationUtility;

public class SplashActivity extends AppCompatActivity {

    Animation _translateAnimation;
    Button btnGetStarted;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        context = this;

        ApplicationUtility.printKeyHash(this);
        initUI();
        addClickListeners();
        startTranslateAnimation();
    }

    private void initUI(){
        btnGetStarted = (Button) findViewById(R.id.btnGetStarted);
    }

    private void addClickListeners(){
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lIntent = new Intent(context, LoginActivity.class);
                startActivity(lIntent);
            }
        });
    }

    private void startTranslateAnimation(){
        _translateAnimation = new TranslateAnimation(TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE, -300f, TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE, 0f);
        _translateAnimation.setDuration(4000);
        _translateAnimation.setRepeatCount(-1);
        _translateAnimation.setRepeatMode(Animation.REVERSE);
        _translateAnimation.setInterpolator(new LinearInterpolator());
        ImageView img = (ImageView) findViewById(R.id.image);
        img.startAnimation(_translateAnimation);
    }
}
