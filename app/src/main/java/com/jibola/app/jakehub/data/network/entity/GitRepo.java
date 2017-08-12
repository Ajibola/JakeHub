package com.jibola.app.jakehub.data.network.entity;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Nullable;

/**
 * Created by hp on 8/9/2017.
 */

@AutoValue
public abstract class GitRepo {

    @SerializedName("id")
    public abstract Long id();

    @SerializedName("name")
    public abstract String name();

    @Nullable
    @SerializedName("full_name")
    public abstract String full_name();

    @SerializedName("html_url")
    public abstract String html_url();

    @SerializedName("description")
    public abstract String description();

    @Nullable
    @SerializedName("language")
    public abstract String language();

    @SerializedName("watchers")
    public abstract int watchers();

    @SerializedName("stargazers_count")
    public abstract int stars();

    @SerializedName("forks")
    public abstract int forks();

    public static TypeAdapter<GitRepo> typeAdapter(Gson gson) {
        return new AutoValue_GitRepo.GsonTypeAdapter(gson);
    }
}
