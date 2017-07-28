package com.soaros.threadlib.tasks;

import com.soaros.threadlib.IThread;
import com.soaros.threadlib.tasks.AbsTask;

public abstract class MainTask extends AbsTask {

    public MainTask() {

    }

    public MainTask(int delay) {
        this.delayTime = delay;
    }

    @Override
    public IThread getThread() {
        return AbsTask.MAIN;
    }
}