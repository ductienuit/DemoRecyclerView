package com.example.ductien.viewlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailTopicActivity extends AppCompatActivity {

    public ImageView topicImage;
    public TextView topicName;
    public TextView topicDescription;
    public View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_topic);

        //Get data from category
        Intent detailTopicIntent = getIntent();
        if(detailTopicIntent==null)
            Toast.makeText(getApplicationContext(),"No data in intent",Toast.LENGTH_LONG);
        Bundle topicData = detailTopicIntent.getBundleExtra("DetailTopic");

        topicImage = findViewById(R.id.topic_image);
        topicName = findViewById(R.id.topic_name);
        topicDescription = findViewById(R.id.topic_description);
    try {
        topicImage.setImageResource(R.drawable.dog);
        topicName.setText(topicData.getString("name"));
        topicDescription.setText(topicData.getString("description"));
    }catch (Exception e)
    {
        Toast.makeText(getApplicationContext(), (CharSequence) e,Toast.LENGTH_SHORT);
    }
    }
}
