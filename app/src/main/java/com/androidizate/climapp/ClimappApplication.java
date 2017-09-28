package com.androidizate.climapp;

import android.app.Application;

import com.androidizate.climapp.dao.DaoMaster;
import com.androidizate.climapp.dao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * @author Andres Oller
 */

public class ClimappApplication extends Application {

    DaoSession daoSession;
    DaoMaster daoMaster;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "climapp");
        Database db = helper.getWritableDb();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public DaoMaster getDaoMaster() {
        return daoMaster;
    }
}
