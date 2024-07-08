package com.piyush.a20_recycler_view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ContactModel> arrContacts = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // Set up the toolbar
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recyclerContact);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrContacts.add(new ContactModel(R.drawable.bb, "Piyush", "9426503589"));
        arrContacts.add(new ContactModel(R.drawable.a, "Pushpa", "9426503589"));
        arrContacts.add(new ContactModel(R.drawable.g, "Gopal", "9426503589"));
        arrContacts.add(new ContactModel(R.drawable.u, "Vijay", "9426503589"));
        arrContacts.add(new ContactModel(R.drawable.u, "Suresh", "9426503589"));
        arrContacts.add(new ContactModel(R.drawable.bb, "Jignesh", "9426503589"));
        arrContacts.add(new ContactModel(R.drawable.b, "Harshvardhan", "9426503589"));
        arrContacts.add(new ContactModel(R.drawable.a, "Jyoti", "9426503589"));
        arrContacts.add(new ContactModel(R.drawable.b, "Hashil", "9426503589"));
        arrContacts.add(new ContactModel(R.drawable.s, "Kalee", "9426503589"));
        arrContacts.add(new ContactModel(R.drawable.n, "Bhagvati", "9426503589"));
        arrContacts.add(new ContactModel(R.drawable.bb, "Suman", "9426503589"));
        arrContacts.add(new ContactModel(R.drawable.b, "Khilan", "9426503589"));
        arrContacts.add(new ContactModel(R.drawable.bb, "Neel", "9426503589"));
        arrContacts.add(new ContactModel(R.drawable.u, "Rajdeep", "9426503589"));
        arrContacts.add(new ContactModel(R.drawable.b, "Virat", "9426503589"));

        RecyclerContactAdapter adapter = new RecyclerContactAdapter(this, arrContacts);
        recyclerView.setAdapter(adapter);
    }
}
