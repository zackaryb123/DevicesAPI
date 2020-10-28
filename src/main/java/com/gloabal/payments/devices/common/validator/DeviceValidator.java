package com.gloabal.payments.devices.common.validator;

import com.gloabal.payments.devices.common.modal.Device;
import com.gloabal.payments.devices.common.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DeviceValidator implements Validator {
    private static final Logger LOGGER =  LoggerFactory.getLogger(DeviceValidator.class);

    @Override
    public boolean supports(Class<?> clazz) {
        return Device.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof Device) {
            if (!validateSerialNumber((Device) target)) {
                errors.rejectValue(
                        "serialNumber",
                        "ER003",
                        "The serial number entered can include a - z, A - Z, 0 - 9 and hyphen. Please correct your entry.");
            } else if (!validateMachineCode((Device) target)) {
                errors.rejectValue(
                        "machineCode",
                        "ER001",
                        "The machine code is incorrect. Check the Machine code you provided and try again.");
            }
        }
    }

    private boolean validateSerialNumber(Device device) {
        return CommonUtils.resolve(device::getSerialNumber)
                .isPresent() && device.getSerialNumber()
                        .matches("^[a-zA-Z0-9]+[-]+[a-zA-Z0-9]*$");
    }

    private boolean validateMachineCode(Device device) {
        return CommonUtils.resolve(device::getMachineCode)
                .isPresent() && !device.getMachineCode().isEmpty();
    }
}
