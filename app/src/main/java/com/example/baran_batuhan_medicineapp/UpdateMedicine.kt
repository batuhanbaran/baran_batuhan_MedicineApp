package com.example.baran_batuhan_medicineapp

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_update_medicine.*


class UpdateMedicine(med: Medicine): DialogFragment() {

    var willUpdateMedicine = med

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

        name.text = willUpdateMedicine.name.toEditable()
        amount.text = willUpdateMedicine.amount.toEditable()
        description.text = willUpdateMedicine.description.toEditable()


        return builder.create()
    }
    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
}