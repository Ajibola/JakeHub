package com.jibola.app.jakehub.data.local.mapper;

import com.jibola.app.jakehub.data.local.entity.GitRepoRealm;
import com.jibola.app.jakehub.data.network.entity.GitRepo;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by hp on 8/10/2017.
 */

public class GitRepoRealmMapper implements Mapper<GitRepo, GitRepoRealm> {

    @Override
    public GitRepoRealm mapObject(GitRepo gitRepo) {
        GitRepoRealm gitRepoRealm = new GitRepoRealm();
        gitRepoRealm.setId(gitRepo.id());
        gitRepoRealm.setName(gitRepo.name());
        gitRepoRealm.setFull_name(gitRepo.full_name());
        gitRepoRealm.setHtml_url(gitRepo.html_url());
        gitRepoRealm.setDescription(gitRepo.description());
        gitRepoRealm.setLanguage(gitRepo.language());
        gitRepoRealm.setWatchers(gitRepo.watchers());
        gitRepoRealm.setStars(gitRepo.stars());
        gitRepoRealm.setForks(gitRepo.forks());

        return gitRepoRealm;
    }

    @Override
    public Collection<GitRepoRealm> mapObjectCollection(Collection<GitRepo> gitRepos) {
        Collection<GitRepoRealm> gitRepoRealms = new ArrayList<>();

        for (GitRepo gitRepo : gitRepos) {
            gitRepoRealms.add(mapObject(gitRepo));
        }
        return gitRepoRealms;
    }
}
