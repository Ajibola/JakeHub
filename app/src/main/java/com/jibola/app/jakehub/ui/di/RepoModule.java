package com.jibola.app.jakehub.ui.di;

import com.jibola.app.jakehub.ui.repo.RepoContract;
import com.jibola.app.jakehub.ui.repo.RepoInteractorImpl;
import com.jibola.app.jakehub.ui.repo.RepoPresenterImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by hp on 8/11/2017.
 */

@Module
public class RepoModule {

    @Provides
    public RepoContract.Interactor provideInteractor(RepoInteractorImpl interactor) {
        return interactor;
    }

    @Provides
    public RepoContract.Presenter providePresenter(RepoPresenterImpl presenter) {
        return presenter;
    }
}
