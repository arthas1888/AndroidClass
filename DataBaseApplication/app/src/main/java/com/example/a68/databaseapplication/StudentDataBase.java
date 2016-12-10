package com.example.a68.databaseapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class StudentDataBase extends SQLiteOpenHelper {

    private static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "_name";
    private static final String COLUMN_PHONE = "_phone";
    public static final String COLUMN_CAREER = "_career";

    private static final String TABLE_NAME = "students";
    private SQLiteDatabase database;
    private Context context;
    private String[] cols = {
            COLUMN_ID, COLUMN_NAME, COLUMN_PHONE, COLUMN_CAREER
    };

    public StudentDataBase(Context context) {
        super(context, "University", null, 1);
        this.context = context;
    }

    public void openConnection(){
        database = getWritableDatabase();
    }

    public void create(Student student){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, student.getName());
        cv.put(COLUMN_PHONE, student.getPhone());
        cv.put(COLUMN_CAREER, student.getCareer());
        database.insert(TABLE_NAME, null, cv);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME
                + " ("
                + COLUMN_ID + " integer PRIMARY KEY not null, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_PHONE + " TEXT, "
                + COLUMN_CAREER + " TEXT"
                + ")";
        db.execSQL(query);
        fillDataBase(db);
    }

    private void fillDataBase(SQLiteDatabase db) {
        ArrayList<Student> studentArrayList = new ArrayList<>();
        studentArrayList.add(new Student("Juan Perez", "123456789", "Ing. Sistemas"));
        studentArrayList.add(new Student("Daniel Asprilla", "987456321", "Ing. Mecanica"));
        studentArrayList.add(new Student("Marco Espriella", "789456123", "Ing. Electronica"));
        studentArrayList.add(new Student("Luis Gutierrez", "456789132", "Ing. Sistemas"));
        studentArrayList.add(new Student("Camilo Garcia", "564987231", "Ing. Civil"));
        for (Student student: studentArrayList) {
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_NAME, student.getName());
            cv.put(COLUMN_PHONE, student.getPhone());
            cv.put(COLUMN_CAREER, student.getCareer());
            db.insert(TABLE_NAME, null, cv);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(query);
        onCreate(db);
    }

    public Cursor getAll() {
        return database.query(TABLE_NAME, cols, null, null, null, null, null);
    }

    public Cursor getByCareer(String career) {
        String query = "SELECT * FROM students WHERE " +
                "_career LIKE '" + career +
                "'";
        return database.rawQuery(query, null);
    }

    public int updateById(Student student, int id)
    {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, student.getName());
        cv.put(COLUMN_PHONE, student.getPhone());
        cv.put(COLUMN_CAREER, student.getCareer());
        return database.update(TABLE_NAME, cv, COLUMN_ID + " = " + id, null);
    }


    public void deleteById(long id) {
        database.delete(TABLE_NAME, COLUMN_ID + " = " + id, null);
    }
}
