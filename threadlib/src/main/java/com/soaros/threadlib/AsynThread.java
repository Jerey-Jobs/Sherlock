package com.soaros.threadlib;

import com.soaros.threadlib.tasks.AbsTask;

public class AsynThread implements IThread {
    @Override
    public void run(AbsTask task, int delay) {
        ThreadUtils.runInBack(task, delay);
    }
}