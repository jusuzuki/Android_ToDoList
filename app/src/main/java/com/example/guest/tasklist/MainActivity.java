package com.example.guest.tasklist;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.guest.tasklist.R;

import java.util.ArrayList;


public class MainActivity extends ListActivity {

   private ArrayList<String> mTasks;
   private Button mNewTaskButton;
   private EditText mNewTaskText;
   private ArrayAdapter<String> mAdapter;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);

       mNewTaskButton = (Button) findViewById(R.id.newTaskButton);
       mNewTaskText = (EditText) findViewById(R.id.newTask);
       mTasks = new ArrayList<String>();

       mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mTasks);
       setListAdapter(mAdapter);

       mNewTaskButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               addTask();
           }
       });
   }

   private void addTask() {
       String newTask =  mNewTaskText.getText().toString();
       mTasks.add(newTask);
       mAdapter.notifyDataSetChanged();
   }
}