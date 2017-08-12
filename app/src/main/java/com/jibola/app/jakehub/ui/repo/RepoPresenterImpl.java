package com.jibola.app.jakehub.ui.repo;

import com.jibola.app.jakehub.app.JakeApplication;
import com.jibola.app.jakehub.domain.model.Repo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hp on 8/11/2017.
 */

public class RepoPresenterImpl implements RepoContract.Presenter {

    @Inject
    RepoContract.Interactor interactor;

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
    public void loadMoreRepositories(int page, final boolean refresh) {
        interactor.loadRepositories(page, 15)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Repo>>() {
                    @Override
                    public void accept(List<Repo> repoList) throws Exception {
                        view.hideLoading();
                        if (repoList != null && repoList.size() > 0) {
                            view.displayRepositories(repoList, refresh);
                        } else {
                            view.showMessage("Unable to load item");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.hideLoading();
                        view.showMessage(throwable.getMessage());
                    }
                });
    }
}
