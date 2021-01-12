package com.example.baran_batuhan_medicineapp

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_update_medicine.*


class UpdateMedicine(med: Medicine, mainActivity: MainActivity): DialogFragment() {

    var willUpdateMedicine = med

    var mainActivity = mainActivity

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity!!)

        val inflater = activity!!.layoutInflater

        val dialogView = inflater.inflate(R.layout.dialog_update_medicine, null)

        val name = dialogView.findViewById<EditText>(R.id.updateName)
        val amount = dialogView.findViewById<EditText>(R.id.updateAmount)
        val description = dialogView.findViewById<EditText>(R.id.updateDescription)
        val cancelButton= dialogView.findViewById<Button>(R.id.cancelButton)
        val updateButton= dialogView.findViewById<Button>(R.id.updateButton)

        builder.setView(dialogView)

        name.setText(willUpdateMedicine.name)
        amount.setText(willUpdateMedicine.amount)
        description.setText(willUpdateMedicine.description)

        updateButton.setOnClickListener {

            val context = this.context
            val db = DBHelper(context!!)

            val newName = name.text.toString()
            val newAmount = amount.text.toString()
            val newDescripton = description.text.toString()


            val updatedMedicine = Medicine()

            updatedMedicine.id = willUpdateMedicine.id
            updatedMedicine.name = newName
            updatedMedicine.amount = newAmount
            updatedMedicine.description = newDescripton


            db.updateMedicine(updatedMedicine)

            dismiss()

            mainActivity.getDataFromDb()
        }

        cancelButton.setOnClickListener {

            dismiss()
        }

        return builder.create()
    }
}