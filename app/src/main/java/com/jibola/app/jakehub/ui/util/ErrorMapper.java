package com.jibola.app.jakehub.ui.util;

import android.content.Context;

import com.bumptech.glide.load.HttpException;
import com.jibola.app.jakehub.R;

import javax.inject.Inject;

/**
 * Created by hp on 8/12/2017.
 */

public class ErrorMapper {

    @Inject
    Context context;

    @Inject
    public ErrorMapper() {

    }

    public String getErrorString(Throwable throwable) {
        if (throwable instanceof HttpException) {
            return context.getString(R.string.error_http);
        } else {
            return context.getString(R.string.error_default);
        }
    }
}
