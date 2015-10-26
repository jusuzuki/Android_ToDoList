package com.example.guest.tasklist.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.example.guest.tasklist.R;
import com.example.guest.tasklist.models.Category;
import com.example.guest.tasklist.models.Task;

import java.util.ArrayList;

public class CategoryActivity extends ListActivity {

    private Category mCategory;
    private ArrayList<String> mTasks;
    private Button mNewTaskButton;
    private EditText mNewTaskText;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

    String name = getIntent().getStringExtra("categoryName");
    mCategory = Category.find(name);

    mNewTaskButton = (Button) findViewById(R.id.newTaskButton);
    mNewTaskText = (EditText) findViewById(R.id.newTask);

    mTasks = new ArrayList<String>();
    for ( Task task : mCategory.tasks() ) {
        mTasks.add(task.getDescription());
    }

    mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mTasks);
    setListAdapter(mAdapter);


    mNewTaskButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addTask();
        }
    });
}

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String taskInDatabase = mAdapter.getItem(position);
        Task taskToDelete = Task.find(taskInDatabase);
        taskToDelete.delete();
        mAdapter.remove(taskInDatabase);
        mAdapter.notifyDataSetChanged();
    }


    private void addTask() {
        String description =  mNewTaskText.getText().toString();
        Task newTask = new Task(description, mCategory);
        newTask.save();
        mTasks.add(description);
        mAdapter.notifyDataSetChanged();
        mNewTaskText.setText("");
    }

}
