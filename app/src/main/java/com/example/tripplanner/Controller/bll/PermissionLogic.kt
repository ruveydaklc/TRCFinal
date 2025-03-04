package com.example.tripplanner.bll

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.tripplanner.view.activities.PermissionActivity

class PermissionLogic {

    companion object{
        private val reqCodeLocations=0

        @RequiresApi(Build.VERSION_CODES.M)
        fun locationPermissionControl(
            activity:PermissionActivity,
            context: Context) {//TODO:
            val requestList = ArrayList<String>()
            var permStatus =
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            if (!permStatus) {
                requestList.add(Manifest.permission.ACCESS_FINE_LOCATION)
            }
            permStatus = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
            if (!permStatus) {
                requestList.add(Manifest.permission.ACCESS_COARSE_LOCATION)
            }
            if (requestList.size == 0) {
                activity.grantedFunc()
            } else {
                activity.requestPermissions(requestList.toTypedArray(), reqCodeLocations)
            }
        }

        @RequiresApi(Build.VERSION_CODES.M)
        fun mediaPermissionControl(
            activity: PermissionActivity,
            context: Context) : Boolean{
            val requestList= ArrayList<String>()
            var permStatus=
                ContextCompat.checkSelfPermission(context,Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED
            if (!permStatus){
                requestList.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            permStatus=ContextCompat.checkSelfPermission(context,Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED

            if (!permStatus){
                requestList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
            if (requestList.size==0){
                activity.grantedFunc()
                return true
            }else{
                activity.requestPermissions(requestList.toTypedArray(), reqCodeLocations)
                return false
            }
        }
    }
}