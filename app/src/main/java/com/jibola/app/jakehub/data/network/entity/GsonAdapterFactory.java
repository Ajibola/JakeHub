package com.jibola.app.jakehub.data.network.entity;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * Created by hp on 8/10/2017.
 */

@GsonTypeAdapterFactory
public abstract class GsonAdapterFactory implements TypeAdapterFactory {

    public static TypeAdapterFactory create() {
        return new AutoValueGson_GsonAdapterFactory();
    }
}
