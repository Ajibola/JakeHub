package com.jibola.app.jakehub.app;

import android.app.Application;

import com.jibola.app.jakehub.di.AppComponent;
import com.jibola.app.jakehub.di.AppModule;
import com.jibola.app.jakehub.di.DaggerAppComponent;

import io.realm.Realm;

/**
 * Created by hp on 8/9/2017.
 */

public class JakeApplication extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        appComponent = initAppComponent();
    }

    protected AppComponent initAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
