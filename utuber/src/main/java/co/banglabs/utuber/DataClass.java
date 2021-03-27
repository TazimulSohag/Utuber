package co.banglabs.utuber;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;


public class DataClass {

    static String image_temp;
    static SharedPreferences sp;
    static int rating_counter = 1;

    public static String utube_url = "";
    public static String utube_url2 = "";

    public static boolean coppyed = false;

    public static String about_us_code = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Document</title>\n" +
            "</head>\n" +
            "<body style=\"font-family: sans-serif;\">\n" +
            "    <div style=\"border: 1px solid #eaeaea;\n" +
            "    padding: 14px;\n" +
            "    margin-top: 13px;\">\n" +
            "        <p style=\"\n" +
            "        color: #333333;\n" +
            "        font-size: 16px;\n" +
            "        margin-top: 0;\n" +
            "        \">\n" +
            "             <b>Tube Optimizer</b> is an android app for creators and brands channel.\n" +
            "             <b>Tube Optimizer</b> - A tool for Boosting your videos, Increase your video view - <b>SEO</b> quickly and simply Youtube videos with Tube Optimizer proprietary keyword to tags:\n" +
            "        </p>\n" +
            "        <ul style=\"\n" +
            "            list-style: none;\n" +
            "            padding: 0 0px 2px 30px;\n" +
            "            border-bottom: 1px solid #e8e8e8;\n" +
            "            margin-bottom: 10px;\n" +
            "        \">\n" +
            "            <li style=\"\n" +
            "            position: relative;\n" +
            "            padding-bottom: 20px;\n" +
            "            color: #5b5b5b;\n" +
            "            font-size: 15px;\n" +
            "            \">\n" +
            "                <span style=\"\n" +
            "                position: absolute;\n" +
            "                height: 8px;\n" +
            "                width: 8px;\n" +
            "                border-radius: 50%;\n" +
            "                background-color: #3cac92;\n" +
            "                left: -20px;\n" +
            "                top: 4px;\n" +
            "                z-index: 1;\n" +
            "                \"></span>\n" +
            "                <span style=\"\n" +
            "                position: absolute;\n" +
            "                top: -1px;\n" +
            "                bottom: 0;\n" +
            "                width: 5px;\n" +
            "                border-left: 2px dotted #3cac92;\n" +
            "                left: -17px;\n" +
            "                z-index: 0;\n" +
            "                \"></span>\n" +
            "                 We give optimized tags that can significantly improve your video execution, subscriber engagement, and promotion openings.\n" +
            "            </li>\n" +
            "            <li style=\"\n" +
            "            position: relative;\n" +
            "            padding-bottom: 20px;\n" +
            "            color: #5b5b5b;\n" +
            "            font-size: 15px;\n" +
            "            \">\n" +
            "                <span style=\"\n" +
            "                position: absolute;\n" +
            "                height: 8px;\n" +
            "                width: 8px;\n" +
            "                border-radius: 50%;\n" +
            "                background-color: #3cac92;\n" +
            "                left: -20px;\n" +
            "                top: 4px;\n" +
            "                z-index: 1;\n" +
            "                \"></span>\n" +
            "                <span style=\"\n" +
            "                position: absolute;\n" +
            "                top: -1px;\n" +
            "                bottom: 0;\n" +
            "                width: 5px;\n" +
            "                border-left: 2px dotted #3cac92;\n" +
            "                left: -17px;\n" +
            "                z-index: 0;\n" +
            "                \"></span>\n" +
            "               Reveal the tricks of the trade behind your most loved YouTube videos - The top tag is the key!\n" +
            "            </li>\n" +
            "            <li style=\"\n" +
            "            position: relative;\n" +
            "            padding-bottom: 20px;\n" +
            "            color: #5b5b5b;\n" +
            "            font-size: 15px;\n" +
            "            \">\n" +
            "                <span style=\"\n" +
            "                position: absolute;\n" +
            "                height: 8px;\n" +
            "                width: 8px;\n" +
            "                border-radius: 50%;\n" +
            "                background-color: #3cac92;\n" +
            "                left: -20px;\n" +
            "                top: 4px;\n" +
            "                z-index: 1;\n" +
            "                \"></span>\n" +
            "                <span style=\"\n" +
            "                position: absolute;\n" +
            "                top: -1px;\n" +
            "                bottom: 0;\n" +
            "                width: 5px;\n" +
            "                border-left: 2px dotted #3cac92;\n" +
            "                left: -17px;\n" +
            "                z-index: 0;\n" +
            "                \"></span>\n" +
            "                You can download any videos thumbnail using this app.\n" +
            "            </li>\n" +
            "            <li style=\"\n" +
            "            position: relative;\n" +
            "            padding-bottom: 20px;\n" +
            "            color: #5b5b5b;\n" +
            "            font-size: 15px;\n" +
            "            \">\n" +
            "                <span style=\"\n" +
            "                position: absolute;\n" +
            "                height: 8px;\n" +
            "                width: 8px;\n" +
            "                border-radius: 50%;\n" +
            "                background-color: #3cac92;\n" +
            "                left: -20px;\n" +
            "                top: 4px;\n" +
            "                z-index: 1;\n" +
            "                \"></span>\n" +
            "                <span style=\"\n" +
            "                position: absolute;\n" +
            "                top: -1px;\n" +
            "                bottom: 0;\n" +
            "                width: 5px;\n" +
            "                border-left: 2px dotted #3cac92;\n" +
            "                left: -17px;\n" +
            "                z-index: 0;\n" +
            "                \"></span>\n" +
            "                 Top 5 line tags that are trending on YouTube, these tags were generated from your keyword search result.\n" +
            "            </li>\n" +
            "            <li style=\"\n" +
            "            position: relative;\n" +
            "            padding-bottom: 20px;\n" +
            "            color: #5b5b5b;\n" +
            "            font-size: 15px;\n" +
            "            \">\n" +
            "                <span style=\"\n" +
            "                position: absolute;\n" +
            "                height: 8px;\n" +
            "                width: 8px;\n" +
            "                border-radius: 50%;\n" +
            "                background-color: #3cac92;\n" +
            "                left: -20px;\n" +
            "                top: 4px;\n" +
            "                z-index: 1;\n" +
            "                \"></span>\n" +
            "                <span style=\"\n" +
            "                position: absolute;\n" +
            "                top: -1px;\n" +
            "                bottom: 0;\n" +
            "                width: 5px;\n" +
            "                border-left: 2px dotted #3cac92;\n" +
            "                left: -17px;\n" +
            "                z-index: 0;\n" +
            "                \"></span>\n" +
            "                 Get more views with simple tricks.\n" +
            "            </li>\n" +
            "            <li style=\"\n" +
            "            position: relative;\n" +
            "            padding-bottom: 20px;\n" +
            "            color: #5b5b5b;\n" +
            "            font-size: 15px;\n" +
            "            \">\n" +
            "                <span style=\"\n" +
            "                position: absolute;\n" +
            "                height: 8px;\n" +
            "                width: 8px;\n" +
            "                border-radius: 50%;\n" +
            "                background-color: #3cac92;\n" +
            "                left: -20px;\n" +
            "                top: 4px;\n" +
            "                z-index: 1;\n" +
            "                \"></span>\n" +
            "                <span style=\"\n" +
            "                position: absolute;\n" +
            "                top: -1px;\n" +
            "                bottom: 0;\n" +
            "                width: 5px;\n" +
            "                border-left: 2px dotted #3cac92;\n" +
            "                left: -17px;\n" +
            "                z-index: 0;\n" +
            "                \"></span>\n" +
            "                You can copy any youtube videos description and edit before paste it to your video description.\n" +
            "            </li>\n" +
            "            <li style=\"\n" +
            "            position: relative;\n" +
            "            padding-bottom: 20px;\n" +
            "            color: #5b5b5b;\n" +
            "            font-size: 15px;\n" +
            "            \">\n" +
            "                <span style=\"\n" +
            "                position: absolute;\n" +
            "                height: 8px;\n" +
            "                width: 8px;\n" +
            "                border-radius: 50%;\n" +
            "                background-color: #3cac92;\n" +
            "                left: -20px;\n" +
            "                top: 4px;\n" +
            "                z-index: 1;\n" +
            "                \"></span>\n" +
            "                <span style=\"\n" +
            "                position: absolute;\n" +
            "                top: -1px;\n" +
            "                bottom: 32px;\n" +
            "                width: 5px;\n" +
            "                border-left: 2px dotted #3cac92;\n" +
            "                left: -17px;\n" +
            "                z-index: 0;\n" +
            "                \"></span>\n" +
            "                Find the best tag that makes a video to become viral online and optimize your videos tag to get more views likes, comments & shares!\n" +
            "            </li>\n" +
            "        </ul>\n" +
            "\n" +
            "        <div style=\"\n" +
            "        position: relative;\n" +
            "        background-color: #f4f4f4;\n" +
            "        border-left: 3px solid #d91515;\n" +
            "        padding: 6px 15px;\n" +
            "        font-size: 15px;\n" +
            "        margin-top: 23px;\n" +
            "        border-radius: 10px;\n" +
            "        \">\n" +
            "            <span style=\"\n" +
            "            position: absolute;\n" +
            "            height: 20px;\n" +
            "            width: 20px;\n" +
            "            border-radius: 50%;\n" +
            "            background-color: #d91515;\n" +
            "            left: -12px;\n" +
            "            color: #fff;\n" +
            "            text-align: center;\n" +
            "            line-height: 18px;\n" +
            "            top: 20px;\n" +
            "            \">&#8664;</span>\n" +
            "            Do you want more views and subscribers, do you want to grow your Youtube channel?\n" +
            "            Download 'Tube Optimizer' right now and optimize your video tags Tags. <br><br>\n" +
            "            View SEO data like meta tags on any video to get an edge when optimizing your own Youtube channel video.\n" +
            "            Let's start your higher vision with only one tap!\n" +
            "        </div>\n" +
            "\n" +
            "        <div style=\"border-top: 1px solid #e6e6e6; margin-top: 20px;\"></div>\n" +
            "\n" +
            "        <div style=\"\n" +
            "        margin-top: 23px;\n" +
            "        background-color: #E9A029;\n" +
            "        padding: 8px;\n" +
            "        border-radius: 10px;\n" +
            "        margin-top: 23px;\n" +
            "        \">\n" +
            "            <h4 style=\"\n" +
            "            color: #fff;\n" +
            "            text-transform: uppercase;\n" +
            "            font-size: 16px;\n" +
            "            margin: 9px 4px;\n" +
            "            \">Important note:</h4>\n" +
            "            <ul style=\"\n" +
            "            list-style: none;\n" +
            "            padding: 0 0px 2px 30px;\n" +
            "            margin-bottom: 10px;\n" +
            "            \">\n" +
            "                <li style=\"\n" +
            "                position: relative;\n" +
            "                padding-bottom: 20px;\n" +
            "                color: #fff;\n" +
            "                font-size: 15px;\n" +
            "                \">\n" +
            "                    <span style=\"\n" +
            "                    position: absolute;\n" +
            "                    height: 8px;\n" +
            "                    width: 8px;\n" +
            "                    border-radius: 50%;\n" +
            "                    background-color: #202020;\n" +
            "                    left: -20px;\n" +
            "                    top: 4px;\n" +
            "                    z-index: 1;\n" +
            "                    \"></span>\n" +
            "                    <span style=\"\n" +
            "                    position: absolute;\n" +
            "                    top: -1px;\n" +
            "                    bottom: 0;\n" +
            "                    width: 5px;\n" +
            "                    border-left: 2px dotted #202020;\n" +
            "                    left: -17px;\n" +
            "                    z-index: 0;\n" +
            "                    \"></span>\n" +
            "                     Do not copy all keywords of other videos\n" +
            "                </li>\n" +
            "                <li style=\"\n" +
            "                position: relative;\n" +
            "                padding-bottom: 20px;\n" +
            "                color: #fff;\n" +
            "                font-size: 15px;\n" +
            "                \">\n" +
            "                    <span style=\"\n" +
            "                    position: absolute;\n" +
            "                    height: 8px;\n" +
            "                    width: 8px;\n" +
            "                    border-radius: 50%;\n" +
            "                    background-color: #202020;\n" +
            "                    left: -20px;\n" +
            "                    top: 4px;\n" +
            "                    z-index: 1;\n" +
            "                    \"></span>\n" +
            "                    <span style=\"\n" +
            "                    position: absolute;\n" +
            "                    top: -1px;\n" +
            "                    bottom: 0;\n" +
            "                    width: 5px;\n" +
            "                    border-left: 2px dotted #202020;\n" +
            "                    left: -17px;\n" +
            "                    z-index: 0;\n" +
            "                    \"></span>\n" +
            "                      Only use keywords related to your video \n" +
            "                </li>\n" +
            "                <li style=\"\n" +
            "                position: relative;\n" +
            "                padding-bottom: 20px;\n" +
            "                color: #fff;\n" +
            "                font-size: 15px;\n" +
            "                \">\n" +
            "                    <span style=\"\n" +
            "                    position: absolute;\n" +
            "                    height: 8px;\n" +
            "                    width: 8px;\n" +
            "                    border-radius: 50%;\n" +
            "                    background-color: #202020;\n" +
            "                    left: -20px;\n" +
            "                    top: 4px;\n" +
            "                    z-index: 1;\n" +
            "                    \"></span>\n" +
            "                    <span style=\"\n" +
            "                    position: absolute;\n" +
            "                    top: -1px;\n" +
            "                    bottom: 0;\n" +
            "                    width: 5px;\n" +
            "                    border-left: 2px dotted #202020;\n" +
            "                    left: -17px;\n" +
            "                    z-index: 0;\n" +
            "                    \"></span>\n" +
            "                     Use the related keywords in the title, description of your video.\n" +
            "                </li>\n" +
            "                <li style=\"\n" +
            "                position: relative;\n" +
            "                padding-bottom: 20px;\n" +
            "                color: #fff;\n" +
            "                font-size: 15px;\n" +
            "                \">\n" +
            "                    <span style=\"\n" +
            "                    position: absolute;\n" +
            "                    height: 8px;\n" +
            "                    width: 8px;\n" +
            "                    border-radius: 50%;\n" +
            "                    background-color: #202020;\n" +
            "                    left: -20px;\n" +
            "                    top: 4px;\n" +
            "                    z-index: 1;\n" +
            "                    \"></span>\n" +
            "                    <span style=\"\n" +
            "                    position: absolute;\n" +
            "                    top: -1px;\n" +
            "                    bottom: 0;\n" +
            "                    width: 5px;\n" +
            "                    border-left: 2px dotted #202020;\n" +
            "                    left: -17px;\n" +
            "                    z-index: 0;\n" +
            "                    \"></span>\n" +
            "                      Edit Downloaded thumbnail before upload.\n" +
            "                </li>\n" +
            "                <li style=\"\n" +
            "                position: relative;\n" +
            "                padding-bottom: 20px;\n" +
            "                color: #fff;\n" +
            "                font-size: 15px;\n" +
            "                \">\n" +
            "                    <span style=\"\n" +
            "                    position: absolute;\n" +
            "                    height: 8px;\n" +
            "                    width: 8px;\n" +
            "                    border-radius: 50%;\n" +
            "                    background-color: #202020;\n" +
            "                    left: -20px;\n" +
            "                    top: 4px;\n" +
            "                    z-index: 1;\n" +
            "                    \"></span>\n" +
            "                    <span style=\"\n" +
            "                    position: absolute;\n" +
            "                    top: -1px;\n" +
            "                    bottom: 24px;\n" +
            "                    width: 5px;\n" +
            "                    border-left: 2px dotted #202020;\n" +
            "                    left: -17px;\n" +
            "                    z-index: 0;\n" +
            "                    \"></span>\n" +
            "                    Please edit the copied description before paste it to your video description.\n" +
            "                </li>\n" +
            "            </ul>\n" +
            "        </div>\n" +
            "        <div style=\"\n" +
            "        position: relative;\n" +
            "        padding: 5px 10px 5px 22px;\n" +
            "        background-color: #424346;\n" +
            "        color: #fff;\n" +
            "        display: inline-block;\n" +
            "        border-radius: 40px;\n" +
            "        font-size: 13px;\n" +
            "        margin-top: 16px;\n" +
            "        \">\n" +
            "        <span style=\"\n" +
            "        position: absolute;\n" +
            "        height: 7px;\n" +
            "        width: 7px;\n" +
            "        border-radius: 50%;\n" +
            "        background-color: #ffffff;\n" +
            "        left: 6px;\n" +
            "        top: 10px;\n" +
            "        \"></span>\n" +
            "        Thank you</div>\n" +
            "\n" +
            "        <br>\n" +
            "        <div style=\"\n" +
            "        position: relative;\n" +
            "        padding: 5px 10px 5px 22px;\n" +
            "        background-color: #125baf;\n" +
            "        color: #fff;\n" +
            "        display: inline-block;\n" +
            "        border-radius: 40px;\n" +
            "        font-size: 13px;\n" +
            "        margin-top: 16px;\n" +
            "        \">\n" +
            "        <span style=\"\n" +
            "        position: absolute;\n" +
            "        height: 7px;\n" +
            "        width: 7px;\n" +
            "        border-radius: 50%;\n" +
            "        background-color: #ffffff;\n" +
            "        left: 6px;\n" +
            "        top: 10px;\n" +
            "        \"></span>\n" +
            "        BangLabs</div>\n" +
            "    </div>\n" +
            "\n" +
            "\n" +
            "    \n" +
            "\n" +
            "\n" +
            "</body>\n" +
            "</html>";


