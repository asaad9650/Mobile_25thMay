package com.example.doorlock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ClickedImageActivity extends AppCompatActivity {
    ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicked_image);
        imgView = findViewById(R.id.imageView_clicked);

        Intent intent = getIntent() ;

        if(intent.getExtras()!= null){
            String selectedImage = intent.getStringExtra("name");
            Picasso.get().load(selectedImage).into(imgView);
        }
    }
}