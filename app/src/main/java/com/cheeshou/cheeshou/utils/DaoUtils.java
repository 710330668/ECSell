package com.cheeshou.cheeshou.utils;

import android.content.Context;

import com.cheeshou.cheeshou.dealer.ui.model.DaoMaster;
import com.cheeshou.cheeshou.dealer.ui.model.DaoSession;

public class DaoUtils {


    public static DaoSession getDaoSession(Context context) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "cesell.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        return daoSession;
    }
}
