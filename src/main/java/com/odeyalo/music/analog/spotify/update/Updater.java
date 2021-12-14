package com.odeyalo.music.analog.spotify.update;

public interface Updater<T> {
    /**
     * Update data and save it to storage
     * @param newData - data for saving
     * @return new object with new data
     */
    T update(T newData);
}
