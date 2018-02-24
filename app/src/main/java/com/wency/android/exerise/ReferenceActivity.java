package com.wency.android.exerise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Date;

public class ReferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);
        Button hard = (Button) findViewById(R.id.hard);
        Button soft = (Button) findViewById(R.id.soft);
        Button weak = (Button) findViewById(R.id.weak);
        Button phantom = (Button) findViewById(R.id.phantom);
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.i("ref","log test");
//                MyDate date = new MyDate();
////                Log.i("ref","data id:"+)
//                Runtime.getRuntime().gc();

                SoftReference[] softArr = new SoftReference[5];
                softArr[0] = new SoftReference<byte[]>(new byte[1024 * 1024 * 100]);
                System.out.println("GC 前===>" + softArr[0].get());
//                System.gc();
                Runtime.getRuntime().gc();
                System.out.println("第一次GC后：===>" + softArr[0].get());
                softArr[1] = new SoftReference<byte[]>(new byte[1024 * 1024 * 100]);
//                System.gc();
                Runtime.getRuntime().gc();
                System.out.println("第二次GC后===>" + softArr[0].get()+"=="+softArr[1].get());
                softArr[2] = new SoftReference<byte[]>(new byte[1024 * 1024 * 100]);
//                System.gc();
                Runtime.getRuntime().gc();
                System.out.println("第三次GC后===>" + softArr[0].get()+"=="+softArr[1].get()+"=="+softArr[2].get());
                softArr[3] = new SoftReference<byte[]>(new byte[1024 * 1024 * 100]);
                System.gc();
//                Runtime.getRuntime().gc();//这里都不需要显示执行，因为堆内存已经满了，虚拟机自己会执行。
                System.out.println("第四次GC后===>" + softArr[0].get()+"=="+softArr[1].get()+"=="+softArr[2].get()+"=="+softArr[3].get());

            }
        });
        soft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SoftReference ref = new SoftReference(new MyDate());
                SoftReference ref = new SoftReference(new Object());
                drainMemory();
            }
        });
        weak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDate date = new MyDate();
//                WeakReference ref = new WeakReference(date);

//                WeakReference ref = new WeakReference(new Object());

                ReferenceQueue queue = new ReferenceQueue();
                WeakReference ref = new WeakReference(new Object(),queue);
                date = null;
                Runtime.getRuntime().gc();
            }
        });
        phantom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReferenceQueue queue = new ReferenceQueue();
//                PhantomReference ref = new PhantomReference(new MyDate(), queue);
                PhantomReference ref = new PhantomReference(new Object(), queue);
                Runtime.getRuntime().gc();
            }
        });
    }

    // 消耗大量内存
    public static void drainMemory() {
        String[] array = new String[1024 * 100];
        for (int i = 0; i < 1024 * 100; i++) {
            for (int j = 'a'; j <= 'z'; j++) {
                array[i] += (char) j;
            }
        }
    }

    public class MyDate extends Date {

        /**
         * Creates a new instance of MyDate
         */
        public MyDate() {
        }

        // 覆盖finalize()方法
        protected void finalize() throws Throwable {
            super.finalize();
            Log.i("ref", "obj [Date: " + this.getTime() + "] is gc");
        }

        public String toString() {
            return "Date: " + this.getTime();
        }
    }
}
