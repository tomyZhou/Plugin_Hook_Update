package com.example.plugin_hook;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//https://github.com/han1202012/Plugin_Hook

public class MainActivity2 extends Activity {

    private static final String TAG = "plugin_MainActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.i(TAG, "MainActivity2 onCreate");

        // 反射插件包中的 com.example.plugin.MainActivity
        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.example.plugin.MainActivity");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Method method = null;
        try {
            method = clazz.getDeclaredMethod("log");
            method.setAccessible(true);

        } catch (NoSuchMethodException e) {
            Log.e("xxx","error");
            e.printStackTrace();
        }

        try {
            // 执行 com.example.plugin.MainActivity 的 log 方法
            method.invoke(clazz.newInstance());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


        // 设置按钮点击事件
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 启动插件包中的 Activity
                Intent pluginIntent = new Intent();

                try {
                    Class<?> clazz  = Class.forName("com.example.plugin.MainActivity");

                    pluginIntent.setClass(MainActivity2.this,clazz);
                    pluginIntent.putExtra("isPlugin", true);
                    startActivity(pluginIntent);

                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }


            }
        });
    }
}