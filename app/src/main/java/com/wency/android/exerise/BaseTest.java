package com.wency.android.exerise;

import android.util.Log;

/**
 * Created by wency on 2018/3/7.
 */

public class BaseTest {

    public BaseTest() {
//        Log.i("static","BaseTest");
    }

    public static void show_static(){
        Log.i("static","父类静态BaseTest_static");
    }

    public void show(){
        Log.i("static","BaseTest");
    }
}
