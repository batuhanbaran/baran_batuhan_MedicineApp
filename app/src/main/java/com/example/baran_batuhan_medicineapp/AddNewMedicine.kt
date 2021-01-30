package com.example.baran_batuhan_medicineapp

import android.app.AlertDialog
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

        //reachin ui elements
        val name = findViewById<EditText>(R.id.name)
        val amount = findViewById<EditText>(R.id.amount)
        val description = findViewById<EditText>(R.id.description)
        val saveButton= findViewById<Button>(R.id.saveBtn)
        val backBButton= findViewById<Button>(R.id.backBtn)

        val context = this
        val db = DBHelper(context)


        saveButton.setOnClickListener {


            //condition for empty fields for avoiding crashes
            if (name.text.toString() != "" && amount.text.toString() != "" && description.text.toString() != ""){

                db.insertData(Medicine(name = name.text.toString(),amount = amount.text.toString(),description = description.text.toString(), flag = false))

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }else{

                val mAlertDialog = AlertDialog.Builder(context)

                mAlertDialog.setTitle("Warning!")
                mAlertDialog.setMessage("Please fill in all fields.") //set alertdialog title


                mAlertDialog.setNegativeButton("OK") { dialog, id ->


                }

                mAlertDialog.show()

            }


        }

        backBButton.setOnClickListener {

            //back to mainactivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

}