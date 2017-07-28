package com.soaros.threadlib.tasks;

import android.util.Log;

import com.soaros.threadlib.AsynThread;
import com.soaros.threadlib.IThread;
import com.soaros.threadlib.UiThread;

public abstract class AbsTask implements Runnable {
    protected int delayTime = 0;
    private AbsTask afterTask;
    public static IThread MAIN;
    public static IThread ASYN;

    static {
        MAIN = new UiThread();
        ASYN = new AsynThread();
    }


    @Override
    public void run() {
        IThread thread = getThread();
        thread.run(new AbsTask() {
            @Override
            public void runInTask() {
                Log.v("ghy", "doOnNext");
            }

            @Override
            public void run() {
                //此处需要重写run方法，否则会死循环
                onNextTask();
            }
        }, delayTime);
    }

    private void onNextTask() {
        //执行自己的方法
        runInTask();
        //通知下个方法
        if (afterTask != null) {
            afterTask.run();
        }
    }

    public abstract void runInTask();

    public IThread getThread() {
        return MAIN;
    }

    public void onNext(AbsTask afterTask) {
        this.afterTask = afterTask;
    }
}