package com.jibola.app.jakehub.app.di;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.jibola.app.jakehub.app.AppScheduler;
import com.jibola.app.jakehub.app.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hp on 8/9/2017.
 */

@Module
public class AppModule {

    private Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context provideAppContext() {
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    Resources provideAppResources() {
        return app.getResources();
    }

    @Provides
    @Singleton
    SchedulerProvider provideAppScheduler() {
        return new AppScheduler();
    }
}
