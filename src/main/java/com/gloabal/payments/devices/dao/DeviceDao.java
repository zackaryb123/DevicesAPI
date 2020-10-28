package com.gloabal.payments.devices.dao;

import com.gloabal.payments.devices.common.modal.Device;

public interface DeviceDao {
    Device insertDeviceDetails(Device device);
    Device retrieveDeviceDetails(String serialNumber, String machineCode);
    Device retrieveDeviceDetailsBySerialNumber(String serialNumber);
    Device retrieveDeviceDetailsByMachineCode(String machineCode);
    String updateDeviceDetails(Device device, String serialNumber);
}
