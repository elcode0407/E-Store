package com.elcode.e_store;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.elcode.e_store.adapter.CategoryAdapter;
import com.elcode.e_store.adapter.CourseAdapter;
import com.elcode.e_store.model.Category;
import com.elcode.e_store.model.Course;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView categoryRecycler,courseRecycler;
    CategoryAdapter categoryAdapter;
    CourseAdapter courseAdapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("courses");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1,"Игры"));
        categoryList.add(new Category(2,"Сайты"));
        categoryList.add(new Category(3,"Языки"));
        categoryList.add(new Category(4,"Прочее"));
        setCategoryRecycler(categoryList);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Course> courseList = new ArrayList<>();;
                for (DataSnapshot u :
                        snapshot.getChildren()) {
                    courseList.add(u.getValue(Course.class));


                }
                for (Course course:
                     courseList) {
                    System.out.println(course.toString());
                }
                setCourseRecycler(courseList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private void setCourseRecycler(List<Course> courseList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);

        courseRecycler = findViewById(R.id.courseList);
        courseRecycler.setLayoutManager(layoutManager);

        courseAdapter = new CourseAdapter(this,courseList);
        courseRecycler.setAdapter(courseAdapter);

    }

    private void setCategoryRecycler(List<Category> categoryList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this,categoryList);
        categoryRecycler.setAdapter(categoryAdapter);

    }

}