package com.example.baran_batuhan_medicineapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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


        val context = this
        //context.deleteDatabase("Medicine Database")


        getDataFromDb()
    }


    fun getDataFromDb(){

        val context = this
        val db = DBHelper(context)

        recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView

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

            val intent = Intent(this,AddNewMedicine::class.java)
             startActivity(intent)

            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()

        getDataFromDb()
    }

}