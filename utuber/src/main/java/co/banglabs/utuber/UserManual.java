package co.banglabs.utuber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.Arrays;


public class UserManual extends AppCompatActivity {

    StepView stepView;
    ImageView next, previous;
    ImageView home;
    static Integer step = 1;
    ImageView imageView;
    static int switch_btn_memory=0;


    @Override
    protected void onPause() {

        step = 1;
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manual);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        final ArrayList<Integer> option_logo = new ArrayList<Integer>(Arrays.asList(R.drawable.one,  R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six));


        stepView = findViewById(R.id.step_view);
        imageView = findViewById(R.id.image_id);
        home = findViewById(R.id.home_id);
        imageView.setImageResource(option_logo.get(0));

        next = findViewById(R.id.next_id);
        previous = findViewById(R.id.previous_id);
        //stepView.done(true);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_effect);

                previous.startAnimation(animFadein);

                if (switch_btn_memory == 1) step--;

                /*if(step == 1){
                    Intent intent = new Intent(UserManual.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }*/
                if(step>=1){
                    step--;
                    //Toast.makeText(UserManual.this, String.valueOf(step), Toast.LENGTH_SHORT).show();
                    stepView.go(step, true);
                    Log.d("stepsize", String.valueOf(step));

                    imageView.setImageResource(option_logo.get(step));
                }

                switch_btn_memory = -1;
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_effect);

                next.startAnimation(animFadein);

                if (switch_btn_memory == -1)    step++;

                if(step==6){
                    finish();
                }

                if(step<6 && step>=1){
                    stepView.go(step, true);
                    Log.d("stepsize", String.valueOf(step));

                    imageView.setImageResource(option_logo.get(step));

                    step++;
                    //Toast.makeText(UserManual.this, String.valueOf(step), Toast.LENGTH_SHORT).show();
                }
                switch_btn_memory = 1;
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        stepView.setOnStepClickListener(new StepView.OnStepClickListener() {
            @Override
            public void onStepClick(int step) {

                Toast.makeText(UserManual.this, "click", Toast.LENGTH_SHORT).show();
            }
        });



    }
}
