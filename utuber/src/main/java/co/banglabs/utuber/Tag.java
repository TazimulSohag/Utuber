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
import java.util.ArrayList;
import java.util.List;



public class Tag extends AppCompatActivity implements View.OnClickListener {

    Button copy_tag;
    ClipboardManager myClipboard;
    EditText tag_search_box;
    ImageButton tag_url_pest_btn, tag_search_btn;
    Bundle derived_url_bundle;
    String derived_url;
    private TagContainerLayout mTagContainerLayout1;
    List<String> list1 = new ArrayList<String>();
    String[] selected_tag = new String[50];


    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);
        
        Log.d("pulok1", "pu");

        for(int i = 0;i<50;i++) selected_tag[i]="000";
        //Tag view section

        mTagContainerLayout1 = (TagContainerLayout) findViewById(R.id.tagcontainerLayout1);
        //mTagContainerLayout1.setDefaultImageDrawableID(R.drawable.yellow_avatar);

        // Set custom click listener
        mTagContainerLayout1.setOnTagClickListener(new TagView.OnTagClickListener() {
            int i = 0;
            @Override
            public void onTagClick(int position, String text) {
                if(i>0){
                    if(selected_tag[position].equals("000")){
                        //Log.d("error3", "rfound");
                        selected_tag[position] = text;

                        if (position < mTagContainerLayout1.getChildCount()) {
                            mTagContainerLayout1.selectTagView(position);
                        }

                        i++;
                    }
                    else{
                        selected_tag[position] = "000";
                        if (position < mTagContainerLayout1.getChildCount()) {
                            mTagContainerLayout1.deselectTagView(position);
                        }
                        i--;
                    }
                }
            }

            @Override
            public void onTagLongClick(final int position, String text) {
                mTagContainerLayout1.toggleSelectTagView(position);
                Log.d("error3", String.valueOf(position));

                if(selected_tag[position].equals("000")){
                    //Log.d("error3", "rfound");
                    selected_tag[position] = text;
                    i++;
                }
                else{
                    selected_tag[position] = "000";
                    i--;
                }

                //Toast.makeText(Tag.this, text, Toast.LENGTH_SHORT).show();

                /*List<Integer> selectedPositions = mTagContainerLayout1.getSelectedTagViewPositions();
                Toast.makeText(Tag.this, "selected-positions:" + selectedPositions.toString(),
                        Toast.LENGTH_SHORT).show();*/
                /*AlertDialog dialog = new AlertDialog.Builder(Tag.this)
                        .setTitle("long click")
                        .setMessage("You will delete this tag!")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (position < mTagContainerLayout1.getChildCount()) {
                                    mTagContainerLayout1.removeTag(position);
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                dialog.show();*/
            }

            @Override
            public void onSelectedTagDrag(int position, String text) {

            }
            @Override
            public void onTagCrossClick(int position) {
            }
        });

        //checking for previous data
        try{
            if(DataClass.available){

                for(int i=0;i<DataClass.tag_length;i++) list1.add(DataClass.tags[i]);
                setTags();

            }
        }
        catch (Exception e){
            Log.d("error", "error on data availability check");
        }


        //For accessing the clickboare option
        myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        copy_tag = findViewById(R.id.tag_copy_id);

        //Top search box items control
        tag_search_box = findViewById(R.id.user_tag_search_box_id);
        tag_url_pest_btn = findViewById(R.id.tag_url_pest_btn_id);
        tag_search_btn = findViewById(R.id.tag_search_btn_id);

        tag_url_pest_btn.setOnClickListener(this);
        tag_search_btn.setOnClickListener(this);
        tag_search_box.setOnClickListener(this);

        copy_tag.setOnClickListener(this);

        //getting and setting the derived url to the search box
        try{
            derived_url_bundle = getIntent().getExtras();
            if(derived_url_bundle.containsKey("url")){
                derived_url = derived_url_bundle.getString("url", "11001");
                //Toast.makeText(this, derived_url, Toast.LENGTH_SHORT).show();
                tag_search_box.setText(derived_url);
                tag_search_btn.callOnClick();
            }
        }
        catch (Exception e){

        }


        //getting every key stroke of the edit text of the activity and filter actions
        tag_search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(i==0){
                    //clear_btn.setVisibility(GONE);
                }
                else{
                    //clear_btn.setVisibility(VISIBLE);
                }
                DataClass.available = false;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                DataClass.url = String.valueOf(charSequence);
                tag_search_box.setError(null);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        tag_search_box.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_UP) {

                    tag_search_box.setFocusableInTouchMode(true);
                    tag_search_box.setFocusable(true);
                    tag_search_box.requestFocus();

                    if(event.getRawX() >= (tag_search_box.getRight() - tag_search_box.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here

                        tag_search_box.setText("");

                        //Toast.makeText(Tag.this, "click", Toast.LENGTH_SHORT).show();

                        return true;
                    }
                }
                return false;
            }
        });


        //tag list

        //Action Bar Logo
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Tag");
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        //Bottom Navigation Section

        BottomNavigationView btnv = findViewById(R.id.bottom_navigation);
        btnv.setSelectedItemId(R.id.tag_item);
        btnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent;

                switch (menuItem.getItemId()){

                    case R.id.tag_item:
                        //Intent intent = new Intent(this, Tag.class)
                        break;
                    case R.id.title_item:
                        intent = new Intent(Tag.this, Title.class);

                        //(problem) need to reduce
                        if(!DataClass.url.equals(null) && (DataClass.url.contains("www.youtube.com") || DataClass.url.contains("youtu.be")) && !DataClass.url.contains(" ")){
                            intent.putExtra("url", DataClass.url);
                        }

                        startActivity(intent);
                        finish();
                        break;

                    case R.id.description_item:
                        intent = new Intent(Tag.this, Description.class);

                        //(problem) need to reduce
                        if(!DataClass.url.equals(null) && (DataClass.url.contains("www.youtube.com") || DataClass.url.contains("youtu.be")) && !DataClass.url.contains(" ")){
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

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.tag_copy_id){

            try{
                String temp="";
                for(int i = 0;i<20;i++){

                    if(!selected_tag[i].equals("000")){

                        temp+=","+selected_tag[i];

                    }
                }
                //temp = temp.replace(",", "");
                if(temp.isEmpty()){
                    if(DataClass.available && DataClass.tag_length>0){
                        for(int  i=0;i<DataClass.tag_length;i++){
                            temp+=","+DataClass.tags[i];
                        }
                    }
                    else{
                        Toast.makeText(this, "nothing to copy", Toast.LENGTH_SHORT).show();

                    }
                }
                temp = temp.substring(1);

                ClipData myClip;
                myClip = ClipData.newPlainText("tag", temp);
                myClipboard.setPrimaryClip(myClip);
                Toast.makeText(this, "Tags copyed to clpboard", Toast.LENGTH_SHORT).show();

            }
            catch (Exception e){
                Toast.makeText(this, "nothing to copy", Toast.LENGTH_SHORT).show();
            }

        }

        else if(view.getId()==R.id.tag_url_pest_btn_id){

            try{
                ClipData abc = myClipboard.getPrimaryClip();
                ClipData.Item item = abc.getItemAt(0);
                String text = item.getText().toString();
                tag_search_box.setText(text);

                //cursor
                EditText editText = findViewById(R.id.user_tag_search_box_id);
                editText.setSelection(editText.getText().length()); // End point Cursor
            }
            catch (Exception e){
                Toast.makeText(this, "clipboard is empty", Toast.LENGTH_SHORT).show();
            }

        }
        else if(view.getId()==R.id.tag_search_btn_id){

            try{
                if(DataClass.check(getApplicationContext())){


                    if(DataClass.chk_rating_show(Tag.this)){

                        DataClass.show_rating_bar(Tag.this, Tag.this);

                    }

                    else{

                        String input = tag_search_box.getText().toString();
                        if(input.isEmpty()){

                            Toast.makeText(this, "Nothing for search", Toast.LENGTH_SHORT).show();
                        }
                        else if(input.contains("youtube.com/") || input.contains("youtu.be/")){

                            String vid_id = DataClass.urlFilter(input);
                            //Toast.makeText(this, vid_id, Toast.LENGTH_SHORT).show();
                            Log.d("hhhhhh", vid_id);
                            //Toast.makeText(this, vid_id, Toast.LENGTH_SHORT).show();
                            if(!vid_id.isEmpty() && vid_id.length()==11){
                                String url = DataClass.first_Section+vid_id+DataClass.second_Section+DataClass.console_key1+DataClass.third_Section_tag;
                                Log.d("error4", url);

                                returnTag(url);
                                DataClass.rating_counter++;
                            }
                            else{
                                tag_search_box.setError("Invalid youtube video link");
                                Log.d("dbgp", vid_id);
                            }

                            //Log.d("error4", "Key: "+temp);
                        }
                        else if(input.contains(".com/") && !input.contains("youtube")){
                            Toast.makeText(this, "Non Youtube content", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
            catch (Exception e){
                //Toast.makeText(this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        }
    }


    void returnTag(String getUrl){

        try{

            final ProgressDialog progressDialog = new ProgressDialog(Tag.this);
            if(!progressDialog.isShowing()){
                progressDialog.setMessage("Extracting tags....");
                progressDialog.show();
            }

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String new_url = getUrl;
            StringRequest getRequest = new StringRequest(Request.Method.GET, new_url, new Response.Listener<String>() {
                @Override
                public void onResponse (String response) {
                    String titleout = response;
                    titleout = titleout.replace("{\n" +
                            "  \"items\": [\n" +
                            "    {\n" +
                            "      \"snippet\": {\n" +
                            "        \"tags\": [\n" +
                            "          \"", "");
                    titleout = titleout.replace("\"\n" +
                            "        ]\n" +
                            "      }\n" +
                            "    }\n" +
                            "  ]\n" +
                            "}", "");
                    titleout = titleout.replace("\"", "");
                    titleout = titleout.replaceAll(String.valueOf(titleout.charAt(titleout.length()-1)), "");
                    boolean check;
                    int i = 1;
                    do{
                        check = false;
                        if(titleout.contains(", ")){
                            //Log.d("error3", String.valueOf(i++));
                            titleout = titleout.replaceAll(", ", ",");
                            check = true;
                        }

                    }while (check);

                    String[] temp = titleout.split(",");
                    list1.clear();
                    //storing for tag loading array list
                    for(int j = 0;j<temp.length;j++)  list1.add(temp[j]);
                    //for offline use
                    for(int j = 0;j<temp.length;j++, DataClass.tag_length++){
                        DataClass.tags[j]=temp[j];
                        Log.d("taglist", DataClass.tags[j]);
                    }

                    //for checking that any offline data is available or not
                    DataClass.available = true;
                    setTags();
                    progressDialog.dismiss();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse (VolleyError error) {
                    //Log.v(TAG, "Volley GET error: " + error.getMessage());
                    returnTag(getUrl.replace(DataClass.console_key1, DataClass.console_key2));
                    if(progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                    //Toast.makeText(Tag.this, "consol error", Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(getRequest);
            
        }
        catch (Exception e){
            //Toast.makeText(this, "Tag loading problem", Toast.LENGTH_SHORT).show();
        }

    }
    void setTags(){
        try{
            mTagContainerLayout1.setTags(list1);
        }
        catch (Exception e){
            Log.d("my_error", "error on setting previous tag");
        }

    }


}
