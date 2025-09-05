package com.example.plugin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    private static final String TAG = "xxx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("xxx", "启动了插件 Activity");
    }

    public void log(){
        Log.e(TAG, "Plugin MainActivity");
    }

    /*
    // 这种方式侵入代码 , 造成开发的差异性 , 建议使用 Hook 加载插件资源
    @Override
    public Resources getResources() {
        if (getApplication() != null && getApplication().getResources() != null) {
            return getApplication().getResources();
        }
        return super.getResources();
    }*/
}