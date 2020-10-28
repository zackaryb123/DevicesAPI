package com.gloabal.payments.devices.common.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gloabal.payments.devices.common.modal.DeviceError;
import com.gloabal.payments.devices.common.modal.ErrorMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class ErrorHandler {
    private static final Logger LOGGER =  LoggerFactory.getLogger(ErrorHandler.class);

    @Value("classpath:ErrorMessage.json")
    private Resource errorMessagesJson;

    private ErrorMessages errorMessages;
    private Map<String, String> errorKeyMap;
    private Map<String, String> errorMessageMap;

    @PostConstruct
    private void init() {
        if (Objects.nonNull(errorMessagesJson)) {
            errorMessages = getPropertyResources(errorMessagesJson);
        }
        if (Objects.nonNull(errorMessages)) {
            mapErrorMessageValues(errorMessages);
        }
    }

    private void mapErrorMessageValues(ErrorMessages errorMessages) {
        errorKeyMap = new HashMap<>();
        errorMessageMap = new HashMap<>();
        for (Map.Entry<String, DeviceError> error: errorMessages.getErrorKeys().entrySet()) {
            errorKeyMap.put(error.getKey(), error.getValue().getResourceKey());
            errorMessageMap.put(error.getKey(), error.getValue().getMessage());
        }
    }

    protected ErrorMessages getPropertyResources(Resource resource) {
        ErrorMessages errorMessages = null;
        if (resource.exists()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                errorMessages = mapper.readValue(resource.getFile(), ErrorMessages.class);
            } catch (Exception ex) {
                LOGGER.error("Unable to Map ErrorMessage.json to Object");
            }
        }
        return errorMessages;
    }

    public String getResourceKey(String errorCode) {
        return errorKeyMap.get(errorCode);
    }

    public String getErrorMessage(String errorCode) {
        return errorMessageMap.get(errorCode);
    }
}