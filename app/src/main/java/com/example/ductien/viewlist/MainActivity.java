package com.example.ductien.viewlist;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.ductien.viewlist.adapter.SwipeRecyclerViewAdapter;
import com.example.ductien.viewlist.models.TopicObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<TopicObject> mDataSet;
    SwipeRecyclerViewAdapter mAdapter;
    final int REQUESTCODE=10;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.quiz_category);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        mDataSet = getTestData();

        if (mDataSet.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
        }

        mAdapter = new SwipeRecyclerViewAdapter(MainActivity.this, mDataSet);
        recyclerView.setAdapter(mAdapter);


//        Firebase
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId  = getString(R.string.default_notification_channel_id);
            String channelName = getString(R.string.default_notification_channel_name);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW));
        }

        // If a notification message is tapped, any data accompanying the notification
        // message is available in the intent extras. In this sample the launcher
        // intent is fired when the notification is tapped, so any accompanying data would
        // be handled here. If you want a different intent fired, set the click_action
        // field of the notification message to the desired intent. The launcher intent
        // is used when no click_action is specified.
        //
        // Handle possible data accompanying notification message.
        // [START handle_data_extras]
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }
        // [END handle_data_extras]
    }

    @Override
    protected void onResume() {
        super.onResume();

        SwipeRecyclerViewAdapter mAdapter = (SwipeRecyclerViewAdapter)recyclerView.getAdapter();
        mAdapter.deleteLastClick();
        recyclerView.setAdapter(mAdapter);
    }

    public List<TopicObject> getTestData() {
        List<TopicObject> testData = new ArrayList<>();
        testData.add(new TopicObject("", "Dog", "Customize your RecyclerView\n" +
                "You can customize the RecyclerView objects to meet your specific needs. The standard classes provide all the functionality that most developers will need; in many cases, the only customization you need to do is design the view for each view holder and write the code to update those views with the appropriate data. However, if your app has specific requirements, you can modify the standard behavior in a number of ways. The following sections describe some of the other common customizations."));
        testData.add(new TopicObject("", "Match Logo", "Simple way to match logo"));
        testData.add(new TopicObject("", "Match Logo", "Simple way to match logo"));
        testData.add(new TopicObject("", "Match Logo", "Simple way to match logo"));
        testData.add(new TopicObject("", "Match Logo", "Simple way to match logo"));
        testData.add(new TopicObject("", "Dog", "Customize your RecyclerView\n" +
                "You can customize the RecyclerView objects to meet your specific needs. The standard classes provide all the functionality that most developers will need; in many cases, the only customization you need to do is design the view for each view holder and write the code to update those views with the appropriate data. However, if your app has specific requirements, you can modify the standard behavior in a number of ways. The following sections describe some of the other common customizations."));
        testData.add(new TopicObject("", "Match Logo", "Simple way to match logo"));
        testData.add(new TopicObject("", "Match Logo", "Simple way to match logo"));
        testData.add(new TopicObject("", "Match Logo", "Simple way to match logo"));
        testData.add(new TopicObject("", "Match Logo", "Simple way to match logo"));
        return testData;
    }

    //Receive data from dialog edit topic.
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mAdapter.onActivityResult(requestCode, resultCode, data);
    }
}

