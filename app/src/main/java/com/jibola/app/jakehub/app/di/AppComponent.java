package com.jibola.app.jakehub.app.di;

import com.jibola.app.jakehub.data.di.ApiModule;
import com.jibola.app.jakehub.ui.repo.MainActivity;
import com.jibola.app.jakehub.ui.di.RepoModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by hp on 8/10/2017.
 */

@Singleton
@Component(modules = {AppModule.class, RepoModule.class, ApiModule.class})
public interface AppComponent {

    void inject(MainActivity target);
}
