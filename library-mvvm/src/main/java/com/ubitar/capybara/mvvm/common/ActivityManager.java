package com.ubitar.capybara.mvvm.common;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Created by chenweiqi on 2017/7/25.
 */

public class ActivityManager {
    private ActivityManager() {
    }

    private static ActivityManager sManager;
    private Stack<WeakReference<Activity>> mActivityStack;

    public static ActivityManager getManager() {
        if (sManager == null) {
            synchronized (ActivityManager.class) {
                if (sManager == null) {
                    sManager = new ActivityManager();
                }
            }
        }
        return sManager;
    }

    /**
     * 添加Activity到栈
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<>();
        }
        mActivityStack.add(new WeakReference<>(activity));
    }

    /**
     * 检查弱引用是否释放，若释放，则从栈中清理掉该元素
     */
    public void checkWeakReference() {
        if (mActivityStack != null) {
            // 使用迭代器进行安全删除
            for (Iterator<WeakReference<Activity>> it = mActivityStack.iterator(); it.hasNext(); ) {
                WeakReference<Activity> activityReference = it.next();
                Activity temp = activityReference.get();
                if (temp == null) {
                    it.remove();
                }
            }
        }
    }

    /**
     * 获取当前Activity（栈中最后一个压入的）
     *
     * @return
     */
    public Activity currentActivity() {
        checkWeakReference();
        if (mActivityStack != null && !mActivityStack.isEmpty()) {
            return mActivityStack.lastElement().get();
        }
        return null;
    }

    /**
     * 关闭当前Activity（栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = currentActivity();
        if (activity != null) {
            finishActivity(activity);
        }
    }

    /**
     * 关闭指定的Activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null && mActivityStack != null) {
            // 使用迭代器进行安全删除
            for (Iterator<WeakReference<Activity>> it = mActivityStack.iterator(); it.hasNext(); ) {
                WeakReference<Activity> activityReference = it.next();
                Activity temp = activityReference.get();
                // 清理掉已经释放的activity
                if (temp == null) {
                    it.remove();
                    continue;
                }
                if (temp == activity) {
                    it.remove();
                }
            }
            activity.finish();
        }
    }

    /**
     * 关闭指定类名的所有Activity
     *
     * @param cls
     */
    public void finishActivity(Class<?> cls) {
        if (mActivityStack != null) {
            // 使用迭代器进行安全删除
            for (Iterator<WeakReference<Activity>> it = mActivityStack.iterator(); it.hasNext(); ) {
                WeakReference<Activity> activityReference = it.next();
                Activity activity = activityReference.get();
                // 清理掉已经释放的activity
                if (activity == null) {
                    it.remove();
                    continue;
                }
                if (activity.getClass().equals(cls)) {
                    it.remove();
                    activity.finish();
                }
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        if (mActivityStack != null) {
            for (WeakReference<Activity> activityReference : mActivityStack) {
                Activity activity = activityReference.get();
                if (activity != null) {
                    activity.finish();
                }
            }
            mActivityStack.clear();
        }
    }

    public void finishOtherActivity(Class Activity) {
        if (mActivityStack != null) {
            for (WeakReference<android.app.Activity> activityReference : mActivityStack) {
                android.app.Activity activity = activityReference.get();
                if (activity != null) {
                    if (!activity.getClass().equals(activity))
                        activity.finish();
                }
            }
            mActivityStack.clear();
        }
    }

    /**
     * 判断某个activity是否在前台显示
     */
    public static boolean isForeground(Activity activity) {
        return isForeground(activity, activity.getClass().getName());
    }

    private static boolean isForeground(Activity context, String className) {
        if (context == null || TextUtils.isEmpty(className))
            return false;
        android.app.ActivityManager am = (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<android.app.ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName()))
                return true;
        }
        return false;
    }

    /**
     * 退出应用程序
     */
    public void exitApp() {
        try {
            finishAllActivity();
            // 退出JVM,释放所占内存资源,0表示正常退出
            System.exit(0);
            // 从系统中kill掉应用程序
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断应用是否在后台
     *
     * @param context
     * @return
     */
    public boolean isApplicationInBackground(Context context) {
        android.app.ActivityManager am = (android.app.ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        List<android.app.ActivityManager.RunningTaskInfo> taskList = am.getRunningTasks(1);
        if (taskList != null && !taskList.isEmpty()) {
            ComponentName topActivity = taskList.get(0).topActivity;
            if (topActivity != null && !topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断Activity是否在运行
     *
     * @param c
     * @return
     */
    public boolean isExistActivity(Class<?> c) {
        if (mActivityStack != null) {
            for (WeakReference<Activity> activityReference : mActivityStack) {
                Activity activity = activityReference.get();
                if (activity != null) {
                    if (activity.getClass().equals(c))
                        return true;
                }
            }
            mActivityStack.clear();
        }
        return false;
    }

    public int getAllActivityCount() {
        return mActivityStack.size();
    }
}
