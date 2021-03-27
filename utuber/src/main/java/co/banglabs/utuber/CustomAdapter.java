package co.banglabs.utuber;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    public Context context;
    public ArrayList<String> name;
    public ArrayList<Integer> image;
    private LayoutInflater mInflater;


    public CustomAdapter(Context context, ArrayList<String> name, ArrayList<Integer> image) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.name = name;
        this.image = image;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;

        if(name.get(0).equals("Tag")){
            view = mInflater.inflate(R.layout.option_base_layout, parent, false);
        }
        else{
            view = mInflater.inflate(R.layout.minitool_baselayout, parent, false);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.option_image.setImageResource(image.get(position));
        holder.option_name_tv.setText(name.get(position));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView option_name_tv;
        ImageView option_image;
        private String urltemp= null;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            option_name_tv = itemView.findViewById(R.id.base_option_name);
            option_image = itemView.findViewById(R.id.base_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try{
                        //listener space
                        Intent intent = null;
                        switch (option_name_tv.getText().toString()){
                            case "Tag":
                                intent = new Intent(context, Tag.class);
                                //Toast.makeText(context, "tg", Toast.LENGTH_SHORT).show();
                                break;

                            case "Thumbnail":
                                intent = new Intent(context, Title.class);
                                break;

                            case "Description":
                                intent = new Intent(context, Description.class);
                                break;


                            case "Shortcuts":
                                ArrayList<String> option_name = new ArrayList<String>(Arrays.asList("Trending", "Gaming", "Music", "Live"));
                                ArrayList<Integer> option_logo = new ArrayList<Integer>(Arrays.asList(R.drawable.trending,  R.drawable.gaming,
                                        R.drawable.music, R.drawable.live));

                                final Dialog dialog = new Dialog(context);
                                dialog.setContentView(R.layout.mini_tool_layout);
                                dialog.setTitle("Title...");
                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                                RecyclerView listview = dialog.findViewById(R.id.mini_tool_list_id);
                                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
                                listview.setLayoutManager(staggeredGridLayoutManager); // set LayoutManager to RecyclerView
                                CustomAdapter customAdapter = new CustomAdapter(context, option_name, option_logo);
                                listview.setAdapter(customAdapter);

                                dialog.show();
                                break;

                            case "Trending":
                                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/feed/trending")));
                                break;

                            case "Gaming":
                                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/gaming")));
                                break;

                            case "Music":
                                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/feed/trending?bp=4gIuCggvbS8wNHJsZhIiUExGZ3F1TG5MNTlhbG0zTms0cWdiUk5jQ1NRQ1ZwVXRKMw%3D%3D")));
                                break;

                            case "Live":
                                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UC4R8DWoMoI7CAwX8_LjQHig")));
                                break;

                            default:
                                //Toast.makeText(context, "no action to do", Toast.LENGTH_SHORT).show();
                                    
                        }

                        urltemp = DataClass.url;
                        if(urltemp != null && (urltemp.contains("www.youtube.com") || (urltemp.contains("youtu.be")) && !urltemp.contains(" "))){
                            assert intent != null;
                            intent.putExtra("url", urltemp);
                            //Toast.makeText(context, "got the error", Toast.LENGTH_SHORT).show();
                        }
                        context.startActivity(intent);
                    }
                    catch (Exception e){
                        //Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();

                    }

                }
            });
        }
    }
}




