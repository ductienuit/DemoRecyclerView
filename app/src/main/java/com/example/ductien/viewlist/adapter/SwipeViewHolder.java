package com.example.ductien.viewlist.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.example.ductien.viewlist.R;


public class SwipeViewHolder extends RecyclerView.ViewHolder{

    SwipeLayout swipeLayout;
    public ImageView topicImage;
    public TextView topicName;
    public TextView topicDescription;
    TextView tvDelete;
    TextView tvEdit;

    public SwipeViewHolder(View itemView) {
        super(itemView);
        topicImage = (ImageView)itemView.findViewById(R.id.topic_image);
        topicName = (TextView)itemView.findViewById(R.id.topic_name);
        topicDescription = (TextView)itemView.findViewById(R.id.topic_description);
        swipeLayout = itemView.findViewById(R.id.swipe);
        tvDelete = (TextView) itemView.findViewById(R.id.tvDelete);
        tvEdit = (TextView) itemView.findViewById(R.id.tvEdit);
    }
}
