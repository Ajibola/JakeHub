package com.jibola.app.jakehub.data.local.mapper;

import com.jibola.app.jakehub.data.local.entity.GitRepoRealm;
import com.jibola.app.jakehub.domain.model.Repo;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by hp on 8/10/2017.
 */

public class RepoRealmMapper implements Mapper<GitRepoRealm, Repo> {

    @Override
    public Repo mapObject(GitRepoRealm gitRepoRealm) {
        return Repo.create(gitRepoRealm.getId(), gitRepoRealm.getName(), gitRepoRealm.getFull_name(),
                gitRepoRealm.getHtml_url(), gitRepoRealm.getDescription(), gitRepoRealm.getLanguage(),
                gitRepoRealm.getWatchers(), gitRepoRealm.getStars(), gitRepoRealm.getForks());
    }

    @Override
    public Collection<Repo> mapObjectCollection(Collection<GitRepoRealm> gitRepoRealms) {
        Collection<Repo> repos = new ArrayList<>();
        for (GitRepoRealm gitRepoRealm : gitRepoRealms) {
            repos.add(mapObject(gitRepoRealm));
        }
        return repos;
    }
}
