package com.example.tripplanner.Controller.dal

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.tripplanner.model.YerEntity

class GezilecekOperation(context: Context) {

    var tripPlannerDatabase : SQLiteDatabase? = null
    var dbOpenHelper: DatabaseOpenHelper

    /** Constant Strings */
    val yerTableStr = "Yer"
    val yerAdiStr = "YerAdi"
    val yerTanimStr = "KisaTanim"
    val yerAciklamaStr = "KisaAciklama"

    init {
        dbOpenHelper = DatabaseOpenHelper(context, "TripPlannerDB",null,1)
    }

    fun openDB(){
        // Open DB to write, there is a readable version too.
        tripPlannerDatabase = dbOpenHelper.writableDatabase
    }

    fun closeDB(){
        // Closes DB
        if(tripPlannerDatabase != null && tripPlannerDatabase!!.isOpen){
            tripPlannerDatabase!!.close()
        }
    }

    fun yerEkle(yerEntity: YerEntity) : Boolean{

        val cv = ContentValues()

        cv.put(yerAdiStr, yerEntity.yerAdi)
        cv.put(yerAciklamaStr, yerEntity.kisaAciklama)
        cv.put(yerTanimStr, yerEntity.kisaTanim)

        openDB()
        val effectedRowCount = tripPlannerDatabase!!.insert(yerTableStr, null, cv)
        closeDB()

        return effectedRowCount>0
    }
}