package com.example.taskmanager;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class TaskService extends IntentService {

    private static final String TAG = "TaskService";

    public TaskService() {
        super("TaskService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            String taskName = intent.getStringExtra("taskName");
            Thread.sleep(3000); // Simulate task processing
            Log.d(TAG, "Task '" + taskName + "' completed.");
        } catch (InterruptedException e) {
            Log.e(TAG, "Error completing task", e);
        }
    }
}
