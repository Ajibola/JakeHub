package com.jibola.app.jakehub.ui.util;

import android.content.Context;

import com.jibola.app.jakehub.R;

import javax.inject.Inject;

import retrofit2.HttpException;

/**
 * Created by hp on 8/12/2017.
 */

public class ErrorMapper {

    Context context;

    @Inject
    public ErrorMapper(Context context) {
        this.context = context;
    }

    public String getErrorString(Throwable throwable) {
        if (throwable instanceof HttpException) {
            return context.getString(R.string.error_http);
        } else {
            return context.getString(R.string.error_default);
        }
    }
}
