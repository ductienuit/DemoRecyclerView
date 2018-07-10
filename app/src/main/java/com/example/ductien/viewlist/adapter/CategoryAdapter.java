package com.example.ductien.viewlist.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ductien.viewlist.DetailTopicActivity;
import com.example.ductien.viewlist.R;
import com.example.ductien.viewlist.models.TopicObject;

import java.util.List;

/**
 * Created by DucTien on 09/07/2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private Context context;
    private List<TopicObject> topicList;
    public int savePosition;

    public CategoryAdapter(Context context, List<TopicObject> topicList) {
        this.context = context;
        this.topicList = topicList;
        savePosition=-1;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, final int position) {
        final TopicObject topicObject = topicList.get(position);
        holder.topicImage.setImageResource(R.drawable.dog);
        holder.topicName.setText(topicObject.getTopicName());
        holder.topicDescription.setText(topicObject.getTopicDescription());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Save the last position click
                savePosition= topicList.indexOf(topicObject);

                /* Send data to another activity */
                Bundle topic = new Bundle();
                topic.putString("image",topicObject.getTopicImage());
                topic.putString("name",topicObject.getTopicName());
                topic.putString("description",topicObject.getTopicDescription());

                Intent detailTopicIntent = new Intent(context, DetailTopicActivity.class);
                detailTopicIntent.putExtra("DetailTopic",topic);
                context.startActivity(detailTopicIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    public void deleteLastClick()
    {
        if (savePosition != -1) {
            topicList.remove(savePosition);
            savePosition = -1;
        }
    }
}
