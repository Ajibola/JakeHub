package com.jibola.app.jakehub.data.di;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jibola.app.jakehub.BuildConfig;
import com.jibola.app.jakehub.data.network.GitHubApi;
import com.jibola.app.jakehub.data.network.entity.GsonAdapterFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hp on 8/9/2017.
 */

@Module
public class ApiModule {

    private static final String API_BASE_URL = "NAME_BASE_URL";

    @Provides
    @Named(API_BASE_URL)
    String provideBaseUrlString() {
        return GitHubApi.API_ENDPOINT;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .registerTypeAdapterFactory(GsonAdapterFactory.create())
                .create();
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) { okHttpClient.addInterceptor(loggingInterceptor); }
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverter(Gson gson) {
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(new GsonBuilder()
                .registerTypeAdapterFactory(GsonAdapterFactory.create())
                .create());

        return gsonConverterFactory;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(
            Gson gson,
            OkHttpClient okHttpClient,
            @Named(API_BASE_URL) String baseUrl,
            GsonConverterFactory gsonConverterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    GitHubApi provideGitHubApi(Retrofit retrofit) {
        return retrofit.create(GitHubApi.class);
    }


}
