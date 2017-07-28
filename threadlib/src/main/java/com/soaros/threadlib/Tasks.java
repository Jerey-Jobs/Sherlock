package com.soaros.threadlib;

import android.app.Activity;
import android.content.Context;

import com.soaros.threadlib.tasks.AbsTask;

/**
 * 线程链表
 */
public class Tasks {

    private AbsTask firstTask = null;
    private AbsTask lastTask = null;

    private Tasks() {}

    public static Tasks with(Context context) {
        // TODO 生命周期监听,
        return new Tasks();
    }

    public void clearTask() {
        firstTask = null;
        lastTask = null;
    }

    public Tasks onNext(AbsTask task) {
        if (firstTask == null) {
            lastTask = firstTask = task;
        } else {
            lastTask.onNext(task);
            lastTask = task;
        }
        return this;
    }

    public void start() {
        if (firstTask != null) {
            firstTask.run();
        } else {
            throw new NullPointerException("there is no task");
        }
    }


}