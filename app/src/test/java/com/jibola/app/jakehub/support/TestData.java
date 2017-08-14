package com.jibola.app.jakehub.support;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jibola.app.jakehub.data.local.entity.GitRepoRealm;
import com.jibola.app.jakehub.data.local.mapper.GitRepoRealmMapper;
import com.jibola.app.jakehub.data.local.mapper.RepoRealmMapper;
import com.jibola.app.jakehub.data.network.entity.GitRepo;
import com.jibola.app.jakehub.data.network.entity.GsonAdapterFactory;
import com.jibola.app.jakehub.domain.model.Repo;

import java.util.Arrays;
import java.util.List;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hp on 8/14/2017.
 */

public class TestData {

    GsonConverterFactory gsonConverterFactory;
    Gson gson;

    public TestData() {
        gsonConverterFactory = GsonConverterFactory.create(new GsonBuilder()
                .registerTypeAdapterFactory(GsonAdapterFactory.create())
                .create());

        gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .registerTypeAdapterFactory(GsonAdapterFactory.create())
                .create();
    }

    public GitRepo getGitRepo(String fileName) {
        return gson.fromJson(new TestUtil().loadResourceFile(fileName), GitRepo.class);
    }

    public GitRepoRealm getGitRepoRealm(String fileName) {
        GitRepoRealmMapper gitRepoRealmMapper = new GitRepoRealmMapper();
        return gitRepoRealmMapper.mapObject(getGitRepo(fileName));
    }

    public Repo getRepo(String fileName) {
        RepoRealmMapper repoRealmMapper = new RepoRealmMapper();
        return repoRealmMapper.mapObject(getGitRepoRealm(fileName));
    }

    public List<GitRepo> getGitRepoList(String fileName) {
        GitRepo[] gitRepos = gson.fromJson(new TestUtil().loadResourceFile(fileName), GitRepo[].class);
        return Arrays.asList(gitRepos);
    }

    public List<GitRepoRealm> getGitRepoRealmList(String fileName) {
        GitRepoRealmMapper gitRepoRealmMapper = new GitRepoRealmMapper();
        return (List<GitRepoRealm>) gitRepoRealmMapper.mapObjectCollection(getGitRepoList(fileName));
    }

    public List<Repo> getRepoList(String fileName) {
        RepoRealmMapper repoRealmMapper = new RepoRealmMapper();
        return (List<Repo>) repoRealmMapper.mapObjectCollection(getGitRepoRealmList(fileName));
    }
}
