package com.elcode.e_store.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.elcode.e_store.CoursePage;
import com.elcode.e_store.MainActivity;
import com.elcode.e_store.OrderPage;
import com.elcode.e_store.R;
import com.elcode.e_store.model.Course;
import com.elcode.e_store.model.Order;

import java.util.List;
import java.util.Set;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    Context context;
    public List<Course> courses;

    public OrderAdapter(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View orderItems = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false);
        return new OrderAdapter.OrderViewHolder(orderItems);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.orderText.setText(courses.get(position).getTitle());
        holder.orderText.setBackgroundColor(Color.parseColor(courses.get(position).getColor()));
        holder.orderText.setBackgroundResource(R.drawable.round_background);
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(position);
                Order.items_id.remove((Integer) courses.get(position).getId());
                for (Integer c :
                Order.items_id) {
                    System.out.println(c.toString());
                }
                OrderPage.coursesList.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.orderText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CoursePage.class);

                intent.putExtra("courseImage", courses.get(position).getImg());
                intent.putExtra("coursePageBg", Color.parseColor(courses.get(position).getColor()));
                intent.putExtra("courseBg", Color.parseColor(courses.get(position).getColor()));
                intent.putExtra("courseTitle", courses.get(position).getTitle());
                intent.putExtra("courseDate", courses.get(position).getDate());
                intent.putExtra("courseLevel", courses.get(position).getLevel());
                intent.putExtra("courseText", courses.get(position).getText());
                intent.putExtra("courseId", courses.get(position).getId());


                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static final class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderText;
        ImageButton deleteBtn;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            deleteBtn = itemView.findViewById(R.id.deletBtn);
            orderText = itemView.findViewById(R.id.textView);
        }

    }
}
