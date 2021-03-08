package com.example.companiasapp.utils

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.companiasapp.model.CompaniasData

val DATABASE_NAME = "CompaniasDB"
val TABLE_NAME = "Companias"
val ID_COMPANIA = "Id"
val COMPANIA = "Compania"
val DESCRIPCION = "Descripcion"
val IMAGEN = "imagen"

class SaveData(var context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME +" (" +
                ID_COMPANIA + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COMPANIA + " VARCHAR(50)," +
                DESCRIPCION + " VARCHAR(50)," +
                IMAGEN + " VARCHAR(256))"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        
    }

    fun insertData(companiasData: CompaniasData) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COMPANIA, companiasData.compania)
        cv.put(DESCRIPCION, companiasData.description)
        cv.put(IMAGEN, companiasData.img)
        val result = db.insert(TABLE_NAME, null, cv)
        if (result == (-1).toLong())
            Toast.makeText(context, "ERROR EN LA BASE DE DATOS", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "DATOS GUARDADOS", Toast.LENGTH_SHORT).show()
    }
}