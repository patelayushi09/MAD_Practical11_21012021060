package com.example.mad_practical11_21012021060


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "persons.db"
        private const val TABLE_NAME = "person"
        private const val COLUMN_ID = "id"
        private const val COLUMN_PERSON_NAME = "person_name"
        private const val COLUMN_PERSON_EMAIL_ID = "person_email_id"
        private const val COLUMN_PERSON_PHONE_NO = "person_phone_no"
        private const val COLUMN_PERSON_ADDRESS = "person_address"
        private const val COLUMN_PERSON_GPS_LAT = "person_lat"
        private const val COLUMN_GPS_LONG = "person_long"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PERSON_NAME + " TEXT,"
                + COLUMN_PERSON_EMAIL_ID + " TEXT,"
                + COLUMN_PERSON_PHONE_NO + " TEXT,"
                + COLUMN_PERSON_ADDRESS + " TEXT,"
                + COLUMN_PERSON_GPS_LAT + " TEXT,"
                + COLUMN_GPS_LONG + " TEXT)")

        if (db != null) {
            db.execSQL(CREATE_TABLE)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        }
    }

//    fun insertPersson(person: Person) : Long
//    {
//
//    }
//
//    fun deletePersson(person: Person) : Int
//    {
//
//    }
//
//    fun getAllPersons() :ArrayList<Person>
//    {
//
//    }
}