    public static String about_us_code1 = "<html><body>" +

            "<font style=\"font-size:22px\" >Tube Optimizer</font></br>" +
            "<p align = \"justify\">Tube Optimizer is an android app for creators and brands channel.Tube Optimizer a tool for Boosting your videos, Increase your video view-SEO quickly and simply Youtube videos with Tube Optimizer" +
            "ers proprietary keyword to optimize tags:</p>" +

            "<ol>" +
            "<li>We give optimized tags that can significantly improve your video execution, subscriber engagement, and promotion openings.</li></br>" +
            "<li>Reveal the tricks of the trade behind your most loved YouTube videos - The top tag is the key!</li></br>" +
            "<li>You can download any videos thumbnail using this app.</li>" +
            "<li>Top 5 line tags that are trending on YouTube, these tags were generated from your keyword search result.</br></li>" +
            "<li>Get more views with simple tricks.</li>" +
            "<li>You can copy any youtube videos description and edit before paste it to your video description.</li></br>" +
            "<li>Find the best tag that makes a video to become viral online and optimize your videos tag to get more views likes, comments & shares!</li></br>" +

            "</ol>" +

            "</body>" +

            "</html>";

    public static String about_us_code2 = "<html><body>" +

            "<p>Do you want more views and subscribers, do you want to grow your Youtube channel?Download Tube Optimizer right now and optimize your video tags Tags.</br>" +
            "</p>" +

