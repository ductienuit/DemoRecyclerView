package com.example.ductien.viewlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ductien.viewlist.adapter.CategoryAdapter;
import com.example.ductien.viewlist.models.TopicObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.quiz_category);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        CategoryAdapter mAdapter = new CategoryAdapter(MainActivity.this, getTestData());
        recyclerView.setAdapter(mAdapter);
    }
    private static final String TAG = MainActivity.class.getSimpleName();

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
}

