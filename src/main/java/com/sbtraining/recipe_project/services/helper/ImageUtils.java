package com.sbtraining.recipe_project.services.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Created by sousaJ on 05/01/2021
 * in package - com.sbtraining.recipe_project.services.helper
 **/
@Slf4j
@Service
public class ImageUtils {

    public byte[] compressBytes(byte[] data){
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[data.length];
        while(!deflater.finished()){
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try{
            outputStream.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        log.info("Compressed file byte size - {}", outputStream.toByteArray().length);
        return  outputStream.toByteArray();
    }


    public byte[] decompressFile(byte[] data){
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[data.length];
        try {
            while (!inflater.finished()){
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (DataFormatException | IOException e) {
            e.printStackTrace();
        }
        log.info("Decompressed file byte size - {}", outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }
}
