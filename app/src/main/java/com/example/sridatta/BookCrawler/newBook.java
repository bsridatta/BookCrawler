package com.example.sridatta.BookCrawler;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.UUID;

public class newBook extends AppCompatActivity {
//empty image slot
    private ImageButton mSelectImage;

    // input entities
    private Uri imageUri=null;
    private EditText title;
    private EditText publisher;
    private EditText author;
    private EditText location ;
    private Button addBook;
    private ProgressDialog mProgress;
//firebase
    private StorageReference mStorage;
    //gallery
    private static final int GALLERY_REQUEST =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);

        mStorage=FirebaseStorage.getInstance().getReference();

        //firebase linkage
        // inside  library>>Books>>BookAddingNow

//        mRootRef=new Firebase("https://mylogin-42311.firebaseio.com/Library/books");

  //mFireStorage= new FirebaseStorage("gs://mylogin-42311.appspot.com");

        mProgress=new ProgressDialog(this);

        mSelectImage=(ImageButton) findViewById(R.id.imageBook);

        addBook=(Button)findViewById(R.id.btn_addBook);
// fields to be filled
        location=(EditText)findViewById(R.id.et_addLocation);
        title=(EditText)findViewById(R.id.et_addTitle);
        publisher=(EditText)findViewById(R.id.et_addPublisher);
        author=(EditText)findViewById(R.id.et_addAuthor);

        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent =new Intent(Intent.ACTION_GET_CONTENT);
                    galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_REQUEST);
            }
        });


// the process of adding

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startAdding();


                // stacking inside  library>>Books>>BookAddingNow
/*
                Firebase childRef =mRootRef.push();
                childRef.child("title").setValue(sTitle);
                childRef.child("author").setValue(sAuthor);
                childRef.child("publisher").setValue(sPublisher);
                childRef.child("code").setValue(sCode);
*/


            }
        });


    }



    private void startAdding(){
        mProgress.setMessage("adding to the catelog");
        mProgress.show();
        String sTitle=title.getText().toString().trim();
        String sPublisher=publisher.getText().toString().trim();
        String sAuthor=author.getText().toString().trim();
        String sLocation=location.getText().toString().trim();
        String ImageName="book";
        //universal unique identifier
        ImageName= UUID.randomUUID().toString();
        if(!TextUtils.isEmpty(sTitle)&&!TextUtils.isEmpty(sPublisher)&&!TextUtils.isEmpty(sAuthor)&&!TextUtils.isEmpty(sLocation) && imageUri!=null){

            StorageReference filePath = mStorage.child("CatelogBook_Images").child(ImageName);

            filePath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override

                //on failure also needed
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl= taskSnapshot.getDownloadUrl();

                    mProgress.dismiss();
                }
            });
        }
        /*
           Firebase childRef =mRootRef.push();
                childRef.child("title").setValue(sTitle);
         */

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERY_REQUEST && resultCode== RESULT_OK){
            imageUri=data.getData();

            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(200,350)
                    .start(this);
            mSelectImage.setImageURI(imageUri);
        }
    }



}
