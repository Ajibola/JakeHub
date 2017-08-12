package com.jibola.app.jakehub.ui.repo;

import com.jibola.app.jakehub.domain.model.Repo;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by hp on 8/10/2017.
 */

public interface RepoContract {

    interface View {

        void showLoading();

        void hideLoading();

        void showMessage(Throwable throwable);

        void displayRepositories(List<Repo> repo, boolean refresh);
    }

    interface Presenter {

        void setView(RepoContract.View view);

        void refreshRepositories();

        void loadMoreRepositories(int page, boolean refresh);
    }

    interface Interactor {

        Observable<List<Repo>> loadRepositories(int page);

        Observable<List<Repo>> loadCachedRepositories(int page);
    }
}
