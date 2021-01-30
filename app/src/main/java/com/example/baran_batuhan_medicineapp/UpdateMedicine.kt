package com.example.baran_batuhan_medicineapp

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.CheckBox
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


        //getting ui items!
        val name = dialogView.findViewById<EditText>(R.id.updateName)
        val amount = dialogView.findViewById<EditText>(R.id.updateAmount)
        val description = dialogView.findViewById<EditText>(R.id.updateDescription)
        val myCheckBox = dialogView.findViewById<CheckBox>(R.id.statusCheckBox)
        val cancelButton = dialogView.findViewById<Button>(R.id.cancelButton)
        val updateButton = dialogView.findViewById<Button>(R.id.updateButton)

        builder.setView(dialogView)


        //filling empty fields with data
        name.setText(willUpdateMedicine.name)
        amount.setText(willUpdateMedicine.amount)
        description.setText(willUpdateMedicine.description)


        //checkbox controle
        if (willUpdateMedicine.flag){

            myCheckBox.isChecked = true

        }else{

            myCheckBox.isChecked = false
        }

        updateButton.setOnClickListener {


            //condition for empty fields medicene data should not be empty!
            if (name.text.toString() == "" || amount.text.toString() == "" || description.text.toString() == ""){

                val mAlertDialog = android.app.AlertDialog.Builder(context)

                mAlertDialog.setTitle("Warning!")
                mAlertDialog.setMessage("Please fill in all fields.") //set alertdialog title


                mAlertDialog.setNegativeButton("OK") { dialog, id ->


                }

                mAlertDialog.show()

            }else{

                //if fields are not empty then update data with new values!

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

                if (myCheckBox.isChecked){

                    updatedMedicine.flag = true

                }else{

                    updatedMedicine.flag = false
                }


                //call update method and pass new object to it.
                db.updateMedicine(updatedMedicine)

                mainActivity.getDataFromDb()
                dismiss()
            }


        }

        cancelButton.setOnClickListener {

            dismiss()
        }

        return builder.create()
    }
}