package com.wency.android.exerise;

import android.util.Log;

/**
 * Created by wency on 2018/3/7.
 */

public class Test extends BaseTest {
    public Test() {
//        Log.i("static","Test");
    }

    public static void show_static(){
        Log.i("static","子类静态Test_static");
    }

    @Override
    public void show() {
//        super.show();
        Log.i("static","Test");
    }
}
