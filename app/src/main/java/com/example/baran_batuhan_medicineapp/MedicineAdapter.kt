package com.example.baran_batuhan_medicineapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MedicineAdapter(


    private val mainActivity: MainActivity,
    val medicineList: ArrayList<Medicine>)

    : RecyclerView.Adapter<MedicineAdapter.ListItemHolder>(){



    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): ListItemHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.medicine_items, parent, false)

        return ListItemHolder(itemView)

    }
    inner class ListItemHolder(view: View) :
        RecyclerView.ViewHolder(view),
        View.OnClickListener {

        internal var name = view.findViewById<TextView>(R.id.name)

        internal var amount = view.findViewById<TextView>(R.id.amount)

        internal var description = view.findViewById<TextView>(R.id.description)

        internal var deleteButton = view.findViewById<ImageButton>(R.id.deleteBtn)

        internal var editButton = view.findViewById<ImageButton>(R.id.editBtn)

        init {
            view.isClickable = true
            view.setOnClickListener(this)
            deleteButton.setOnClickListener(this)
            editButton.setOnClickListener(this)
        }


        override fun onClick(view: View) {

            //val intentToCarPager = Intent(view!!.context, CarPagerActivity::class.java)

            //view.context.startActivity(intentToCarPager)

        }

    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {

        val medicine = medicineList!![position]

        holder.name.text = medicine.name

        holder.amount.text = medicine.amount

        holder.description.text = medicine.description

        holder.deleteButton.setOnClickListener {

            println("delete")

        }

        holder.editButton.setOnClickListener {

            println("edit")
        }
    }

    override fun getItemCount(): Int {
        if (medicineList != null) {
            return medicineList.size
        }
        return -1
    }



}