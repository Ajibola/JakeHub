package com.jibola.app.jakehub.ui.repo;

import com.jibola.app.jakehub.data.local.LocalDataSource;
import com.jibola.app.jakehub.data.network.ApiDataSource;
import com.jibola.app.jakehub.domain.model.Repo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by hp on 8/11/2017.
 */

public class RepoInteractorImpl implements RepoContract.Interactor {

    private final String USER_NAME = "JakeWharton";
    private ApiDataSource apiDataSource;
    private LocalDataSource localDataSource;

    @Inject
    public RepoInteractorImpl(ApiDataSource apiDataSource, LocalDataSource localDataSource) {
        this.apiDataSource = apiDataSource;
        this.localDataSource = localDataSource;
    }

    @Override
    public Observable<List<Repo>> loadRepositories(final int page, final int per_page) {
        return apiDataSource.getRepos(USER_NAME, page + 1, per_page)
                .onErrorReturn(new Function<Throwable, List<Repo>>() {
                    @Override
                    public List<Repo> apply(Throwable throwable) throws Exception {
                        List<Repo> localData = localDataSource.getRepos(USER_NAME, page, per_page).blockingFirst();
                        return localData;
                    }
                });
    }

}
