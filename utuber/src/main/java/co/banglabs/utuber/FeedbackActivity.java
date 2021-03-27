package co.banglabs.utuber;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {
    EditText email, revew;
    Button clear, send;
    DatabaseReference dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        dr = FirebaseDatabase.getInstance().getReference();


        //Action Bar Logo
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.android_white);
        getSupportActionBar().setTitle("Feedback");
        //getSupportActionBar().setDisplayUseLogoEnabled(true);

        email = findViewById(R.id.feedback_email_id);
        revew = findViewById(R.id.user_revew_box_id);

        clear = findViewById(R.id.clear_btn_id);
        send = findViewById(R.id.send_btn_id);

        clear.setOnClickListener(this);
        send.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.clear_btn_id) {

            email.setText("");
            revew.setText("");

        } else if (view.getId() == R.id.send_btn_id) {
            try {
                //Intent intent = new Intent(Intent.ACTION_SEND);
                /*intent.setType("text/email");
                intent.putExtra(intent.EXTRA_EMAIL, new String[]{"banglabs2020@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback from Utuber");
                intent.putExtra(Intent.EXTRA_TEXT, "Email: "+ "\n Message: "+revew.getText().toString());
                startActivity(Intent.createChooser(intent, "feedback with"));*/

                if (!email.getText().toString().isEmpty() && email.getText().toString().contains("@") && email.getText().toString().toLowerCase().contains(".com") && !revew.getText().toString().isEmpty()) {

                    if (DataClass.check(FeedbackActivity.this)) {
                        dr.push().setValue(email.getText().toString() + " : " + revew.getText().toString());
                        Toast.makeText(this, "Thank you for your oppnion", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(this, "Please fill all the information correctly", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {

            }
        }
    }
}
