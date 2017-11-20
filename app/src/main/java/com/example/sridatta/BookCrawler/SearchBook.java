package com.example.sridatta.BookCrawler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;


//old  guy display all that in the db

public class SearchBook extends AppCompatActivity {

    private TextView search;
    private Firebase mRef;
    private ListView mListView;
    private ArrayList<String> mUserNames=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);

        mRef=new Firebase("https://mylogin-42311.firebaseio.com/");


       // search =(TextView) findViewById(R.id.tv_search);

        mListView=(ListView)findViewById(R.id.lv_catelog);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mUserNames);
        mListView.setAdapter(arrayAdapter);

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value=dataSnapshot.getValue(String.class);
                mUserNames.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

/*
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //if there is any change in the database that is stored as datasnapshot

            //String value =dataSnapshot.getValue(String.class);
              //  search.setText(value);
                Map<String,String> map = dataSnapshot.getValue(Map.class);
                String name=map.get("Name");
                String age=map.get("Age");
                String profession=map.get("Profession");

                 Log.v("E_VALUE","Name : "+ name);
                Log.v("E_VALUE","Age : "+ age);
                Log.v("E_VALUE","Profession : "+ profession);





            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

                //incase of error this is called

            }
        });

        */
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.action_add){
            startActivity(new Intent(SearchBook.this,addBook.class));
        }

        return super.onOptionsItemSelected(item);
    }


    public void backToMain(View v) {
        startActivity(new Intent(SearchBook.this,addBook.class));

    }
}
