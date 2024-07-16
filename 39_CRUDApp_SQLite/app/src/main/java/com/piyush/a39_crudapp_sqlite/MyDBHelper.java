package com.piyush.a39_crudapp_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "StudentDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_STUDENT = "students";
    private static final String KEY_ROLLNO = "rollNo";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_GENDER = "gender";

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_STUDENT + "("
                + KEY_ROLLNO + " INTEGER PRIMARY KEY, "
                + KEY_NAME + " TEXT, "
                + KEY_ADDRESS + " TEXT, "
                + KEY_GENDER + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(sqLiteDatabase);
    }

    public void addStudent(StudentModel student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ROLLNO, student.getRollNo());
        values.put(KEY_NAME, student.getName());
        values.put(KEY_ADDRESS, student.getAddress());
        values.put(KEY_GENDER, student.getGender());

        db.insert(TABLE_STUDENT, null, values);
        db.close(); // Closing the database after operation is completed
    }

    public ArrayList<StudentModel> fetchStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENT, null);

        ArrayList<StudentModel> arrStudents = new ArrayList<>();

        while (cursor.moveToNext()) {
            StudentModel student = new StudentModel();
            student.setRollNo(cursor.getInt(0));
            student.setName(cursor.getString(1));
            student.setAddress(cursor.getString(2));
            student.setGender(cursor.getString(3));
            arrStudents.add(student);
        }
        cursor.close();
        return arrStudents;
    }

    public void updateStudent(StudentModel student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());
        values.put(KEY_ADDRESS, student.getAddress());
        values.put(KEY_GENDER, student.getGender());

        db.update(TABLE_STUDENT, values, KEY_ROLLNO + "=?", new String[]{String.valueOf(student.getRollNo())});
        db.close();
    }

    public void deleteStudent(int rollNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENT, KEY_ROLLNO + "=?", new String[]{String.valueOf(rollNo)});
        db.close();
    }
}
