package com.soaros.threadlib;

import com.soaros.threadlib.tasks.AbsTask;

public class UiThread implements IThread {
    @Override
    public void run(AbsTask task, int delay) {
        ThreadUtils.runInUi(task, delay);
    }
}