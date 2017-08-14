package com.jibola.app.jakehub.data.local;

import com.jibola.app.jakehub.data.local.entity.GitRepoRealm;
import com.jibola.app.jakehub.data.local.mapper.RepoRealmMapper;
import com.jibola.app.jakehub.domain.DataSource;
import com.jibola.app.jakehub.domain.model.Repo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by hp on 8/10/2017.
 */

public class LocalDataSource implements DataSource {

    @Inject
    public LocalDataSource() {
    }

    @Override
    public Observable<List<Repo>> getRepos(String userName, int page, int limit) {
        int skipCount = page * limit;
        int skipEnd = skipCount + limit;

        Realm realm = Realm.getDefaultInstance();
        RealmResults<GitRepoRealm> gitRepoRealms = realm.where(GitRepoRealm.class).findAll();

        List<Repo> repoList = new ArrayList<>();
        skipEnd = (skipEnd > gitRepoRealms.size() - 1) ? gitRepoRealms.size() - 1 : skipEnd;
        if (skipCount < gitRepoRealms.size()) {
            repoList = (List<Repo>) new RepoRealmMapper().mapObjectCollection(
                    gitRepoRealms.subList(skipCount, skipEnd));
        }

        return Observable.just(repoList);
    }
}
