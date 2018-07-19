package com.example.ductien.viewlist.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.ductien.viewlist.DetailTopicActivity;
import com.example.ductien.viewlist.DialogEditTopic;
import com.example.ductien.viewlist.R;
import com.example.ductien.viewlist.models.TopicObject;

import java.util.List;

/**
 * Created by DucTien on 09/07/2018.
 */

public class SwipeRecyclerViewAdapter extends RecyclerSwipeAdapter<SwipeViewHolder> {

    private Context context;
    private List<TopicObject> topicList;
    final int REQUESTCODE=10;
    public int savePosition;
    public int savePosChangeTxt;

    public SwipeRecyclerViewAdapter(Context context, List<TopicObject> topicList) {
        this.context = context;
        this.topicList = topicList;
        savePosition=-1;
        savePosChangeTxt=-1;
    }

    @Override
    public SwipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_row_item, parent, false);
        return new SwipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SwipeViewHolder holder, final int position) {
        final TopicObject topicObject = topicList.get(position);
        holder.topicImage.setImageResource(R.drawable.dog);
        holder.topicName.setText(topicObject.getTopicName());
        holder.topicDescription.setText(topicObject.getTopicDescription());


        //Handle onClick the swipe item
        holder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save the last position click
                savePosition = topicList.indexOf(topicObject);

                /* Send data to another activity */
                Bundle topic = new Bundle();
                topic.putString("image",topicObject.getTopicImage());
                topic.putString("name",topicObject.getTopicName());
                topic.putString("description",topicObject.getTopicDescription());

                //Send data to another activity
                Intent detailTopicIntent = new Intent(context, DetailTopicActivity.class);
                detailTopicIntent.putExtra("DetailTopic",topic);
                context.startActivity(detailTopicIntent);
            }
        });

        //Handle click TextView Edit
        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Clicked on Edit  " + holder.topicName.getText(), Toast.LENGTH_SHORT).show();

                /* Send data to another activity */
                Bundle topic = new Bundle();
                topic.putString("image",topicObject.getTopicImage());
                topic.putString("name",topicObject.getTopicName());
                topic.putString("description",topicObject.getTopicDescription());
                topic.putInt("position",position);

                savePosChangeTxt=position;

                //Send data to another activity
                Intent detailTopicIntent = new Intent(context, DialogEditTopic.class);
                detailTopicIntent.putExtra("DetailTopic",topic);
                ((Activity)context).startActivityForResult(detailTopicIntent,REQUESTCODE);

            }
        });

        //Handle click TextView delete
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemManger.removeShownLayouts(holder.swipeLayout);
                topicList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, topicList.size());
                mItemManger.closeAllItems();
                Toast.makeText(view.getContext(), "Deleted " + holder.topicName, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public void deleteLastClick()
    {
        if (savePosition != -1) {
            String nameTopic = topicList.get(savePosition).getTopicName();

            topicList.remove(savePosition);
            notifyItemRemoved(savePosition);
            notifyItemRangeChanged(savePosition, topicList.size());
            mItemManger.closeAllItems();

            Toast.makeText(context, "Deleted "+ nameTopic, Toast.LENGTH_SHORT).show();
            //Reset the last click position
            savePosition = -1;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Kiểm tra requestCode có trùng với REQUEST_CODE vừa dùng
        if(requestCode == REQUESTCODE) {
            // resultCode được set bởi DetailEditTopic
            // RESULT_OK chỉ ra rằng kết quả này đã thành công
            if(resultCode == Activity.RESULT_OK) {
                // Nhận dữ liệu từ Intent trả về
                final String result = data.getStringExtra(DialogEditTopic.EXTRA_DATA);

                TopicObject topicUpdated = new TopicObject("", data.getStringExtra("name"),
                        data.getStringExtra("description"));
                topicList.set(savePosChangeTxt,topicUpdated);

                savePosChangeTxt=-1;

                // Sử dụng kết quả result bằng cách hiện Toast
                Toast.makeText(context, "Result: " + result, Toast.LENGTH_LONG).show();
            } else {
                // DetailActivity không thành công, không có data trả về.
                Toast.makeText(context, "Result: Text not change", Toast.LENGTH_LONG).show();
            }
        }
    }
}
