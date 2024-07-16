package com.piyush.a38_sqlite_demo;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        MyDBHelper dbHelper = new MyDBHelper(this);
        /*dbHelper.addContact("Raman", "987654321");
        dbHelper.addContact("Neel", "9876534551");
        dbHelper.addContact("Nithin", "98765434521");
        dbHelper.addContact("Mukesh", "987654355");
        dbHelper.addContact("Kartik", "987654421");
        dbHelper.addContact("Akash", "987655421");
        dbHelper.addContact("Amit", "987654331");*/

        ContactModel contactModel = new ContactModel();
        contactModel.id = 1;
        contactModel.name = "Piyush";
        contactModel.phone_no = "1234567890";
        dbHelper.updateContact(contactModel);
        dbHelper.DeleteContact(2);

        ArrayList<ContactModel> arrContacts = dbHelper.fetchContact();
        for (int i = 0; i < arrContacts.size(); i++) {
            Log.e("CONTACT_INFO", "Name: "+ arrContacts.get(i).name+", Phone Number: "+ arrContacts.get(i).phone_no);
        }
    }
}
