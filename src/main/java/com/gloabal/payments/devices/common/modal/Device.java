package com.gloabal.payments.devices.common.modal;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Device implements Serializable {
    private String serialNumber = null;
    private String machineCode = null;
    private String deviceName = null;
}
