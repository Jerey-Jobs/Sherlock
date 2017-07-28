package com.soaros.threadlib;

import com.soaros.threadlib.tasks.AbsTask;

public interface IThread {
    void run(AbsTask task, int delay);
}