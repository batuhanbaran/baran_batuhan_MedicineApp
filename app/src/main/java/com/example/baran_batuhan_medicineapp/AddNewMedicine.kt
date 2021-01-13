package com.example.baran_batuhan_medicineapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddNewMedicine : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_medicine)

        val name = findViewById<EditText>(R.id.name)
        val amount = findViewById<EditText>(R.id.amount)
        val description = findViewById<EditText>(R.id.description)
        val saveButton= findViewById<Button>(R.id.saveBtn)
        val backBButton= findViewById<Button>(R.id.backBtn)

        val context = this
        val db = DBHelper(context)


        saveButton.setOnClickListener {

            db.insertData(Medicine(name = name.text.toString(),amount = amount.text.toString(),description = description.text.toString(), flag = false))

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        backBButton.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

}