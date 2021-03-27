package co.banglabs.utuber;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseReference dr;
    public DrawerLayout dl;
    public ActionBarDrawerToggle t;
    public NavigationView nv;
    private AlertDialog.Builder adb;
    ClipboardManager myClipboard;
    EditText youtube_search_box;
    ImageButton youtube_url_pest_btn, youtube_search_btn;
    SharedPreferences sp;
    String value1 = "";

    TextView msg;
    private EditText text;
    /* LinearLayout subscriber_section;
    EditText subscriber_email;
    Button send_mail;*/

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dr = FirebaseDatabase.getInstance().getReference().child("user_mail");

        action_job();

        //navigation drawyer section in oncreate method
        dl = (DrawerLayout) findViewById(R.id.activity_main_dr);
        t = new ActionBarDrawerToggle(MainActivity.this, dl, R.string.Open, R.string.Close);
        nv = (NavigationView) findViewById(R.id.navigation_v);
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //getSupportActionBar().hide();
        getSupportActionBar().setHomeButtonEnabled(false);

        /*getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view =getSupportActionBar().getCustomView();*/


        //keyboard breaking problem
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        //For accessing the clickboare option
        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        /*msg = findViewById(R.id.msg_id);
        subscriber_section = findViewById(R.id.subscription_section_id);
        subscriber_email = findViewById(R.id.subs_email_id);
        send_mail = findViewById(R.id.send_mail_btn_id);
        send_mail.setOnClickListener(this);*/

        //Data for Recycler view or user option menu
        RecyclerView listview = findViewById(R.id.option_list);
        ArrayList<String> option_name = new ArrayList<String>(Arrays.asList("Tag", "Thumbnail", "Description", "Shortcuts"));
        ArrayList<Integer> option_logo = new ArrayList<Integer>(Arrays.asList(R.drawable.tag, R.drawable.thumbnail,
                R.drawable.description, R.drawable.shortcurt));

        //Top search box items control
        youtube_search_box = findViewById(R.id.youtube_search_box_id);
        youtube_url_pest_btn = findViewById(R.id.youtube_url_pest_btn_id);
        youtube_search_btn = findViewById(R.id.youtube_search_btn_id);


        youtube_search_box.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    youtube_search_box.setFocusableInTouchMode(true);
                    youtube_search_box.setFocusable(true);
                    youtube_search_box.requestFocus();


                    if (event.getRawX() >= (youtube_search_box.getRight() - youtube_search_box.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here

                        youtube_search_box.setText("");

                        //Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();

                        return true;
                    }
                }
                return false;
            }
        });


        //youtube_search_box.setFocusable(true);
        //youtube_search_box.requestFocus();

        youtube_url_pest_btn.setOnClickListener(this);
        youtube_search_btn.setOnClickListener(this);
        youtube_search_box.setOnClickListener(this);

        //Setting recyclerview
        //listview.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        // set a StaggeredGridLayoutManager with 3 number of columns and vertical orientation
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        listview.setLayoutManager(staggeredGridLayoutManager); // set LayoutManager to RecyclerView
        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, option_name, option_logo);
        listview.setAdapter(customAdapter);


        //getting every key stroke of the edit text of the activity and filter actions
        youtube_search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Toast.makeText(MainActivity.this, String.valueOf(i)+" "+String.valueOf(i1)+" "+String.valueOf(i2)+" ", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                DataClass.url = String.valueOf(charSequence);

                //Toast.makeText(MainActivity.this, DataClass.url, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.feedback_id:

                        Intent intent = new Intent(MainActivity.this, FeedbackActivity.class);
                        startActivity(intent);

                        //Toast.makeText(MainActivity.this, "My Account",Toast.LENGTH_SHORT).show();break;
                        break;

                    case R.id.aboutus_id:
                        Intent intent2 = new Intent(MainActivity.this, AboutUs.class);
                        startActivity(intent2);
                        //Toast.makeText(MainActivity.this, "Settings",Toast.LENGTH_SHORT).show();break;
                        break;

                    case R.id.manual_id:
                        Intent intent3 = new Intent(MainActivity.this, UserManual.class);
                        startActivity(intent3);
                        //Toast.makeText(MainActivity.this, "Settings",Toast.LENGTH_SHORT).show();break;
                        break;

                    case R.id.share_id:
                        /*Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        String subject = "Youtuber tool";
                        String body = "This app is about";
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                        shareIntent.putExtra(Intent.EXTRA_TEXT, body);
                        startActivity(Intent.createChooser(shareIntent, "Share"));
                        //Toast.makeText(MainActivity.this, "My Cart",Toast.LENGTH_SHORT).show();break;*/


                        try {
                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.setType("text/plain");
                            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                            String shareMessage = "";
                            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                            startActivity(Intent.createChooser(shareIntent, "choose one"));
                        } catch (Exception e) {
                            //e.toString();
                        }


                        break;
                    default:
                }
                return true;
            }
        });

        try {
            if (DataClass.coppyed && !DataClass.utube_url.isEmpty()) {
                DataClass.coppyed = false;
                finish();
                //Log.d("utubeurl", DataClass.utube_url);
            }
        } catch (Exception e) {


        }

    }

    //listener for clicking navigation drawyer
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)

    @Override
    public void onClick(View view) {
        String user_inputstr, url = null;
        boolean gotoweb = false;
        String first_section = "https://www.youtube.com/results?search_query=";
        if (view.getId() == R.id.youtube_search_btn_id) {
            //Log.d("error2", "found");
            try {
                if (check()) {
                    user_inputstr = youtube_search_box.getText().toString();
                    if (user_inputstr.equals("youtube.com")) {
                        gotoweb = true;
                        //url = user_inputstr;
                    }
                    //need to check the condition again
                    else if (user_inputstr.contains(".com/") && !user_inputstr.contains("youtube")) {

                        Toast.makeText(this, "The url is not belongs to youtube", Toast.LENGTH_SHORT).show();
                    } else if (user_inputstr.isEmpty()) {
                        Toast.makeText(this, "Nothing to search", Toast.LENGTH_SHORT).show();
                    } else {
                        gotoweb = true;
                        url = first_section + user_inputstr;
                    }
                    if (gotoweb) {
                        /*Intent intent = new Intent(MainActivity.this, YoutubeView.class);
                        intent.putExtra("input", url);
                        startActivity(intent);
                        Log.d("error2", "found");*/
                        String query = youtube_search_box.getText().toString();

                        if (query != null) {

                            Intent intent = new Intent(Intent.ACTION_SEARCH);
                            intent.setPackage("com.google.android.youtube");
                            intent.putExtra("query", query);
                            //intent.putExtra("trending","") ;
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);

                        }
                    }
                }
            } catch (Exception e) {
                //Toast.makeText(this, "Check your internet Connection", Toast.LENGTH_SHORT).show();
            }

        } else if (view.getId() == R.id.youtube_url_pest_btn_id) {


            try {
                ClipData abc = myClipboard.getPrimaryClip();
                ClipData.Item item = abc.getItemAt(0);
                String text = item.getText().toString();
                youtube_search_box.setText(text);


                //cursor
                EditText editText = findViewById(R.id.youtube_search_box_id);
                editText.setSelection(editText.getText().length()); // End point Cursor


            } catch (Exception e) {
                Toast.makeText(this, "clipboard is empty", Toast.LENGTH_SHORT).show();
            }
        }

        /*else if(view.getId()==R.id.send_mail_btn_id){

            try{
                String user_email = subscriber_email.getText().toString();
                if (user_email.contains(".com")&& user_email.contains("@")){
                    msg.setVisibility(GONE);
                    subscriber_section.setVisibility(GONE);
                }
                //need to check the condition again
                else{
                    subscriber_email.setError("wrong email format");
                }
            }
            catch (Exception e){
                Toast.makeText(this, "email is empty", Toast.LENGTH_SHORT).show();
            }
        }*/
    }

    @Override
    public void onBackPressed() {

        sp = getSharedPreferences("user_mail_data", MODE_PRIVATE);


        if (sp.contains("data")) {

            adb = new AlertDialog.Builder((MainActivity.this));
            adb.setTitle("Confirm to Exit");
            adb.setCancelable(false);
            adb.setIcon(R.drawable.android_white);
            adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    exit();
                }
            });
            adb.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog alertDialog = adb.create();
            alertDialog.show();
        } else {

            final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.email_custom_dialog, null);

            final EditText email = (EditText) dialogView.findViewById(R.id.email_box_id);
            Button submit = (Button) dialogView.findViewById(R.id.email_send_id);
            Button exit = (Button) dialogView.findViewById(R.id.email_exit_id);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String user_mail = email.getText().toString();
                    if (!user_mail.isEmpty() && user_mail.contains(".com") && user_mail.contains("@")) {

                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("data", "subscribed");
                        editor.commit();

                        //database connection

                        dr.push().setValue(user_mail);

                        Toast.makeText(MainActivity.this, "Thank you for Subscribe", Toast.LENGTH_SHORT).show();

                        dialogBuilder.dismiss();
                        finish();
                    } else {

                        email.setError("Check the Email and try again");
                    }
                }
            });
            exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // DO SOMETHINGS
                    finish();
                }
            });

            dialogBuilder.setView(dialogView);
            dialogBuilder.show();


            /*submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String user_mail = email.getText().toString();
                    if(!user_mail.isEmpty() && user_mail.contains(".com") && user_mail.contains("@")){

                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("data", "subscribed");
                        editor.commit();

                        Toast.makeText(MainActivity.this, "Email send", Toast.LENGTH_SHORT).show();
                        alertDialog.cancel();

                    }
                    else{

                        email.setError("Check the Email and try again");
                    }


                }
            });*/


        }

    }

    void exit() {
        super.onBackPressed();
    }

    boolean check() {

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        boolean isWiFi = nInfo.getType() == ConnectivityManager.TYPE_WIFI;
        boolean isCelluler = nInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        if ((isWiFi || isCelluler)) {
            return true;
        } else {
            return false;
        }
    }


    private void action_job() {

        //custom actionbar color
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#F9F9F9"));
        ColorDrawable colorDrawablep = new ColorDrawable(Color.parseColor("#FF0000"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setLogo(R.drawable.android_red);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Tube Optimizer");
        getSupportActionBar().setElevation(0);


    }

    @Override
    protected void onRestart() {
        super.onRestart();

        try {
            if (!DataClass.utube_url.isEmpty()) {
                youtube_search_box.setText(DataClass.utube_url);
                DataClass.utube_url = "";
                Log.d("utubeurl", DataClass.utube_url);
            }
        } catch (Exception e) {


        }
        //Toast.makeText(this, "restart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Toast.makeText(this, "pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Toast.makeText(this, "destroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //Toast.makeText(this, "create", Toast.LENGTH_SHORT).show();
    }
}