package com.pyush.a013_lottie_animations;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.pyush.a013_lottie_animations.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LottieAnimationView checkAnimation;
        Button button;

        checkAnimation = findViewById(R.id.checkAnimation);
        checkAnimation.setAnimation(R.raw.check_animation);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnimation.playAnimation();
            }
        });
    }
}