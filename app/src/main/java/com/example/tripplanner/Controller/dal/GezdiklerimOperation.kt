package com.example.tripplanner.Controller.dal

import android.content.Context
import android.database.sqlite.SQLiteDatabase

class GezdiklerimOperation(context: Context) {

    var TripPlannerDatabase : SQLiteDatabase? = null
    var dbOpenHelper: DatabaseOpenHelper

    val dbName="TripPlannerDB"
    val tableName="Yer"

    init {
        dbOpenHelper = DatabaseOpenHelper(context, dbName,null,1)
    }

    fun open(){
        // Open DB to write, there is a readable version too.
        TripPlannerDatabase = dbOpenHelper.writableDatabase
    }

    fun close(){
        // Closes DB
        if(TripPlannerDatabase != null && TripPlannerDatabase!!.isOpen){
            TripPlannerDatabase!!.close()
        }
    }

}