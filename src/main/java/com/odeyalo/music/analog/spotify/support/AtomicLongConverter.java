package com.odeyalo.music.analog.spotify.support;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.concurrent.atomic.AtomicLong;

@Converter
public class AtomicLongConverter implements AttributeConverter<AtomicLong, Long> {

    @Override
    public Long convertToDatabaseColumn(AtomicLong attribute) {
        return attribute.get();
    }

    @Override
    public AtomicLong convertToEntityAttribute(Long value) {
        return new AtomicLong(value);
    }
}
