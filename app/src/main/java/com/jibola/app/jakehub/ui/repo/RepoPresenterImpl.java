package com.jibola.app.jakehub.ui.repo;

import com.jibola.app.jakehub.app.AppScheduler;
import com.jibola.app.jakehub.app.SchedulerProvider;
import com.jibola.app.jakehub.domain.model.Repo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by hp on 8/11/2017.
 */

public class RepoPresenterImpl implements RepoContract.Presenter {

    @Inject
    RepoContract.Interactor interactor;

    @Inject
    SchedulerProvider appScheduler;

    private RepoContract.View view;

    @Inject
    public RepoPresenterImpl() {
    }

    @Override
    public void setView(RepoContract.View view) {
        this.view = view;
    }

    @Override
    public void refreshRepositories() {
        view.showLoading();
        loadMoreRepositories(0, true);
    }

    @Override
    public void loadMoreRepositories(final int page, final boolean refresh) {
        interactor.loadRepositories(page)
                .observeOn(appScheduler.mainThread())
                .subscribeOn(appScheduler.backgroundThread())
                .onErrorReturn(new Function<Throwable, List<Repo>>() {
                    @Override
                    public List<Repo> apply(Throwable throwable) throws Exception {
                        view.hideLoading();
                        List<Repo> localData = interactor.loadCachedRepositories(page).blockingFirst();
                        view.showMessage(throwable);
                        return localData;
                    }
                })
                .subscribe(new Consumer<List<Repo>>() {
                    @Override
                    public void accept(List<Repo> repoList) throws Exception {
                        view.hideLoading();
                        if (repoList != null && repoList.size() > 0) {
                            view.displayRepositories(repoList, refresh);
                        } else {
                            view.showMessage(new Exception("No repos loaded"));
                        }
                    }
                });
    }
}