            "<p>View SEO data like meta tags on any video to get an edge when optimizing your own Youtube channel video.</br>" +
            "Tube Optimizer Lets start your higher vision with only one tap!</p></br></br>" +

            "Important note:</br>" +
            "<ol>" +

            "<li>Do not copy all keywords of other videos</li>" +
            "<li>Only use keywords related to your video content</li>" +
            "<li>Use the related keywords in the title, description of your video.</li>" +
            "<li>Please edit the copied description before paste it to your video description.</li>" +
            "<li>Edit Downloaded thumbnail before upload.</li>" +
            "</ol>" +
            "</body>" +

            "</html>";


    static String url = "";
    static String first_Section = "https://www.googleapis.com/youtube/v3/videos?id=";
    static String second_Section = "&key=";
    static String third_Section_title = "&fields=items(snippet(title))&part=snippet";
    static String third_Section_description = "&fields=items(snippet(description))&part=snippet";
    static String third_Section_tag = "&fields=items(snippet(tags))&part=snippet";
    static String third_Section_thumb1 = "&fields=items(snippet(thumbnails(maxres(url))))&part=snippet";
    static String third_Section_thumb2 = "&fields=items(snippet(thumbnails(standard(url))))&part=snippet";
    static String third_Section_thumb3 = "&fields=items(snippet(thumbnails(medium(url))))&part=snippet";


