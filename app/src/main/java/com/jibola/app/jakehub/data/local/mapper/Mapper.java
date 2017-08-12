package com.jibola.app.jakehub.data.local.mapper;

import java.util.Collection;

/**
 * Created by hp on 8/10/2017.
 */

public interface Mapper<Source, Dest> {

    Dest mapObject(Source source);

    Collection<Dest> mapObjectCollection(Collection<Source> sources);
}
