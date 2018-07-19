package com.example.ductien.viewlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DialogEditTopic extends Activity {

    Button btnSave;
    EditText topic_name;
    EditText topic_description;
    private int position;
    private boolean flagChangeText;
    public static final String EXTRA_DATA = "EXTRA_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_edit_topic);
        addControl();
        receiveData();
        addEvent();
        flagChangeText=false;
        position=-1;
    }

    private void receiveData() {
        Intent intent = getIntent();
        Bundle topicData = intent.getBundleExtra("DetailTopic");
        topic_name.setText(topicData.getString("name"));
        topic_description.setText(topicData.getString("description"));
    }

    private void addEvent() {

        topic_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                flagChangeText=true;
            }
        });
        topic_description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                flagChangeText=true;
            }
        });
        topic_name.removeTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                flagChangeText=true;
            }
        });
        topic_description.removeTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                flagChangeText=true;
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = topic_description.getText().toString();
                String name = topic_name.getText().toString();
                Intent intent = new Intent();

                intent.putExtra("name",name);
                intent.putExtra("description",description);
                intent.putExtra(EXTRA_DATA, "Some interesting data!");

                if(flagChangeText)
                  setResult(Activity.RESULT_OK,intent);

                finish();           //finishing activity
            }
        });
    }

    private void addControl() {
        btnSave = (Button) findViewById(R.id.btnSave);
        topic_name = findViewById(R.id.ed_topic_name);
        topic_description = findViewById(R.id.ed_topic_description);
    }
}