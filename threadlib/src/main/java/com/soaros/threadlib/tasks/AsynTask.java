package com.soaros.threadlib.tasks;

import com.soaros.threadlib.IThread;
import com.soaros.threadlib.tasks.AbsTask;

public abstract class AsynTask extends AbsTask {

    public AsynTask() {

    }

    public AsynTask(int delay) {
        this.delayTime = delay;
    }

    @Override
    public IThread getThread() {
        return AbsTask.ASYN;
    }
}