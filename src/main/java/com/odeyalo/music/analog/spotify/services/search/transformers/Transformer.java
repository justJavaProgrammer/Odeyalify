package com.odeyalo.music.analog.spotify.services.search.transformers;

/**
 * from T to R
 *
 * @param <T>
 * @param <R>
 */
public interface Transformer<T, R> {
    R transformToEntity(T t);

    T transformFromEntity(R r);
}
