package com.gloabal.payments.devices.common.modal;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@ConstructorBinding
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class BaseResponse implements Serializable {
    private List<DeviceError> errors = null;
}
