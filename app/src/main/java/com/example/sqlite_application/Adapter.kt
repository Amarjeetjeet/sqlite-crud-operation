package com.example.sqlite_application

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import android.widget.Toast


class Adapter(private val mList: List<Employeedata> , private val context : Context , private val DB : DBHelper) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewID: TextView = itemView.findViewById(R.id.text_id)
        val editText_Name: EditText = itemView.findViewById(R.id.edittext_name)
        val editText_Email: EditText = itemView.findViewById(R.id.edittext_email)
        val button_delete: Button = itemView.findViewById(R.id.button_delete)
        val button_Edit: Button = itemView.findViewById(R.id.button_edit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employeeModelClass = mList[position]
        val id = employeeModelClass.id
        holder.textViewID.text = employeeModelClass.id
        holder.editText_Name.setText(employeeModelClass.name)
        holder.editText_Email.setText(employeeModelClass.email)


        holder.button_delete.setOnClickListener(View.OnClickListener {
            DB.deleteEmployee(id)
            Toast.makeText(context, "Deleted the id $id", Toast.LENGTH_SHORT)
                .show()
            notifyItemRemoved(position);

        })

        holder.button_Edit.setOnClickListener {
            val stringName = holder.editText_Name.text.toString()
            val stringEmail = holder.editText_Email.text.toString()
            DB.updateEmployee(
                Employeedata(
                    employeeModelClass.id,
                    stringName,
                    stringEmail
                )
            )
            notifyDataSetChanged()
            (context as Activity).finish()
            context.startActivity((context as Activity).intent)
        }

    }


    override fun getItemCount(): Int {
        return mList.size
    }

}