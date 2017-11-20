package com.example.sridatta.BookCrawler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//under construction

public class RecycleView extends AppCompatActivity {


    private RecyclerView rv_allbooks;
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
// getting the book
mDatabase= FirebaseDatabase.getInstance().getReference().child("books");

// making it as a card
        rv_allbooks=(RecyclerView) findViewById(R.id.rv_recycleView);
        rv_allbooks.setHasFixedSize(true);
        rv_allbooks.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<RetreiveBooks,RecycleViewHolder> firebaseRecyclerAdapter =new FirebaseRecyclerAdapter<RetreiveBooks, RecycleViewHolder>(
// the view :p
            RetreiveBooks.class,
                R.layout.activity_row_view,
                RecycleViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(RecycleViewHolder viewHolder, RetreiveBooks model, int position) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setAuthor(model.getAuthor());
                viewHolder.setCode(model.getCode());
                viewHolder.setPublisher(model.getPublisher());

            }
        };


        rv_allbooks.setAdapter(firebaseRecyclerAdapter);

    }

    // adding entities into the card i guess

    public static class RecycleViewHolder extends RecyclerView.ViewHolder {

        View mView;
        public RecycleViewHolder(View itemView) {
            super(itemView);

        mView=itemView;
        }

        public void setTitle(String title){

            TextView tvTitle =(TextView)mView.findViewById(R.id.tv_title);
            tvTitle.setText(title);

        }

        public void setPublisher(String publisher){

            TextView tvPublisher =(TextView)mView.findViewById(R.id.tv_publisher);
            tvPublisher.setText(publisher);

        }

        public void setCode(String code){

            TextView tvCode =(TextView)mView.findViewById(R.id.tv_code);
            tvCode.setText(code);

        }

        public void setAuthor(String author){

            TextView tvAuthor =(TextView)mView.findViewById(R.id.tv_author);
            tvAuthor.setText(author);

        }





    }
//floating bar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
//floating bar

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //floating bar
        if(item.getItemId()==R.id.action_add){
            startActivity(new Intent(RecycleView.this,addBook.class));
        }

        return super.onOptionsItemSelected(item);
    }



}