    static String console_key1 = "AIzaSyBlHgRCM925jP6__sFkSXAiRMnOnTU0FgY";
    static String console_key2 = "AIzaSyBBinv0wR5XLhzyH3IwpbR6fbHR8pM_6DA";

    static String[] tags = new String[50];
    static int tag_length = 0;
    static boolean available = false;


    public static void set_rating_statas(Context context) {

        sp = context.getSharedPreferences("user_rating_data", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("data", "rated");
        editor.commit();


    }

    /*public static void show_statas(Context context){
        sp = context.getSharedPreferences("user_rating_data", context.MODE_PRIVATE);

        if(sp.contains("data")){

            Log.d("msssg", sp.getString("data", "null"));

        }
    }*/
    public static boolean chk_rating_show(Context context) {
        sp = context.getSharedPreferences("user_rating_data", context.MODE_PRIVATE);

        if (!sp.contains("data") && (rating_counter % 4 == 0)) {
            rating_counter++;
            return true;
        } else {
            return false;
        }
    }

    public static void show_rating_bar(final Context context, final Activity activity) {

        final AlertDialog dialogBuilder = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.rating_custom_dialog, null);

        final RatingBar ratingBar = (RatingBar) dialogView.findViewById(R.id.rating_bar_id);
        Button rate = (Button) dialogView.findViewById(R.id.rate_btn_id);
        Button later = (Button) dialogView.findViewById(R.id.later_btn_id);

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float user_rate = ratingBar.getRating();
                Toast.makeText(context, String.valueOf(user_rate), Toast.LENGTH_SHORT).show();
                sp = context.getSharedPreferences("user_rating_data", context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("data", "rated");
                editor.commit();
                String shareMessage = "";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(shareMessage)));
            }
        });
        later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DO SOMETHINGS
                dialogBuilder.cancel();
                //finish();
            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    public static String urlFilter(String get) {


        if (get.contains("=")) {
            get = get.substring(get.indexOf("=") + 1);

            if (get.contains("&")) {
                get = get.substring(0, get.indexOf("&"));
            }
        } else {
            get = get.replace("http://", "");
            get = get.replace("https://", "");
            get = get.replace("https://", "");
            get = get.replace("www.youtube", "");
            get = get.replace("youtu.be", "");
            get = get.replace("embed", "");
            get = get.replace(".com/embed/", "");
            get = get.replace(".com/v/", "");
            get = get.replace(".com/e/", "");
            get = get.replace(".com/watch?v=", "");
            get = get.replace("/watch?feature=player_embedded&v=", "");
            get = get.replace("/", "");
        }


        return get;
    }


    static boolean check(Context c) {


        ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        boolean isWiFi = nInfo.getType() == ConnectivityManager.TYPE_WIFI;
        boolean isCelluler = nInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        if ((isWiFi || isCelluler)) {
            return true;
        } else {
            Toast.makeText(c, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
