package com.example.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    private static final String TAG = "AddTaskActivity";

    private EditText taskNameEditText;
    private Button saveTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Log.d(TAG, "onCreate: AddTaskActivity created");

        taskNameEditText = findViewById(R.id.taskNameEditText);
        saveTaskButton = findViewById(R.id.saveTaskButton);

        saveTaskButton.setOnClickListener(v -> {
            try {
                String taskName = taskNameEditText.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("taskName", taskName);
                setResult(RESULT_OK, intent);
                finish();
            } catch (Exception e) {
                Log.e(TAG, "Error saving task", e);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: AddTaskActivity started");
    }
}
