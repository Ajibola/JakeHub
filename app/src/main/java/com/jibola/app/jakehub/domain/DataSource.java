package com.jibola.app.jakehub.domain;

import com.jibola.app.jakehub.domain.model.Repo;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by hp on 8/9/2017.
 */

public interface DataSource {

    Observable<List<Repo>> getRepos(String userName, int page, int limit);
}
