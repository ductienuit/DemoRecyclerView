package com.example.ductien.viewlist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ductien.viewlist.R;
import com.example.ductien.viewlist.models.TopicObject;

import java.util.List;

/**
 * Created by DucTien on 09/07/2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private Context context;
    private List<TopicObject> topicList;

    public CategoryAdapter(Context context, List<TopicObject> topicList) {
        this.context = context;
        this.topicList = topicList;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_category_layout, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        TopicObject topicObject = topicList.get(position);
        holder.topicImage.setImageResource(R.drawable.dog);
        holder.topicName.setText(topicObject.getTopicName());
        holder.topicDescription.setText(topicObject.getTopicDescription());
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }
}
