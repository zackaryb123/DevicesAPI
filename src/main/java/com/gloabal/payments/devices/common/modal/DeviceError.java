package com.gloabal.payments.devices.common.modal;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class DeviceError {
    private String resourceKey = null;
    private String errorCode = null;
    private String message = null;
}
