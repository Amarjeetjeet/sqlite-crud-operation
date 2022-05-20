package com.example.sqlite_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_add.setOnClickListener(View.OnClickListener {
            val db = DBHelper(this, null)
            val name = edittext_name.text.toString()
            val email = edittext_email.text.toString()

            if (name.length <= 0 || email.length <= 0) {
                edittext_name.setError("Can't be Empty")
                edittext_name.requestFocus();
                edittext_email.setError("Can't be Empty")
                edittext_email.requestFocus();
                Toast.makeText(this@MainActivity, "Enter All Data", Toast.LENGTH_SHORT).show()
            }else{
            db.addEmployee(name, email)
            Toast.makeText(this@MainActivity, "Add Employee Successfully", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
        })

        button_view.setOnClickListener {
            val intent = Intent(this@MainActivity, view_employee::class.java)
            startActivity(intent)
        }

    }
}