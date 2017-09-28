package com.androidizate.climapp.utils;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fedebenedetto, antorosmari on 27/9/2017.
 */

public class PermissionUtils {
    public static final int REQUEST_PERMISSION = 899 ;
    public static final int REQUEST_MULTIPLE_PERMISSIONS = 900;

    public static void checkPermission(AppCompatActivity appCompatActivity, String permissionName) {

        if (ContextCompat.checkSelfPermission(appCompatActivity, permissionName) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(appCompatActivity,

                    permissionName)) {

            } else {

                ActivityCompat.requestPermissions(appCompatActivity, new String[] {permissionName}, REQUEST_PERMISSION);

            }

        }

    }



    public static void checkPermissions(AppCompatActivity appCompatActivity, List<String> permissionsList) {

        List<String> noPermissionGrantedList = new ArrayList<>();



        if (!permissionsList.isEmpty()) {

            for (String permission : permissionsList) {

                if (ContextCompat.checkSelfPermission(appCompatActivity, permission) != PackageManager.PERMISSION_GRANTED) {

                    noPermissionGrantedList.add(permission);

                }

            }

            if (!noPermissionGrantedList.isEmpty()) {

                ActivityCompat.requestPermissions(appCompatActivity, noPermissionGrantedList.toArray

                        (new String[noPermissionGrantedList.size()]), REQUEST_MULTIPLE_PERMISSIONS);

            }

        }

    }
}
