package com.gloabal.payments.devices.controller;

import com.gloabal.payments.devices.common.modal.Device;
import com.gloabal.payments.devices.common.modal.DeviceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;

public interface DeviceApi {
    // POST Device Endpoint
    @PostMapping(
            value = "/device",
            produces = {"application/json"},
            consumes = {"application/json"}
    ) ResponseEntity<DeviceResponse> createDevice(
            @RequestHeader(value = "Content-Type", required = true)
                    String contentType,
            @RequestHeader(value = "Accept", required = true)
                    String accept,
            @Valid @RequestBody(required = true) Device device
    ) throws Exception;

    // GET Device Endpoint
    @GetMapping(
            value = "/device",
            produces = {"application/json"},
            consumes = {"application/json"}
    ) ResponseEntity<DeviceResponse> findDevice(
            @RequestHeader(value = "Content-Type", required = true)
                    String contentType,
            @RequestHeader(value = "Accept", required = true)
                    String accept,
            @Valid @RequestHeader(value = "serialNumber", required = false)
                    String serialNumber,
            @Valid @RequestHeader(value = "machineCode", required = false)
                    String machineCode
    ) throws Exception;

    // PUT Device Endpoint
    @PutMapping(
            value = "/device",
            produces = {"application/json"},
            consumes = {"application/json"}
    ) ResponseEntity<DeviceResponse>  updateDevice(
            @RequestHeader(value = "Content-Type", required = true)
                    String contentType,
            @RequestHeader(value = "Accept", required = true)
                    String accept,
            @RequestHeader(value = "serialNumber", required = true)
                    String  serialNumber,
            @Valid @RequestBody(required = true) Device device
    ) throws Exception;
}
