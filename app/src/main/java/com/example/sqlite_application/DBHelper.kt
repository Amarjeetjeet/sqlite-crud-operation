package com.example.sqlite_application

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.ArrayList

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {

        val query =("CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT," +
                EMAIL + " TEXT" + ")")


        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    // This method is for adding data in our database
    fun addEmployee(name: String, email: String ){
        val values = ContentValues()
        values.put(NAME, name)
        values.put(EMAIL, email)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }


    fun getEmployeeList(): List<Employeedata>? {
        val sql = "select * from " + TABLE_NAME
        val db = this.readableDatabase
        val storeEmployee: MutableList<Employeedata> = ArrayList<Employeedata>()
        val cursor: Cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(0)
                val name = cursor.getString(1)
                val email = cursor.getString(2)
                storeEmployee.add(Employeedata( id ,name, email))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return storeEmployee
    }


    fun updateEmployee(employeeModelClass: Employeedata) {
        val contentValues = ContentValues()
        contentValues.put(
            NAME,
            employeeModelClass.name
        )
        contentValues.put(
          EMAIL,
            employeeModelClass.email
        )
        val Db = this.writableDatabase
        Db.update(
            TABLE_NAME,
            contentValues,
            ID + " = ?",
            arrayOf<String>(java.lang.String.valueOf(employeeModelClass.id))
        )
    }

    fun deleteEmployee(id: String) {
        val db = this.readableDatabase
        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(
            TABLE_NAME, ID + " = ? ",
            arrayOf(id)
        )
        db.close();

    }

    companion object{
        // here we have defined variables for our database

        //Database version
        private val DATABASE_VERSION = 1

        //Database Name
        private val DATABASE_NAME = "employee_database"

        //Database Table name
        val TABLE_NAME = "EMPLOYEE"

        //Table columns
        val ID = "id"
        val NAME = "name"
        val EMAIL = "email"

    }
}