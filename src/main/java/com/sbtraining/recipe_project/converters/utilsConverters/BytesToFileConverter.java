package com.sbtraining.recipe_project.converters.utilsConverters;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by sousaJ on 18/02/2021
 * in package - com.sbtraining.recipe_project.converters.utilsConverters
 **/
@Component
public class BytesToFileConverter implements Converter<byte[], InputStream> {

    @Override
    public InputStream convert(byte[] bytes) {
        byte[] byteArray = new byte[bytes.length];
        int i = 0;

        for (Byte b: bytes){
            byteArray[i++] = b;
        }
        return new ByteArrayInputStream(byteArray);
    }
}
