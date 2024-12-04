package com.example.taskmanager;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.taskmanager.model.Task;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Nadav";

    private ListView taskListView;
    private Button addTaskButton;
    private ArrayList<Task> taskList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: MainActivity created");

        // Initialize views and data
        taskListView = findViewById(R.id.taskListView);
        addTaskButton = findViewById(R.id.addTaskButton);
        taskList = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getTaskDescriptions());
        taskListView.setAdapter(adapter);

        // Add task button listener
        addTaskButton.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivityForResult(intent, 1);
            } catch (Exception e) {
                Log.e(TAG, "Error starting AddTaskActivity", e);
            }
        });

        // Task list click listener for completion dialog
        taskListView.setOnItemClickListener((parent, view, position, id) -> showCompleteTaskDialog(position));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: MainActivity started");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: MainActivity paused");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: MainActivity restarted");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: MainActivity stoped");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            try {
                // Retrieve task name from the Intent
                String taskName = data.getStringExtra("taskName");
                if (taskName != null && !taskName.trim().isEmpty()) {
                    // Add new task to the list and update ListView
                    taskList.add(new Task(taskName));
                    updateTaskList();
                } else {
                    Log.w(TAG, "Task name was null or empty.");
                }
            } catch (Exception e) {
                Log.e(TAG, "Error adding task from AddTaskActivity", e);
            }
        }
    }

    private void showCompleteTaskDialog(int position) {
        Task task = taskList.get(position);
        new AlertDialog.Builder(this)
                .setTitle("Complete Task")
                .setMessage("Are you sure you want to complete '" + task.getName() + "'?")
                .setPositiveButton("Yes", (dialog, which) -> completeTask(task))
                .setNegativeButton("No", null)
                .show();
    }

    private void completeTask(Task task) {
        try {
            Intent intent = new Intent(this, TaskService.class);
            intent.putExtra("taskName", task.getName());
            startService(intent);
        } catch (Exception e) {
            Log.e(TAG, "Error starting TaskService", e);
        }
    }

    private ArrayList<String> getTaskDescriptions() {
        ArrayList<String> descriptions = new ArrayList<>();
        for (Task task : taskList) {
            descriptions.add(task.getDescription());
        }
        return descriptions;
    }

    private void updateTaskList() {
        adapter.clear();
        adapter.addAll(getTaskDescriptions());
        adapter.notifyDataSetChanged();
    }
}
