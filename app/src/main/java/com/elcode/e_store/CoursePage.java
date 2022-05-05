package com.elcode.e_store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.elcode.e_store.adapter.CourseAdapter;
import com.elcode.e_store.model.Order;

public class CoursePage extends AppCompatActivity {
    public static ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);
        imageButton = findViewById(R.id.addToCard);
        LinearLayout coursePageBg = findViewById(R.id.courseBG);
        ConstraintLayout courseBg = findViewById(R.id.coursePageBg);
        ImageView courseImage = findViewById(R.id.coursePageImage);
        TextView courseTitle = findViewById(R.id.coursePageTitle);
        TextView courseDate = findViewById(R.id.coursePageDate);
        TextView courseLevel = findViewById(R.id.coursePageLevel);
        TextView courseText = findViewById(R.id.coursePageText);

        coursePageBg.setBackgroundColor(getIntent().getIntExtra("coursePageBg",0));
        courseBg.setBackgroundColor(getIntent().getIntExtra("courseBg", 0));
        Glide.with(this).load(getIntent().getStringExtra("courseImage")).into(courseImage);
        courseTitle.setText(getIntent().getStringExtra("courseTitle"));
        courseDate.setText(getIntent().getStringExtra("courseDate"));
        courseLevel.setText(getIntent().getStringExtra("courseLevel"));
        courseText.setText(getIntent().getStringExtra("courseText"));


    }

    public  void addToCard(View view){
        int item_id = getIntent().getIntExtra("courseId", 0);
        Order.items_id.add(item_id);
        Toast.makeText(this,"Добавлено в Корзину!", Toast.LENGTH_LONG).show();
    }
}