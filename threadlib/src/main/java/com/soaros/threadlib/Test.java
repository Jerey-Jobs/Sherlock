package com.soaros.threadlib;

import android.util.Log;

import com.soaros.threadlib.tasks.AsynTask;
import com.soaros.threadlib.tasks.MainTask;

/**
 * @author xiamin
 * @date 7/28/17.
 */
public class Test {
    public static void main(String[] args) {
//        Tasks.with(this)
//                   .onNext(new MainTask() {
//                       @Override
//                       public void runInTask() {
//                           Log.d("xiamin","thread:" + Thread.currentThread().getId());
//                       }
//                   }).onNext(new AsynTask() {
//            @Override
//            public void runInTask() {
//                Log.d("xiamin","thread:" + Thread.currentThread().getId());
//            }
//        }).onNext(new MainTask() {
//            @Override
//            public void runInTask() {
//                Log.d("xiamin","thread:" + Thread.currentThread().getId());
//            }
//        }).start();
    }
}
