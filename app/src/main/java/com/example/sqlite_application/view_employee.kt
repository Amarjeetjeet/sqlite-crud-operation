package com.example.sqlite_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_view_employee.*

class view_employee : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_employee)

        recyclerView.setLayoutManager(LinearLayoutManager(this))
        recyclerView.setHasFixedSize(true)

        val databaseHelperClass = DBHelper(this,null)
        val employeeModelClasses: List<Employeedata>? = databaseHelperClass.getEmployeeList()

        if (employeeModelClasses != null) {
            if (employeeModelClasses.size > 0) {
                val employeadapterclass =
                    Adapter(employeeModelClasses, this,databaseHelperClass)
                recyclerView.setAdapter(employeadapterclass)
            } else {
                Toast.makeText(this, "There is no employee in the database", Toast.LENGTH_SHORT).show()
            }
        }
    }
}