package com.example.com.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.com.common.util.PermissionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Map;

/**
 * <p>类说明</p>
 * Created by 710330 on 2018/4/11.
 */
public class CommonInjection {
    private static Application sApplication;

    public static void init(@NonNull final Context context) {
        sApplication = (Application) context.getApplicationContext();
    }

    public static Context provideAppContext() {
        if (sApplication != null) {
            return sApplication;
        } else {
            throw new NullPointerException("Common module application is null!");
        }
    }


    private static final LinkedList<Activity> ACTIVITY_LIST = new LinkedList<>();

    private static Application.ActivityLifecycleCallbacks mCallbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            setTopActivity(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {
            setTopActivity(activity);
        }

        @Override
        public void onActivityResumed(Activity activity) {
            setTopActivity(activity);
        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            ACTIVITY_LIST.remove(activity);
        }
    };

    static void setTopActivity(final Activity activity) {
        if (activity.getClass() == PermissionUtils.PermissionActivity.class) return;
        if (ACTIVITY_LIST.contains(activity)) {
            if (!ACTIVITY_LIST.getLast().equals(activity)) {
                ACTIVITY_LIST.remove(activity);
                ACTIVITY_LIST.addLast(activity);
            }
        } else {
            ACTIVITY_LIST.addLast(activity);
        }
    }

    public static LinkedList<Activity> provideActivityList() {
        return ACTIVITY_LIST;
    }

    public static Context provideTopActivityOrApp() {
        Activity topActivity = provideTopActivity();
        return topActivity == null ? CommonInjection.provideAppContext() : topActivity;
    }

    public static Activity provideTopActivity() {
        if (!ACTIVITY_LIST.isEmpty()) {
            final Activity topActivity = ACTIVITY_LIST.getLast();
            if (topActivity != null) {
                return topActivity;
            }
        }
        // using reflect to get top activity
        try {
            @SuppressLint("PrivateApi")
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            Map activities = (Map) activitiesField.get(activityThread);
            if (activities == null) return null;
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Activity activity = (Activity) activityField.get(activityRecord);
                    setTopActivity(activity);
                    return activity;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}
