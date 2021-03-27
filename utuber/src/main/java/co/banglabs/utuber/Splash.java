package co.banglabs.utuber;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class Splash extends AppCompatActivity {

    Animation anim1, anim2, anim3;
    ImageView imageView1;
    TextView textview, textView2;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        try{
            Bundle extras = getIntent().getExtras();
            DataClass.utube_url = extras.getString(Intent.EXTRA_TEXT);
            DataClass.coppyed = true;
        }
        catch (Exception e){
            DataClass.coppyed = false;
            //Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }

        imageView1 = findViewById(R.id.iconid);
        textview = findViewById(R.id.textid);
        textView2 = findViewById(R.id.text2_id);
        //imageView2 = findViewById(R.id.splash_img2);

        /*RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(3000);
        rotate.setInterpolator(new LinearInterpolator());

        RotateAnimation rotate2 = new RotateAnimation(360, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate2.setDuration(3000);
        rotate2.setInterpolator(new LinearInterpolator());
*/

        
        anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.movable_anim);
        anim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.movable_anim);
        anim3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.movable_anim);

        imageView1.setAnimation(anim1);
        textview.setAnimation(anim2);
        textView2.setAnimation(anim3);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
                // Do something after 5s = 5000ms
                //buttons[inew][jnew].setBackgroundColor(Color.BLACK);
            }
        }, 1600);

        /*anim1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });*/


    }
}
