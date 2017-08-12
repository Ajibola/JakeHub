package com.jibola.app.jakehub.data.network;

import com.jibola.app.jakehub.data.local.RealmRepoManager;
import com.jibola.app.jakehub.data.local.entity.GitRepoRealm;
import com.jibola.app.jakehub.data.local.mapper.GitRepoRealmMapper;
import com.jibola.app.jakehub.data.local.mapper.RepoRealmMapper;
import com.jibola.app.jakehub.data.network.entity.GitRepo;
import com.jibola.app.jakehub.domain.DataSource;
import com.jibola.app.jakehub.domain.model.Repo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hp on 8/10/2017.
 */

public class ApiDataSource implements DataSource {

    private GitHubApi gitHubApi;

    @Inject
    public ApiDataSource(GitHubApi gitHubApi) {
        this.gitHubApi = gitHubApi;
    }

    @Override
    public Observable<List<Repo>> getRepos(String userName, int page, int limit) {
        return gitHubApi.getUserRepos(userName, page, limit)
                .map(new Function<List<GitRepo>, List<GitRepoRealm>>() {
                    @Override
                    public List<GitRepoRealm> apply(List<GitRepo> gitRepos) throws Exception {
                        return (List<GitRepoRealm>) new GitRepoRealmMapper().mapObjectCollection(gitRepos);
                    }
                })
                .map(new Function<List<GitRepoRealm>, List<Repo>>() {
                    @Override
                    public List<Repo> apply(final List<GitRepoRealm> gitRepoRealms) throws Exception {
                        new RealmRepoManager().saveRepos(gitRepoRealms);
                        return (List<Repo>) new RepoRealmMapper().mapObjectCollection(gitRepoRealms);
                    }
                });
    }
}
