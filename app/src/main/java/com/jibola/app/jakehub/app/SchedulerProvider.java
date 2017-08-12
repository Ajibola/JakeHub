package com.jibola.app.jakehub.app;

import io.reactivex.Scheduler;

/**
 * Created by hp on 8/10/2017.
 */

public interface SchedulerProvider {

    Scheduler mainThread();

    Scheduler backgroundThread();
}
