package com.example.com.common.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

/**
 * <p>通讯类相关工具类</p>
 * Created by zqiang94 on 2018/5/18.
 */
public class ContractUtil {


    public static String getName(Context context, Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0 || cursor.getColumnCount() == 0) {
            return "";
        }
        cursor.moveToFirst();
        // 获得DATA表中的名字
        String username = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

        return username;
    }

    public static String getTel(Context context, Cursor cursor) {

        if (cursor == null || cursor.getCount() == 0 || cursor.getColumnCount() == 0) {
            return "";
        }
        String result = "";
        ContentResolver reContentResolverol = context.getContentResolver();
        cursor.moveToFirst();
        // 获得DATA表中的名字
        String username = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        // 条件为联系人ID
        String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
        // 获得DATA表中的电话号码，条件为联系人ID,因为手机号码可能会有多个
        Cursor phone = reContentResolverol.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
                        + contactId, null, null);
        while (phone.moveToNext()) {
            result = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }

        return result;

    }
}
