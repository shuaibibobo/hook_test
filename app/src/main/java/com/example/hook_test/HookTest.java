package com.example.hook_test;

import android.widget.Toast;
import de.robv.android.xposed.IXposedHookLoadPackage;

import de.robv.android.xposed.XC_MethodHook;
import android.util.Log;
import de.robv.android.xposed.XposedBridge;

import de.robv.android.xposed.XposedHelpers;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookTest implements IXposedHookLoadPackage {

    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        if (loadPackageParam.packageName.equals("com.example.hook")) {

            XposedBridge.log(" has Hooked!");
            Log.i("test","进来啦啊孙菲菲烦烦烦烦烦烦烦烦烦");
            Class clazz = loadPackageParam.classLoader.loadClass(

                    "com.example.hook.MainActivity");

            XposedHelpers.findAndHookMethod(clazz, "toastMessage", new XC_MethodHook() {

                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                    super.beforeHookedMethod(param);

                    XposedBridge.log(" has Hooked!");

                }

                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    param.setResult("你已被劫持");

                }

            });

        }

    }

}