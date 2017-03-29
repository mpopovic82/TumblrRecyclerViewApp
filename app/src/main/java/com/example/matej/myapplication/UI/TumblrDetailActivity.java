package com.example.matej.myapplication.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.example.matej.myapplication.R;

public class TumblrDetailActivity extends AppCompatActivity {

    public static final String BLOG_NAME = "BLOG_NAME";
    public static final String SUMMARY = "SUMMARY";
    public static final String DATE = "DATE";
    public static final String IMAGE_URL = "IMAGE_URL";


    private String name;
    private String summary;
    private String imageUrl;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tumblr_detail_activity);
        setData();
        showTumblrData();

    }

    private void setData() {
        Intent i = getIntent();
        if (i.hasExtra(BLOG_NAME)) {
            name = i.getExtras().getString(BLOG_NAME);
        }

        if (i.hasExtra(SUMMARY)) {
            summary = i.getExtras().getString(SUMMARY);
        }

        if (i.hasExtra(DATE)) {
            date = i.getExtras().getString(DATE);
        }

        if (i.hasExtra(IMAGE_URL)) {
            imageUrl = i.getExtras().getString(IMAGE_URL);
        }
    }
    private void showTumblrData() {

        Intent i = getIntent();

        TextView blogName = (TextView) findViewById(R.id.name);
        blogName.setText(name);
        TextView blogSummary = (TextView) findViewById(R.id.summary);
        blogSummary.setText(summary);
        TextView blogDate = (TextView) findViewById(R.id.date);
        blogDate.setText(date);
        TextView blogImageUrl = (TextView) findViewById(R.id.image_url);
        blogImageUrl.setText(imageUrl);
    }
}










