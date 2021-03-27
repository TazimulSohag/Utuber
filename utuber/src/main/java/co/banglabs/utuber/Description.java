package co.banglabs.utuber;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Description extends AppCompatActivity implements View.OnClickListener {

    ClipboardManager myClipboard;
    EditText des_search_box;
    ImageButton des_url_pest_btn, des_search_btn;
    Bundle derived_url_bundle;
    String derived_url;
    EditText video_description;
    Button copy_btn;
    boolean single = true;
    ProgressDialog progressDialog;

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        progressDialog = new ProgressDialog(Description.this);

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        //For accessing the clickboare option
        myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

        //Action Bar Logo
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Description");
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        copy_btn = findViewById(R.id.des_copy_btn_id);
        video_description = findViewById(R.id.video_description_id);
        //returnDescription();

        //Top search box items control
        des_search_box = findViewById(R.id.user_des_search_box_id);
        des_url_pest_btn = findViewById(R.id.des_url_pest_btn_id);
        des_search_btn = findViewById(R.id.des_search_btn_id);

        copy_btn.setOnClickListener(this);
        des_url_pest_btn.setOnClickListener(this);
        des_search_box.setOnClickListener(this);
        des_search_btn.setOnClickListener(this);
        video_description.setOnClickListener(this);

        //getting and setting the derived url to the search box
        try{
            derived_url_bundle = getIntent().getExtras();
            if(derived_url_bundle.containsKey("url")){
                derived_url = derived_url_bundle.getString("url", "");
                //Toast.makeText(this, derived_url, Toast.LENGTH_SHORT).show();
                des_search_box.setText(derived_url);
                des_search_btn.callOnClick();
            }
        }
        catch (Exception e){

        }

        //getting every key stroke of the edit text of the activity and filter actions
        des_search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(i==0){
                  //  clear_btn.setVisibility(GONE);
                }
                else{
               //     clear_btn.setVisibility(VISIBLE);
                }
                DataClass.available = false;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                DataClass.url = String.valueOf(charSequence);
                des_search_box.setError(null);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        des_search_box.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_UP) {

                    des_search_box.setFocusableInTouchMode(true);
                    des_search_box.setFocusable(true);
                    des_search_box.requestFocus();

                    if(event.getRawX() >= (des_search_box.getRight() - des_search_box.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here

                        des_search_box.setText("");

                        return true;
                    }
                }
                return false;
            }
        });


        //Bottom Navigation Section
        BottomNavigationView btnv = findViewById(R.id.bottom_navigation);
        btnv.setSelectedItemId(R.id.description_item);
        btnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent;
                switch (menuItem.getItemId()){

                    case R.id.tag_item:
                        intent = new Intent(Description.this, Tag.class);

                        //(problem) need to reduce
                        if(!DataClass.url.equals(null) && (DataClass.url.contains("www.youtube.com") || DataClass.url.contains("youtu.be") )&& !DataClass.url.contains(" ")){
                            intent.putExtra("url", DataClass.url);
                        }

                        startActivity(intent);
                        finish();
                        break;

                    case R.id.title_item:
                        intent = new Intent(Description.this, Title.class);

                        //(problem) need to reduce
                        if(!DataClass.url.equals(null) && (DataClass.url.contains("www.youtube.com") || DataClass.url.contains("youtu.be") )&& !DataClass.url.contains(" ")){
                            intent.putExtra("url", DataClass.url);
                        }

                        startActivity(intent);
                        finish();
                        break;

                    case R.id.description_item:
                        break;
                }

                return false;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.des_url_pest_btn_id){

            try{
                ClipData abc = myClipboard.getPrimaryClip();
                ClipData.Item item = abc.getItemAt(0);
                String text = item.getText().toString();
                des_search_box.setText(des_search_box.getText().toString()+text);



                //cursor

                EditText editText = findViewById(R.id.user_des_search_box_id);
                editText.setSelection(editText.getText().length()); // End point Cursor



            }
            catch (Exception e){
                Toast.makeText(this, "clipboard is empty", Toast.LENGTH_SHORT).show();
            }


        }
        else if(view.getId()==R.id.video_description_id){

            video_description.setFocusableInTouchMode(true);
            video_description.setFocusable(true);
            video_description.requestFocus();

            /*if(single){
                video_description.callOnClick();
                single = false;
            }*/

        }
        else if(view.getId()==R.id.des_copy_btn_id){

            String description;
            description = video_description.getText().toString();
            if(description.isEmpty()){
                Toast.makeText(this, "There are nothing to copy", Toast.LENGTH_SHORT).show();
            }
            else{
                ClipData myClip;
                myClip = ClipData.newPlainText("description", description);
                myClipboard.setPrimaryClip(myClip);
                Toast.makeText(this, "Description copyed to clpboard", Toast.LENGTH_SHORT).show();
            }



        }
        if(view.getId()==R.id.des_search_btn_id){

            try{
                if(DataClass.check(getApplicationContext())){


                    if(DataClass.chk_rating_show(Description.this)){

                        DataClass.show_rating_bar(Description.this, Description.this);

                    }
                    else{
                        String input = des_search_box.getText().toString();
                        if(input.isEmpty()){
                            //des_search_box.setError("Enter url");
                            Toast.makeText(this, "Nothing for search", Toast.LENGTH_SHORT).show();
                        }
                        else if(input.contains("youtube.com") || input.contains("youtu.be")){
                            progressDialog.setMessage("Extracting tags....");
                            progressDialog.show();
                            String vid_id = DataClass.urlFilter(input);
                            //title_search_box.setText(vid_id);
                            String url = DataClass.first_Section+vid_id+DataClass.second_Section+DataClass.console_key1+DataClass.third_Section_description;
                            //String url2 = DataClass.first_Section+vid_id+DataClass.second_Section+DataClass.console_key1+DataClass.third_Section_thumb2;
                            //Log.d("error4", url);
                            returnDescription(url);
                            DataClass.rating_counter++;
                            //loadImage(url2);
                            //Log.d("error4", "Key: "+temp);
                        }
                        else if(input.contains(".com") && !input.contains("youtube")){
                            Toast.makeText(this, "Non Youtube content", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            //goto yourube
                            Log.d("error4", "Go to yoube");
                        }
                    }

                }
            }
            catch (Exception e){
                //Toast.makeText(this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            }

        }

    }

    void returnDescription(String url){
        
        try{
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String getUrl = url;
            //des_search_box.setText(getUrl);
            StringRequest getRequest = new StringRequest(Request.Method.GET, getUrl, new Response.Listener<String>() {
                @Override
                public void onResponse (String response) {

                    String titleout = response;
                    titleout = titleout.replace("{\n" +
                            "  \"items\": [\n" +
                            "    {\n" +
                            "      \"snippet\": {\n" +
                            "        \"description\": \"", "");
                    titleout = titleout.replace("\"\n" +
                            "      }\n" +
                            "    }\n" +
                            "  ]\n" +
                            "}", "");
                    titleout = titleout.replace("\\n", "\n");
                    //titleout = titleout.replace("\n", );

                    //String first = "pulok";
                    //String second = first.replaceAll("p", "");

                    //Toast.makeText(Description.this, String.valueOf(titleout), Toast.LENGTH_SHORT).show();
                    video_description.setText(String.valueOf(titleout));
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse (VolleyError error) {
                    //Log.v(TAG, "Volley GET error: " + error.getMessage());

                    returnDescription(getUrl.replace(DataClass.console_key1, DataClass.console_key2));
                    if(progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }

                    //Toast.makeText(Description.this, "error", Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(getRequest);
            progressDialog.dismiss();
        }
        catch (Exception e){
            Toast.makeText(this, "error on description data reading", Toast.LENGTH_SHORT).show();
        }

    }

}
