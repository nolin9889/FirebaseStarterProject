package com.software.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void submit(View button){

        EditText etName = (EditText) findViewById(R.id.EditTextName);
        EditText etId = (EditText) findViewById(R.id.EditTextId);
        final String name = etName.getText().toString();
        final String id = etId.getText().toString();

        System.err.println("Above new Firebase");
        Firebase myFirebaseRef = new Firebase("https://nolin-110.firebaseio.com/student");
        Student student = new Student();
        student.setName(name);
        student.setStudentId(id);
        myFirebaseRef.child(student.getStudentId()).setValue(student);
/*

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.err.println("onClick in MainActivity is working!");
                Firebase myFirebaseRef = new Firebase("https://nolin-110.firebaseio.com/student");
                Student student = new Student();
                student.setName(name);
                student.setStudentId(id);
                myFirebaseRef.child(student.getStudentId()).setValue(student);
            }
        });  */


        Intent output = new Intent();
        setResult(RESULT_OK, output);
        finish();
        //Button button2 = (Button) findViewById(R.id.Submit);

      //  final Firebase myFirebaseRef = new Firebase("https://nolin-110.firebaseio.com/student");





    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
