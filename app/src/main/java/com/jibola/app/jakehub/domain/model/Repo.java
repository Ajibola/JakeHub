package com.jibola.app.jakehub.domain.model;

import com.google.auto.value.AutoValue;

import javax.annotation.Nullable;

/**
 * Created by hp on 8/9/2017.
 */

@AutoValue
public abstract class Repo {

    public abstract Long id();

    public abstract String name();

    @Nullable
    public abstract String full_name();

    public abstract String html_url();

    public abstract String description();

    @Nullable
    public abstract String language();

    public abstract int watchers();

    public abstract int stars();

    public abstract int forks();

    public static Repo create(
            Long id,
            String name,
            String full_name,
            String html_url,
            String description,
            String language,
            int watchers,
            int stars,
            int forks) {
        return new AutoValue_Repo(id, name, full_name, html_url, description, language, watchers, stars, forks);
    }

}
