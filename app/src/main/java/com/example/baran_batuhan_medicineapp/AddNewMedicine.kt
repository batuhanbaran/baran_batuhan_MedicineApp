package com.example.baran_batuhan_medicineapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddNewMedicine : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_medicine)

        val name = findViewById<EditText>(R.id.name)
        val amount = findViewById<EditText>(R.id.amount)
        val description = findViewById<EditText>(R.id.description)
        val saveButton= findViewById<Button>(R.id.saveBtn)

        val context = this
        val db = DBHelper(context)

        saveButton.setOnClickListener {

            db.insertData(Medicine(name = name.text.toString(),amount = amount.text.toString(),description = description.text.toString()))

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}