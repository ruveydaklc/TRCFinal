package com.example.tripplanner.dal

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.tripplanner.model.ZiyaretEntity
import com.example.tripplanner.model.YerEntity

class TripPlannerOperation(context: Context) {

    var tripPlannerDatabase: SQLiteDatabase? = null
    var dbOpenHelper: DatabaseOpenHelper

    /** Constant Strings */
    val yerTableStr = "Yer"
    val yerAdiStr = "YerAdi"
    val yerTanimStr = "KisaTanim"
    val yerAciklamaStr = "KisaAciklama"
    val yerOncelikStr = "Oncelik"
    val yerZiyaretStr = "Ziyaret"
    val gezdiklerimTableStr = "Gezdiklerim"
    val gezdiklerimTarihStr = "Tarih"
    val gezdiklerimAciklamaStr = "Aciklama"

    init {
        dbOpenHelper = DatabaseOpenHelper(context, "TripPlannerDB", null, 1)
    }

    fun openDB() {
        // Open DB to write, there is a readable version too.
        tripPlannerDatabase = dbOpenHelper.writableDatabase
    }

    fun closeDB() {
        // Closes DB
        if (tripPlannerDatabase != null && tripPlannerDatabase!!.isOpen) {
            tripPlannerDatabase!!.close()
        }
    }

    @SuppressLint("Range")
    fun tumYerleriGetir(): ArrayList<YerEntity> {

        val yerList2Return: ArrayList<YerEntity> = arrayListOf()
        var yerEntity: YerEntity

        openDB()
        val dbObject = tripPlannerDatabase!!.rawQuery("SELECT * FROM $yerTableStr", null)

        if (dbObject.moveToFirst()) {
            do {
                yerEntity = YerEntity()
                // Get data from 0th column of selected row(outer).
                yerEntity.id = dbObject.getInt(0)
                // Preferred method over getting data from index. Indexes may shift.
                yerEntity.yerAdi = dbObject.getString(dbObject.getColumnIndex(yerAdiStr))
                yerEntity.kisaTanim = dbObject.getString(dbObject.getColumnIndex(yerTanimStr))
                yerEntity.kisaAciklama = dbObject.getString(dbObject.getColumnIndex(yerAciklamaStr))
                yerEntity.oncelik = dbObject.getString(dbObject.getColumnIndex(yerOncelikStr))
                yerEntity.ziyaretEdildi =
                    dbObject.getInt(dbObject.getColumnIndex(yerZiyaretStr)) > 0
                yerList2Return.add(yerEntity)
            } while (dbObject.moveToNext())
        }

        return yerList2Return
    }

    @SuppressLint("Range")
    fun yerGetir(yerId: Int): YerEntity {

        var yerEntity = YerEntity()

        openDB()
        val dbObject =
            tripPlannerDatabase!!.rawQuery("SELECT * FROM $yerTableStr WHERE Id = ${yerId}", null)

        if (dbObject.moveToFirst()) {
            do {
                yerEntity = YerEntity()
                // Get data from 0th column of selected row(outer).
                yerEntity.id = yerId
                // Preferred method over getting data from index. Indexes may shift.
                yerEntity.yerAdi = dbObject.getString(dbObject.getColumnIndex(yerAdiStr))
                yerEntity.kisaTanim = dbObject.getString(dbObject.getColumnIndex(yerTanimStr))
                yerEntity.kisaAciklama = dbObject.getString(dbObject.getColumnIndex(yerAciklamaStr))
                yerEntity.oncelik = dbObject.getString(dbObject.getColumnIndex(yerOncelikStr))
                yerEntity.ziyaretEdildi =
                    dbObject.getInt(dbObject.getColumnIndex(yerZiyaretStr)) > 0
            } while (dbObject.moveToNext())
        }
        dbObject.close()
        closeDB()

        return yerEntity
    }

    fun yerEkle(yerEntity: YerEntity): Boolean {

        val cv = ContentValues()

        cv.put(yerAdiStr, yerEntity.yerAdi)
        cv.put(yerAciklamaStr, yerEntity.kisaAciklama)
        cv.put(yerTanimStr, yerEntity.kisaTanim)
        cv.put(yerOncelikStr, yerEntity.oncelik)
        cv.put(yerZiyaretStr, yerEntity.ziyaretEdildi)

        openDB()
        val effectedRowCount = tripPlannerDatabase!!.insert(yerTableStr, null, cv)
        closeDB()

        return effectedRowCount > 0
    }


    @SuppressLint("Range")
    fun ziyaretleriGetir(yerEntity: YerEntity): ArrayList<ZiyaretEntity> {

        val ziyaretList2Return: ArrayList<ZiyaretEntity> = arrayListOf()
        var ziyaretEntity: ZiyaretEntity

        openDB()
        val dbObject = tripPlannerDatabase!!.rawQuery("SELECT * FROM $gezdiklerimTableStr WHERE Yer = ${yerEntity.id}", null)

        if (dbObject.moveToFirst()) {
            do {
                ziyaretEntity = ZiyaretEntity()
                // Get data from 0th column of selected row(outer).
                ziyaretEntity.id = dbObject.getInt(0)
                // Preferred method over getting data from index. Indexes may shift.
                ziyaretEntity.tarih =
                    dbObject.getString(dbObject.getColumnIndex(gezdiklerimTarihStr))
                ziyaretEntity.aciklama =
                    dbObject.getString(dbObject.getColumnIndex(gezdiklerimAciklamaStr))
                ziyaretEntity.yerId = dbObject.getInt(dbObject.getColumnIndex(yerTableStr))
                ziyaretList2Return.add(ziyaretEntity)
            } while (dbObject.moveToNext())
        }
        dbObject.close()
        closeDB()

        return ziyaretList2Return
    }

    @SuppressLint("Range")
    fun gezdiklerimiGetir(): ArrayList<YerEntity> {

        val gezdiklerimList2Return: ArrayList<YerEntity> = arrayListOf()
        val gezdiklerimIDList: ArrayList<Int> = arrayListOf()

        openDB()
        val dbObject = tripPlannerDatabase!!.rawQuery("SELECT * FROM $gezdiklerimTableStr", null)

        if (dbObject.moveToFirst()) {
            do {
                if (!gezdiklerimIDList.contains(dbObject.getInt(dbObject.getColumnIndex(yerTableStr)))) {
                    gezdiklerimIDList.add(dbObject.getInt(dbObject.getColumnIndex(yerTableStr)))
                }
            } while (dbObject.moveToNext())
        }
        dbObject.close()
        closeDB()

        gezdiklerimIDList.forEach {
            gezdiklerimList2Return.add(yerGetir(it))
        }

        return gezdiklerimList2Return
    }

    fun ziyaretEkle(ziyaretEntity: ZiyaretEntity): Boolean {

        val cv = ContentValues()
        cv.put(gezdiklerimTarihStr, ziyaretEntity.tarih)
        cv.put(gezdiklerimAciklamaStr, ziyaretEntity.aciklama)
        cv.put(yerTableStr, ziyaretEntity.yerId)

        // TODO any condition-ensurement

        openDB()
        val effectedRowCount = tripPlannerDatabase!!.insert(gezdiklerimTableStr, null, cv)
        closeDB()

        return effectedRowCount > 0

    }
}