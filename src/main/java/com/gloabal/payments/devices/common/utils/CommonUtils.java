package com.gloabal.payments.devices.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Supplier;

public class CommonUtils {
    private static final Logger LOGGER =  LoggerFactory.getLogger(CommonUtils.class);

    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            return Optional.ofNullable(resolver.get());
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }

    public static String toJson(Object from) {
        String json = null;
        try {
            if (from != null) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                json = mapper.writeValueAsString(from);
            }
        } catch (JsonProcessingException e) {
            LOGGER.info("Unable to transform object to json : " + e.getMessage());
        }
        return json;
    }
}
