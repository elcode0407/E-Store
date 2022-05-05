package com.elcode.e_store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.elcode.e_store.adapter.OrderAdapter;
import com.elcode.e_store.model.Course;
import com.elcode.e_store.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderPage extends AppCompatActivity {
    Context context;
    RecyclerView orderRecycler;
    OrderAdapter orderAdapter;
    public static List<Course> coursesList = new ArrayList<>();;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_order_page);

//        ListView orders_list = findViewById(R.id.orders_list);
        coursesList.clear();
        for(Course c: MainActivity.fullCourseList){
            if(Order.items_id.contains(c.getId()))
                coursesList.add(c);
        }

//        orders_list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, coursesTitle));
        setCourseRecycler(coursesList);

    }

    private void setCourseRecycler(List<Course> courseList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        orderRecycler = findViewById(R.id.orders_list);
        orderRecycler.setLayoutManager(layoutManager);

        orderAdapter = new OrderAdapter(this, courseList);
        orderRecycler.setAdapter(orderAdapter);

    }
}