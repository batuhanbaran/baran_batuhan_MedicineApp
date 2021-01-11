package com.example.baran_batuhan_medicineapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

class DBHelper(val context: Context) : SQLiteOpenHelper(context,DBHelper.DATABASE_NAME,null,DBHelper.DATABASE_VERSION) {
    private val TABLE_NAME="Medicine"
    private val COL_ID = "id"
    private val COL_NAME = "name"
    private val COL_AMOUNT = "amount"
    private val COL_DESCRIPTION = "description"
    companion object {
        private val DATABASE_NAME = "Medicine Database"//database adı
        private val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_NAME  VARCHAR(256),$COL_AMOUNT  VARCHAR(256),$COL_DESCRIPTION  VARCHAR(256))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertData(med:Medicine){
        val sqliteDB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_NAME , med.name)
        contentValues.put(COL_AMOUNT, med.amount)
        contentValues.put(COL_DESCRIPTION, med.description)

        val result = sqliteDB.insert(TABLE_NAME,null,contentValues)

        Toast.makeText(context,if(result != -1L) "Kayıt Başarılı" else "Kayıt yapılamadı.", Toast.LENGTH_SHORT).show()
    }

    fun readData():ArrayList<Medicine>{
        val empList: ArrayList<Medicine> = ArrayList<Medicine>()

        // Query to select all the records from the table.
        val selectQuery = "SELECT  * FROM $TABLE_NAME"

        val db = this.readableDatabase
        // Cursor is used to read the record one by one. Add them to data model class.
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)

        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var amount: String
        var description: String


        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                amount = cursor.getString(cursor.getColumnIndex(COL_AMOUNT))
                description = cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION))


                val emp = Medicine(id = id,name = name,amount = amount,description = description)
                empList.add(emp)

            } while (cursor.moveToNext())
        }
        return empList
    }


    fun deleteMedicine(medicine: Medicine) {
        val db = this.writableDatabase
        var values = ContentValues()
        values.put("id", medicine.id)
        values.put("name", medicine.name)
        values.put("amount", medicine.amount)
        values.put("description", medicine.description)
        println(values)
        val retVal = db.delete("Medicine", "id = " + medicine.id, null)
        if (retVal >= 1) {
            Log.v("@@@WWe", " Record deleted")
        } else {
            Log.v("@@@WWe", " Not deleted")
        }
        db.close()

    }

    fun deleteAllData(){
        val sqliteDB = this.writableDatabase
        sqliteDB.delete(TABLE_NAME,null,null)
        sqliteDB.close()

    }


}

