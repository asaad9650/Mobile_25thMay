package com.example.doorlock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


//class ImageModel {
//    String imgUrl;
//    String date;
//
//    public ImageModel() {
//    }
//
//    public ImageModel(String imgUrl, String date) {
//        this.imgUrl = imgUrl;
//        this.date = date;
//    }
//
//    public String getImgUrl() {
//        return imgUrl;
//    }
//
//    public void setImgUrl(String imgUrl) {
//        this.imgUrl = imgUrl;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//}
public class showimages extends AppCompatActivity {
//    GridView gridView;
    private DatabaseReference ref;
//    String[] urls;
//    String[] dates;
    ArrayList<SubjectData> arrayList = new ArrayList<>();
    ListView list;
    CustomAdapter customAdapter;
//    ArrayList<String> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showimages);

        list = findViewById(R.id.list);

        this.ref = FirebaseDatabase.getInstance().getReference().child("image");


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String url = String.valueOf(dataSnapshot.child("imgUrl").getValue());
                    String date = String.valueOf(dataSnapshot.child("date").getValue());
                    arrayList.add(new SubjectData(url, date));
//                    Toast.makeText(showimages.this, arrayList.toString() , Toast.LENGTH_LONG).show();
                    customAdapter = new CustomAdapter(showimages.this, arrayList);
                    list.setAdapter(customAdapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ////                String selectedName = (String) images.get(i);
                String selectedImage = arrayList.get(i).imgUrl;
                startActivity(new Intent(showimages.this, ClickedImageActivity.class).putExtra("name", selectedImage));
            }
        });

//        arrayList.add(new SubjectData( "https://www.tutorialspoint.com/java/images/java-mini-logo.jpg" , "JAVA"));
//        arrayList.add(new SubjectData("Python", "https://www.tutorialspoint.com/python/", "https://www.tutorialspoint.com/python/images/python-mini.jpg"));
//        arrayList.add(new SubjectData("Javascript", "https://www.tutorialspoint.com/javascript/", "https://www.tutorialspoint.com/javascript/images/javascript-mini-logo.jpg"));
//        arrayList.add(new SubjectData("Cprogramming", "https://www.tutorialspoint.com/cprogramming/", "https://www.tutorialspoint.com/cprogramming/images/c-mini-logo.jpg"));
//        arrayList.add(new SubjectData("Cplusplus", "https://www.tutorialspoint.com/cplusplus/", "https://www.tutorialspoint.com/cplusplus/images/cpp-mini-logo.jpg"));
//        arrayList.add(new SubjectData("Android", "https://www.tutorialspoint.com/android/", "https://www.tutorialspoint.com/android/images/android-mini-logo.jpg"));


    }
}


class CustomAdapter implements ListAdapter {
    ArrayList<SubjectData> arrayList;
    Context context;
    public CustomAdapter(Context context, ArrayList<SubjectData> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }
    @Override
    public boolean isEnabled(int position) {
        return true;
    }
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SubjectData subjectData = arrayList.get(position);
//        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.row, null);
//            convertView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                }
//            });
            TextView tittle = convertView.findViewById(R.id.title);
            ImageView imag = convertView.findViewById(R.id.list_image);
            tittle.setText(subjectData.date);
            Picasso.get().load(subjectData.imgUrl).into(imag);
//        }
            return convertView;
//        }
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        return arrayList.size();
    }
    @Override
    public boolean isEmpty() {
        return false;
    }
}

class SubjectData {
    String imgUrl;
    String date;

    public SubjectData(String imgUrl, String date) {
        this.imgUrl = imgUrl;
        this.date = date;
    }
}
//        gridView = findViewById(R.id.gridView);
//        this.ref = FirebaseDatabase.getInstance().getReference().child("image");
//
//
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    String value = String.valueOf(dataSnapshot.child("imgUrl").getValue());
////
//                    images.add(value);
//                    Toast.makeText(showimages.this, images.toString(), Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        CustomAdapter customAdapter = new CustomAdapter(images, this);
//        gridView.setAdapter(customAdapter);
//
//
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                String selectedName = (String) images.get(i);
//                String selectedImage = images.get(i);
//                startActivity(new Intent(showimages.this, ClickedImageActivity.class).putExtra("name", selectedImage));
//            }
//        });
//    }
//
//    public class CustomAdapter extends BaseAdapter {
//        private ArrayList<String> imagesPhoto;
//        private Context context;
//        private LayoutInflater layoutInflater;
//
//        public CustomAdapter(ArrayList<String> imagesPhoto, Context context) {
//            this.imagesPhoto = imagesPhoto;
//            this.context = context;
//            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
//        }
//
//        @Override
//        public int getCount() {
//            return imagesPhoto.size();
//        }
//
//        @Override
//        public Object getItem(int i) {
//            return i;
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//            if (view == null) {
//                view = layoutInflater.inflate(R.layout.row, viewGroup, false);
//            }
//            ImageView imagePhoto = view.findViewById(R.id.imageView);
//            Picasso.get().load(images.get(i)).into(imagePhoto);
//            return view;
//        }
//    }
//}
