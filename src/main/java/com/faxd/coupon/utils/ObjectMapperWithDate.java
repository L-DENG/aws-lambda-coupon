package com.faxd.coupon.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperWithDate {

    public static ObjectMapper getObjectMapper(){

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper;
    }
}
