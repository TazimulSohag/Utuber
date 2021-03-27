package co.banglabs.utuber;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.io.File;



public class Title extends AppCompatActivity implements View.OnClickListener {

    ClipboardManager myClipboard;
    EditText title_search_box;
    ImageButton title_url_pest_btn, title_search_btn;
    String[] quality_options_list;
    Spinner quality_options;
    Button img_download_btn, title_copy;
    TextView titleView;
    ImageView video_image;
    Bundle derived_url_bundle;
    ProgressDialog progressDialog;
    private static final int PERMISSION_REQUEST_CODE = 1000;
    //WebView downloadview;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){

            case PERMISSION_REQUEST_CODE:{

                if(grantResults.length > 0 && grantResults[0] == getPackageManager().PERMISSION_GRANTED)
                    Toast.makeText(this, "Permission Genarated", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(this, "Permission Denide", Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        progressDialog = new ProgressDialog(Title.this);

        if(ActivityCompat.checkSelfPermission(Title.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, PERMISSION_REQUEST_CODE);
            }
        }

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        quality_options_list = getResources().getStringArray(R.array.quality_type);
        //For accessing the clickboare option
        myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

        //spinner
        quality_options = findViewById(R.id.download_option_id);
        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, quality_options_list);
        quality_options.setAdapter(spinner_adapter);
        //at this moment there is no use of listener so thats all...

        //title options
        title_copy = findViewById(R.id.title_copy_id);
        title_copy.setOnClickListener(this);
        titleView = findViewById(R.id.title_view_id);
        title_search_btn = findViewById(R.id.title_search_btn_id);
        title_search_btn.setOnClickListener(this);

        //Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Action Bar Logo
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Thumbnail");
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //Top search box items control
        title_search_box = findViewById(R.id.user_title_search_box_id);
        title_url_pest_btn = findViewById(R.id.title_url_pest_btn_id);

        title_url_pest_btn.setOnClickListener(this);
        title_search_box.setOnClickListener(this);

        //image section
        video_image = findViewById(R.id.video_image_view_id);
        //Picasso.with(getApplicationContext()).load("https://www.w3schools.com/w3css/img_lights.jpg").into(video_image);

        //getting and setting the derived url to the search box
        try{
            String derived_url;
            derived_url_bundle = getIntent().getExtras();
            if(derived_url_bundle.containsKey("url")){
                derived_url = derived_url_bundle.getString("url", "");
                //Toast.makeText(this, derived_url, Toast.LENGTH_SHORT).show();
                title_search_box.setText(derived_url);
                title_search_btn.callOnClick();
            }
        }
        catch (Exception e){

        }
        //getting every key stroke of the edit text of the activity and filter actions
        title_search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(i==0){
                   // clear_btn.setVisibility(GONE);
                }
                else{
                    //clear_btn.setVisibility(VISIBLE);
                }
                DataClass.available = false;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                DataClass.url = String.valueOf(charSequence);
                title_search_box.setError(null);

            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });



        title_search_box.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_UP) {

                    title_search_box.setFocusableInTouchMode(true);
                    title_search_box.setFocusable(true);
                    title_search_box.requestFocus();

                    if(event.getRawX() >= (title_search_box.getRight() - title_search_box.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here

                        title_search_box.setText("");

                        //Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();

                        return true;
                    }
                }
                return false;
            }
        });


        //image download button
        img_download_btn = findViewById(R.id.image_download_btn_id);
        img_download_btn.setOnClickListener(this);

        //Bottom Navigation Section
        BottomNavigationView btnv = findViewById(R.id.bottom_navigation);
        btnv.setSelectedItemId(R.id.title_item);
        btnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent;
                menuItem.setChecked(true);
                switch (menuItem.getItemId()){

                    case R.id.tag_item:
                        intent = new Intent(Title.this, Tag.class);

                        //(problem) need to reduce
                        if(!DataClass.url.equals(null) && (DataClass.url.contains("www.youtube.com") || DataClass.url.contains("youtu.be"))&& !DataClass.url.contains(" ")){
                            intent.putExtra("url", DataClass.url);
                        }

                        startActivity(intent);
                        finish();
                        break;
                    case R.id.title_item:
                        break;

                    case R.id.description_item:
                        intent = new Intent(Title.this, Description.class);

                        //(problem) need to reduce
                        if(!DataClass.url.equals(null) && (DataClass.url.contains("www.youtube.com") || DataClass.url.contains("youtu.be") )&& !DataClass.url.contains(" ")){
                            intent.putExtra("url", DataClass.url);
                        }

                        startActivity(intent);
                        finish();
                        break;
                }
                return false;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
         if(view.getId()==R.id.title_url_pest_btn_id){

            try{
                ClipData abc = myClipboard.getPrimaryClip();
                ClipData.Item item = abc.getItemAt(0);
                String text = item.getText().toString();
                title_search_box.setText(title_search_box.getText().toString()+text);


                //cursor

                EditText editText = findViewById(R.id.user_title_search_box_id);
                editText.setSelection(editText.getText().length()); // End point Cursor



            }
            catch (Exception e){
                Toast.makeText(this, "clipboard is empty", Toast.LENGTH_SHORT).show();
            }

            //Toast.makeText(this, "pest", Toast.LENGTH_SHORT).show();

        }
        else if(view.getId()==R.id.image_download_btn_id){

            try{
                if(DataClass.check(getApplicationContext())){


                    String image_Quality = quality_options.getSelectedItem().toString();
                    Toast.makeText(this, image_Quality, Toast.LENGTH_SHORT).show();
                    String vid_id = "", input = "";
                    //.d("hellow", image_Quality);
                    try{
                        input = DataClass.url;

                    }
                    catch (Exception e){
                        //Toast.makeText(this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                    if(input.contains("youtube.com") || input.contains("youtu.be")) {

                        vid_id = DataClass.urlFilter(input);
                    }


                    String url = DataClass.first_Section+vid_id+DataClass.second_Section+DataClass.console_key1;
                    String image_url = null;
                    if(image_Quality.equals("High Quality")){
                        url+=DataClass.third_Section_thumb1;
                        Log.d("hellow", url);
                        image_url = returnImage(url);
                        Log.d("hellowp", "to "+image_url);

                    }
                    else if(image_Quality.equals("Normal Quality")){
                        url+=DataClass.third_Section_thumb2;
                        image_url = returnImage(url);


                    }
                    else if(image_Quality.equals("Low Quality")){
                        url+=DataClass.third_Section_thumb3;
                        image_url = returnImage(url);

                    }
                    try{
                        //download_image(image_url);

                        if(isStoragePermissionGranted()){
                            final File directory = new  File(String.valueOf("/Utuber"));

                            if(!directory.exists()){
                                directory.mkdir();

                                download_image(image_url);
                                //Picasso.with(Title.this).load(image_url).into(picassoImageTarget(getApplicationContext(), "", "Utuber_"+System.currentTimeMillis()+".jpg"));
                            }
                            else{
                                download_image(image_url);
                                //imageUrl = image_url;
                                /*DownloadImage downloadImage = new DownloadImage();
                                downloadImage.execute(image_url);*/
                                //Picasso.with(Title.this).load(image_url).into(picassoImageTarget(getApplicationContext(), "", "Utuber_"+System.currentTimeMillis()+".jpg"));
                            }
                        }
                    }
                    catch (Exception e){
                        Log.d("hellowp", "error");
                    }
                    Log.d("hello3", image_url);

                    //Toast.makeText(this, "download", Toast.LENGTH_SHORT).show();
                    
                }
            }
            catch (Exception e){
                Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        }
        else if(view.getId()==R.id.title_copy_id){

            String search_title;
            search_title = titleView.getText().toString();
            if(search_title.isEmpty()){
                Toast.makeText(this, "There are nothing to copy", Toast.LENGTH_SHORT).show();
            }
            else{
                ClipData myClip;
                myClip = ClipData.newPlainText("title", search_title);
                myClipboard.setPrimaryClip(myClip);
                Toast.makeText(this, "Title copyed to clpboard", Toast.LENGTH_SHORT).show();
            }
        }
        else if(view.getId()==R.id.title_search_btn_id){
            
            try{
                if(DataClass.check(getApplicationContext())){


                    if(DataClass.chk_rating_show(Title.this)){

                        DataClass.show_rating_bar(Title.this, Title.this);

                    }
                    else{
                        progressDialog.setMessage("Extracting contents....");
                        progressDialog.show();

                        String input = title_search_box.getText().toString();
                        if(input.isEmpty()){
                            //title_search_box.setError("Enter url");
                            Toast.makeText(this, "Nothing for search", Toast.LENGTH_SHORT).show();
                        }
                        else if(input.contains("youtube.com") || input.contains("youtu.be")){

                            String vid_id = DataClass.urlFilter(input);
                            //title_search_box.setText(vid_id);
                            String url1 = DataClass.first_Section+vid_id+DataClass.second_Section+DataClass.console_key1+DataClass.third_Section_title;
                            String url2 = DataClass.first_Section+vid_id+DataClass.second_Section+DataClass.console_key1+DataClass.third_Section_thumb2;
                            //Log.d("error4", url);
                            returnTitle(url1);
                            loadImage(url2);
                            DataClass.rating_counter++;
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
                Toast.makeText(this, "Check your internet Connection", Toast.LENGTH_SHORT).show();
            }

            
        }
    }

    void returnTitle(String title_url){
        
        try{


            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String getUrl = title_url;

            final ProgressDialog progressDialog = new ProgressDialog(Title.this);
            progressDialog.setMessage("Extracting tags....");
            progressDialog.show();
            //title_search_box.setText(getUrl);
            StringRequest getRequest = new StringRequest(Request.Method.GET, getUrl, new Response.Listener<String>() {
                @Override
                public void onResponse (String response) {
                    String titleout = response;
                    titleout = titleout.replace("{\n" +
                            "  \"items\": [\n" +
                            "    {\n" +
                            "      \"snippet\": {\n" +
                            "        \"title\": \"", "");
                    titleout = titleout.replace("\"\n" +
                            "      }\n" +
                            "    }\n" +
                            "  ]\n" +
                            "}", "");

                    titleout = titleout.replace(String.valueOf(titleout.charAt(titleout.length()-1)), "");

                    //Log.d("error2", titleout);
                    //Log.d("error2", String.valueOf(titleout.length()));
                    //Toast.makeText(Title.this, titleout, Toast.LENGTH_SHORT).show();
                    titleView.setText(String.valueOf(titleout));
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse (VolleyError error) {
                    //Log.v(TAG, "Volley GET error: " + error.getMessage());

                    returnTitle(getUrl.replace(DataClass.console_key1, DataClass.console_key2));
                    if(progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                    //Toast.makeText(Title.this, "error", Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(getRequest);
            progressDialog.dismiss();
            
        }
        catch (Exception e){
            Toast.makeText(this, "Title loading problem", Toast.LENGTH_SHORT).show();
        }
    }
    void loadImage(String url){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String getUrl = url;
        //title_search_box.setText(getUrl);
        StringRequest getRequest = new StringRequest(Request.Method.GET, getUrl, new Response.Listener<String>() {
            @Override
            public void onResponse (String response) {
                String image_url = response;
                image_url = image_url.replace("{\n" +
                        "  \"items\": [\n" +
                        "    {\n" +
                        "      \"snippet\": {\n" +
                        "        \"thumbnails\": {\n" +
                        "          \"standard\": {\n" +
                        "            \"url\": \"", "");
                image_url = image_url.replace("\"\n" +
                        "          }\n" +
                        "        }\n" +
                        "      }\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}", "");

                image_url = image_url.replace(String.valueOf(image_url.charAt(image_url.length()-1)), "");
                try{
                    Picasso.with(getApplicationContext()).load(image_url).into(video_image);
                    progressDialog.dismiss();
                }
                catch (Exception e){
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse (VolleyError error) {
                //Log.v(TAG, "Volley GET error: " + error.getMessage());

                loadImage(getUrl.replace(DataClass.console_key1, DataClass.console_key2));
                progressDialog.dismiss();
                //Toast.makeText(Title.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(getRequest);
    }

    String returnImage(String received_url){


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String getUrl = received_url;
        //final String[] return_url = new String[1];
        //title_search_box.setText(getUrl);



        StringRequest getRequest = new StringRequest(Request.Method.GET, getUrl, new Response.Listener<String>() {
            @Override
            public void onResponse (String response) {
                String image_url = response;
                image_url = image_url.replace("{\n" +
                        "  \"items\": [\n" +
                        "    {\n" +
                        "      \"snippet\": {\n" +
                        "        \"thumbnails\": {\n" +
                        "          \"standard\": {\n" +
                        "            \"url\": \"", "");

                image_url = image_url.replace("{\n" +
                        "  \"items\": [\n" +
                        "    {\n" +
                        "      \"snippet\": {\n" +
                        "        \"thumbnails\": {\n" +
                        "          \"maxres\": {\n" +
                        "            \"url\": \"", "");

                image_url = image_url.replace("{\n" +
                        "  \"items\": [\n" +
                        "    {\n" +
                        "      \"snippet\": {\n" +
                        "        \"thumbnails\": {\n" +
                        "          \"medium\": {\n" +
                        "            \"url\": \"", "");

                image_url = image_url.replace("\"\n" +
                        "          }\n" +
                        "        }\n" +
                        "      }\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}", "");

                DataClass.image_temp = image_url.replace(String.valueOf(image_url.charAt(image_url.length()-1)), "");
                Log.d("hellow1", DataClass.image_temp);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse (VolleyError error) {
                //Log.v(TAG, "Volley GET error: " + error.getMessage());
                //Toast.makeText(Title.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(getRequest);


        Log.d("hellowp", "from "+String.valueOf(DataClass.image_temp));
        return String.valueOf(DataClass.image_temp);
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                //Log.v(TAG,"Permission is granted");
                return true;
            } else {

                //Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            //Log.v(TAG,"Permission is granted");
            return true;
        }
    }


    void download_image(String url){


        String DownloadImageURL = url;
        DownloadManager.Request mRequest = new DownloadManager.Request(Uri.parse(DownloadImageURL));
        mRequest.allowScanningByMediaScanner();
        mRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        mRequest.setDestinationInExternalPublicDir("/Utuber", System.currentTimeMillis()+".jpeg");
        DownloadManager mDownloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        mDownloadManager.enqueue(mRequest);
        Toast.makeText(Title.this,"Image Downloaded Successfully...",Toast.LENGTH_LONG).show();

    }


}
