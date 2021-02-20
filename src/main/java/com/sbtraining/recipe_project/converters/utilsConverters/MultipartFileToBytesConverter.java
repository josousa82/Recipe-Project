package com.sbtraining.recipe_project.converters.utilsConverters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by sousaJ on 18/02/2021
 * in package - com.sbtraining.recipe_project.converters
 **/
@Component
public class MultipartFileToBytesConverter implements Converter<MultipartFile, byte[]> {

    @Override
    public byte[] convert(MultipartFile multipartFile) {

        byte[] byteObjects = new byte[0];
        try {
            byteObjects = new byte[multipartFile.getBytes().length];

            int i = 0;

            for (byte b: multipartFile.getBytes()){
                byteObjects[i++] = b;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteObjects;
    }
}
