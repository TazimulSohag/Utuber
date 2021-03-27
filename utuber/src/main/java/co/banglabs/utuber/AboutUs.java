package co.banglabs.utuber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;


public class AboutUs extends AppCompatActivity {

    WebView webView1, webView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        Log.d("errfound", "1");
        webView1 = findViewById(R.id.about_us_view_id1);
        Log.d("errfound", "2");
        webView2 = findViewById(R.id.about_us_view_id2);


        //Action Bar Logo
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.android_white);
        getSupportActionBar().setTitle("About App");
        //getSupportActionBar().setDisplayUseLogoEnabled(true);
        Log.d("errfound", "3");

        webView1.loadDataWithBaseURL(null, DataClass.about_us_code, "text/html", "UTF-8", null);

        Log.d("errfound", "4");

        /*webView2.loadDataWithBaseURL(null, DataClass.about_us_code2, "text/html", "UTF-8", null);
        Log.d("errfound", "5");*/


    }
}
