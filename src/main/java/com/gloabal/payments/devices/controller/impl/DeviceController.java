package com.gloabal.payments.devices.controller.impl;

import com.gloabal.payments.devices.common.modal.Device;
import com.gloabal.payments.devices.common.validator.DeviceValidator;
import com.gloabal.payments.devices.controller.DeviceApi;
import com.gloabal.payments.devices.common.modal.DeviceResponse;
import com.gloabal.payments.devices.service.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.List;

@Controller
public class DeviceController implements DeviceApi {
    private static final Logger LOGGER =  LoggerFactory.getLogger(DeviceController.class);

    @Autowired
    private DeviceValidator deviceValidator;

    @Autowired
    private DeviceService deviceService;

    @Override
    public ResponseEntity<DeviceResponse> createDevice(
            String contentType,
            String accept,
            Device device
    ) throws Exception {
        LOGGER.info("*** START CREATE DEVICE ***");
        LOGGER.info("*** DEVICE REQUEST : " + device);

        DeviceResponse response = new DeviceResponse();
        List<Device> devices = deviceService.createDevice(device);
        response.setDevices(devices);

        LOGGER.info("*** END CREATE DEVICE ***");
        return new ResponseEntity<DeviceResponse>(new DeviceResponse(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DeviceResponse> findDevice(
            String contentType,
            String accept,
            String serialNumber,
            String machineCode
    ) throws Exception {
        LOGGER.info("*** START CREATE DEVICE ***");
        LOGGER.info("*** SERIAL NUMBER : " + serialNumber);

        DeviceResponse response = new DeviceResponse();
        List<Device> devices = deviceService.findDevice(serialNumber, machineCode);
        response.setDevices(devices);

        LOGGER.info("*** END CREATE DEVICE ***");
        return new ResponseEntity<DeviceResponse>(new DeviceResponse(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DeviceResponse> updateDevice(
            String contentType,
            String accept,
            String serialNumber,
            Device device
    ) throws Exception {
        LOGGER.info("*** START CREATE DEVICE ***");
        LOGGER.info("*** DEVICE REQUEST : " + device);
        LOGGER.info("*** SERIAL NUMBER : " + serialNumber);

        DeviceResponse response = new DeviceResponse();
        List<Device> devices = deviceService.updateDevice(device, serialNumber);
        response.setDevices(devices);

        LOGGER.info("*** END CREATE DEVICE ***");
        return new ResponseEntity<DeviceResponse>(new DeviceResponse(), HttpStatus.OK);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(deviceValidator);
    }
}
