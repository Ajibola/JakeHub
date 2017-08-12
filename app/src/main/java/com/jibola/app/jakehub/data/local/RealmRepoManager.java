package com.jibola.app.jakehub.data.local;

import com.jibola.app.jakehub.data.local.entity.GitRepoRealm;

import java.util.List;

import io.realm.Realm;

/**
 * Created by hp on 8/10/2017.
 */

public class RealmRepoManager {

    public void saveRepos(final List<GitRepoRealm> gitRepoRealmList) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(gitRepoRealmList);
            }
        });
        realm.close();
    }
}
