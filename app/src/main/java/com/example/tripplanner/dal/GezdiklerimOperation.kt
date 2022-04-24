package com.example.tripplanner.dal

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.tripplanner.bll.TripPlannerLogic
import com.example.tripplanner.model.GezdiklerimEntity
import com.example.tripplanner.model.YerEntity

class GezdiklerimOperation(context: Context) {


    var tripPlannerDatabase : SQLiteDatabase? = null
    var dbOpenHelper: DatabaseOpenHelper

    /** Constant Strings */
    val gezdiklerimTableStr = "Gezdiklerim"
    val yerTableStr = "Yer"
    val yerAdiStr = "YerAdi"
    val yerTanimStr = "KisaTanim"
    val yerAciklamaStr = "KisaAciklama"
    val gezdiklerimTarihStr = "Tarih"
    val gezdiklerimAciklamaStr = "Aciklama"

    init {
        dbOpenHelper = DatabaseOpenHelper(context, "TripPlannerDB",null,1)
    }

    fun openDB(){
        tripPlannerDatabase = dbOpenHelper.writableDatabase
    }

    fun closeDB(){
        if(tripPlannerDatabase != null && tripPlannerDatabase!!.isOpen){
            tripPlannerDatabase!!.close()
        }
    }

    fun ziyaretEkle(gezdiklerimEntity: GezdiklerimEntity) : Boolean{

        val cv = ContentValues()
        cv.put(gezdiklerimTarihStr, TripPlannerLogic.persistDate(gezdiklerimEntity.tarih))
        cv.put(gezdiklerimAciklamaStr,gezdiklerimEntity.aciklama)
        cv.put(yerTableStr,gezdiklerimEntity.yerId)

        // TODO any condition-ensurement

        openDB()
        val effectedRowCount = tripPlannerDatabase!!.insert(gezdiklerimTableStr, null, cv)
        closeDB()

        return effectedRowCount>0

    }

}