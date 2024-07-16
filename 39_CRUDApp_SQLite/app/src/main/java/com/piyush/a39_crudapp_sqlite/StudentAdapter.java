package com.piyush.a39_crudapp_sqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private ArrayList<StudentModel> studentList;

    public StudentAdapter(ArrayList<StudentModel> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        StudentModel student = studentList.get(position);
        holder.rollNoTextView.setText(String.valueOf(student.getRollNo()));
        holder.nameTextView.setText(student.getName());
        holder.addressTextView.setText(student.getAddress());
        holder.genderTextView.setText(student.getGender());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView rollNoTextView, nameTextView, addressTextView, genderTextView;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            rollNoTextView = itemView.findViewById(R.id.rollNoTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
            genderTextView = itemView.findViewById(R.id.genderTextView);
        }
    }
}
