package com.example.baran_batuhan_medicineapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var adapter: MedicineAdapter? = null
    private var medicineList : ArrayList<Medicine> = arrayListOf()
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //func for dbControl texts
        isDatabaseEmpty()
    }


    fun getDataFromDb(){

        val context = this
        val db = DBHelper(context)

        recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView


        //content of recylerview shown with returned data from DatabaseHandler class!
        adapter = MedicineAdapter(this, db.readData())


        val layoutManager = LinearLayoutManager(applicationContext)

        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()

        // set the adapter
        recyclerView!!.adapter = adapter

        adapter!!.notifyDataSetChanged()

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.addBtn -> {


            //add button
            val intent = Intent(this,AddNewMedicine::class.java)
             startActivity(intent)

            true
        }
        else -> super.onOptionsItemSelected(item)
    }

     fun isDatabaseEmpty(){

         val context = this
         val db = DBHelper(context)
         val dbControl = findViewById<TextView>(R.id.isDbEmpty)

         if (db.readData().isEmpty()){

             dbControl.isVisible = true

         }else{

             //get data from db if it is available

             dbControl.isVisible = false
             getDataFromDb()
         }
    }


}