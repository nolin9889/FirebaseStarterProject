/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.software.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;


public class SearchActivity extends ActionBarActivity {
    Firebase mref;
    TextView textView;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

    }
    protected void onStart() {
        super.onStart();
        System.err.println("Hello from onstart");
        textView = (TextView) findViewById (R.id.TextViewTitle);
        b1 = (Button) findViewById (R.id.Send);
        // User 1 reading from /first
        mref = new Firebase ("https://nolin-110.firebaseio.com/first");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getValue (String.class);
                textView.setText(data);
                Toast.makeText(getBaseContext(), data, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                // User 1 writing to /second
                System.err.println("Hello from b1 onclick");
                mref = new Firebase("https://nolin-110.firebaseio.com/second");
                EditText editText = (EditText)findViewById(R.id.EditTextId);
                String edit = editText.getText().toString();
                mref.setValue(edit);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void search(View button)
    {
        EditText etId = (EditText) findViewById(R.id.EditTextId);
        final String idToSearch = etId.getText().toString();
        Firebase myFirebaseRef = new Firebase("https://nolin-110.firebaseio.com/student");
        Query queryRef = myFirebaseRef.orderByChild("studentId").equalTo(idToSearch);

        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot == null || snapshot.getValue() == null)
                    Toast.makeText(SearchActivity.this, "No record found", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(SearchActivity.this, snapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });
    }

    public void addRecord(View button) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
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
