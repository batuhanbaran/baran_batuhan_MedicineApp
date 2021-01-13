package com.example.baran_batuhan_medicineapp

import android.app.AlertDialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
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

        internal var card = view.findViewById<CardView>(R.id.cardView)

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


            val mAlertDialog = AlertDialog.Builder(holder.itemView.context)

            mAlertDialog.setTitle("Are you sure you want to this record!") //set alertdialog title

            mAlertDialog.setPositiveButton("Yes") { dialog, id ->


                val databaseHandler: DBHelper = DBHelper(holder.itemView.context)
                val removedMed = Medicine(id = medicine.id,name = medicine.name,amount = medicine.amount,description = medicine.description)
                databaseHandler.deleteMedicine(removedMed)

                notifyDataSetChanged()
                dialog.dismiss()
                medicineList.removeAt(position)
                notifyItemRemoved(position)

            }


            mAlertDialog.setNegativeButton("No") { dialog, id ->

            }

            mAlertDialog.show()

        }

        holder.editButton.setOnClickListener {

            val dialog = UpdateMedicine(medicine,mainActivity)
            val manager = (holder.itemView.context as MainActivity).supportFragmentManager

            dialog.show(manager,"")


        }

        if (!medicine.flag){

            holder.card.setCardBackgroundColor(Color.rgb(255,0,0))

        }else{

            holder.card.setCardBackgroundColor(Color.rgb(0,153,76))

        }

    }

    override fun getItemCount(): Int {
        if (medicineList != null) {
            return medicineList.size
        }
        return -1
    }



}