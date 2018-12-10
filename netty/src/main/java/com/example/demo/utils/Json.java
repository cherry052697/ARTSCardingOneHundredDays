package com.example.demo.utils;


import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {
    public static <T> T toObject(String json, Class<T> targetClass) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, targetClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } 
    }
    
    public static String toJson(Object target) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonGenerator jsonGenerator = mapper.getFactory().createGenerator(out, JsonEncoding.UTF8);
            jsonGenerator.writeObject(target);
            jsonGenerator.flush();
            jsonGenerator.close();
            return new String(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    
}
