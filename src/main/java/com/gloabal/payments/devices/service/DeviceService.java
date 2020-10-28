package com.gloabal.payments.devices.service;

import com.gloabal.payments.devices.common.modal.Device;

import java.util.List;

public interface DeviceService {
    List<Device> createDevice(Device request);
    List<Device> findDevice(String serialNumber, String machineCode);
    List<Device> updateDevice(Device request, String serialNumber);
}
