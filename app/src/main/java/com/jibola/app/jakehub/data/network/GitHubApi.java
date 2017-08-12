package com.jibola.app.jakehub.data.network;

import com.jibola.app.jakehub.data.network.entity.GitRepo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by hp on 8/7/2017.
 */

public interface GitHubApi {

    public static final String API_ENDPOINT = "https://api.github.com/";

    @GET("users/{user}/repos")
    Observable<List<GitRepo>> getUserRepos(
            @Path("user") String user, @Query("page") int page, @Query
            ("per_page") int
            limit);
}
