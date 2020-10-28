package com.gloabal.payments.devices.service.impl;

import com.gloabal.payments.devices.common.exception.ServiceException;
import com.gloabal.payments.devices.common.modal.Device;
import com.gloabal.payments.devices.dao.DeviceDao;
import com.gloabal.payments.devices.service.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DeviceServiceImpl implements DeviceService {
    private static final Logger LOGGER =  LoggerFactory.getLogger(DeviceServiceImpl.class);

    @Autowired
    DeviceDao deviceDao;

    @Override
    public List<Device> createDevice(Device device) {
        List<Device> devices = new ArrayList<>();
        try {
            Device insertedDevice = deviceDao.insertDeviceDetails(device);
            devices.add(insertedDevice);
        } catch (DataAccessException ex) {
            LOGGER.error("Unable to connect to Database");
            throw new ServiceException("SYSTEM_ERROR");
        }
        return devices;
    }

    @Override
    public List<Device> findDevice(String serialNumber, String machineCode) {
        List<Device> devices = new ArrayList<>();

            Device device = null;
            if (Objects.nonNull(serialNumber) && Objects.nonNull(machineCode)) {
                try {
                    device = deviceDao.retrieveDeviceDetails(serialNumber, machineCode);
                } catch (EmptyResultDataAccessException ex) {
                    LOGGER.error("Device not found for Serial Number : " + serialNumber + " Machine Code : " + machineCode);
                    throw new ServiceException("ER004");
                } catch (DataAccessException ex) {
                    LOGGER.error("Unable to connect to Database");
                    throw new ServiceException("SYSTEM_ERROR");
                }
            } else if (Objects.isNull(serialNumber)) {
                try {
                    device = deviceDao.retrieveDeviceDetailsByMachineCode(machineCode);
                } catch (EmptyResultDataAccessException ex) {
                    LOGGER.error("Device not found for Machine Code : " + machineCode);
                    throw new ServiceException("ER002");
                } catch (DataAccessException ex) {
                    LOGGER.error("Unable to connect to Database");
                    throw new ServiceException("SYSTEM_ERROR");
                }
            } else {
                try {
                    device = deviceDao.retrieveDeviceDetailsBySerialNumber(serialNumber);
                } catch (EmptyResultDataAccessException ex) {
                    LOGGER.error("Device not found for Serial Number : " + serialNumber);
                    throw new ServiceException("ER004");
                } catch (DataAccessException ex) {
                    LOGGER.error("Unable to connect to Database");
                    throw new ServiceException("SYSTEM_ERROR");
                }
            }
            devices.add(device);
        return devices;
    }

    @Override
    public List<Device> updateDevice(Device device, String serialNumber) {
        List<Device> devices = new ArrayList<>();
        try {
            Device d = new Device();
            d.setSerialNumber(serialNumber);
            deviceDao.updateDeviceDetails(device, serialNumber);
            devices.add(d);
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Device not found for Serial Number : " + serialNumber);
            throw new ServiceException("ER004");
        } catch (DataAccessException ex) {
            LOGGER.error("Unable to connect to Database");
            throw new ServiceException("SYSTEM_ERROR");
        }
        return devices;
    }
}
