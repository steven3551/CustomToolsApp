package com.wuwg.component.launcher;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wuwengao on 2017/6/24.
 */
public class HookUtils {

    private Class<?> proxyActivity;
    private Context context;
    private Object ActivityManangerValue;

    public HookUtils(Class<?> proxyActivity, Context context) {
        this.proxyActivity = proxyActivity;
        this.context = context;
    }

    /**
     * 通过hook替换Intent
     */
    public void hookAms() {
        try {
            Class activityManagerNativeClass = Class.forName("android.app.ActivityManagerNative");
            Field gDefaultField = activityManagerNativeClass.getDeclaredField("gDefault");
            gDefaultField.setAccessible(true);
            Object gDefaultValue = gDefaultField.get(null);
            Class singletonClass = Class.forName("android.util.Singleton");
            Field mInstanceField = singletonClass.getDeclaredField("mInstance");
            mInstanceField.setAccessible(true);
            Object iActivityManagerValue = mInstanceField.get(gDefaultValue);
            Class iActivityManagerProxy = Class.forName("android.app.IActivityManager");
            AmsInvocationHandler handler = new AmsInvocationHandler(iActivityManagerValue);
            Object proxy = Proxy.newProxyInstance(context.getClassLoader(), new Class[]{iActivityManagerProxy}, handler);
            mInstanceField.set(gDefaultValue, proxy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class AmsInvocationHandler implements InvocationHandler {

        private Object iActivityManagerValue;

        public AmsInvocationHandler(Object iActivityManagerValue) {
            this.iActivityManagerValue = iActivityManagerValue;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("startActivity".equals(method.getName())) {
                Intent intent = null;
                int index = 0;
                for (int i = 0; i < args.length; i++) {
                    if (args[i] instanceof Intent) {
                        intent = (Intent) args[i];
                        index = i;
                        break;
                    }
                }
                Intent proxyIntent = new Intent();
                ComponentName cmp = new ComponentName(context, proxyActivity);
                proxyIntent.setComponent(cmp);
                proxyIntent.putExtra("oldIntent", intent);
                args[index] = proxyIntent;
            }
            return method.invoke(iActivityManagerValue, args);
        }
    }

    /**
     * 在系统校验完Activity的合法性之后，在将代理的Activity替换成非法的Activity
     */
    public void hookSystemHandler() {
        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Field activityThreadField = activityThreadClass.getDeclaredField("sCurrentActivityThread");
            activityThreadField.setAccessible(true);
            ActivityManangerValue = activityThreadField.get(null);
            Field mHField = activityThreadClass.getDeclaredField("mH");
            mHField.setAccessible(true);
            Handler handler = (Handler) mHField.get(ActivityManangerValue);
            Field mCallbackField = Handler.class.getDeclaredField("mCallback");
            mCallbackField.setAccessible(true);
            ActivityThreadHandlerCallback callback = new ActivityThreadHandlerCallback(handler);
            mCallbackField.set(handler, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ActivityThreadHandlerCallback implements Handler.Callback {

        private Handler handler;

        public ActivityThreadHandlerCallback(Handler handler) {
            this.handler = handler;
        }

        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 100) {    //该动作的启动Activity
                handleLaunchActivity(msg);
            }
            handler.handleMessage(msg);
            return true;
        }

    }

    private void handleLaunchActivity(Message msg) {
        try {
            Object obj = msg.obj;
            Field intentField = obj.getClass().getDeclaredField("intent");
            intentField.setAccessible(true);
            Intent proxyIntent = (Intent) intentField.get(obj);
            Intent realIntent = (Intent) proxyIntent.getParcelableExtra("oldIntent");
            proxyIntent.setComponent(realIntent.getComponent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
