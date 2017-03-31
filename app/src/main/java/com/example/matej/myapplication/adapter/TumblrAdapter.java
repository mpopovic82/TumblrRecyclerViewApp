package com.example.matej.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.matej.myapplication.R;
import com.example.matej.myapplication.UI.TumblrDetailActivity;
import com.example.matej.myapplication.models.Example;
import com.squareup.picasso.Picasso;

import static com.example.matej.myapplication.UI.TumblrDetailActivity.BLOG_NAME;
import static com.example.matej.myapplication.UI.TumblrDetailActivity.DATE;
import static com.example.matej.myapplication.UI.TumblrDetailActivity.IMAGE_URL;
import static com.example.matej.myapplication.UI.TumblrDetailActivity.SUMMARY;

public class TumblrAdapter extends android.support.v7.widget.RecyclerView.Adapter<TumblrAdapter.AdapterHolder> {

    private Example example;
    private Context context;

    public TumblrAdapter(Example example, Context context) {
        this.example = example;
        this.context = context;
    }

    @Override
    public TumblrAdapter.AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.list_item, parent, false);

        // Return a new holder instance
        AdapterHolder viewHolder = new AdapterHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(TumblrAdapter.AdapterHolder viewHolder, int position) {
        // Get the data model based on position

       
        final String summary = example.getResponse().getPosts().get(position).getSummary();
        //getAltSizes().get(1) we get second alt size from list because first from list is not an image, its a link to blog
        final String imageUrl = example.getResponse().getPosts().get(position).getPhotos().get(0).getAltSizes().get(1).getUrl();
        final String date = example.getResponse().getPosts().get(position).getDate();
        final String name = example.getResponse().getPosts().get(position).getBlogName();



        // Set item views based on your views and data model
        TextView textView = viewHolder.summary;
        final ImageView image = viewHolder.photo;

        textView.setText(summary);
        Picasso.with(context).load(imageUrl).
                fit().into(image);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TumblrDetailActivity.class);
                intent.putExtra(BLOG_NAME, name);
                intent.putExtra(IMAGE_URL, imageUrl);
                intent.putExtra(DATE, date);
                intent.putExtra(SUMMARY, summary);

                context.startActivity(intent);
                //send date form Example model to new Activity via intent.
            }
        };

        image.setOnClickListener(clickListener);
        textView.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return example.getResponse().getPosts().size();
    }

    class AdapterHolder extends android.support.v7.widget.RecyclerView.ViewHolder {

        public TextView summary;
        public ImageView photo;

        public AdapterHolder(View itemView) {
            super(itemView);

            summary = (TextView)itemView.findViewById(R.id.blog_txt);
            photo = (ImageView)itemView.findViewById(R.id.blog_image);
        }
    }
}
