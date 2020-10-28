package com.gloabal.payments.devices.dao.impl;

import com.gloabal.payments.devices.common.modal.Device;
import com.gloabal.payments.devices.dao.DeviceDao;
import com.gloabal.payments.devices.dao.mapper.DeviceDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Repository
public class DeviceDaoImpl implements DeviceDao {
    private static final Logger LOGGER =  LoggerFactory.getLogger(DeviceDaoImpl.class);

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    @Transactional
    public Device insertDeviceDetails(Device device) {
        String query = "INSERT INTO DEVICE_TABLE (SERIAL_NUMBER, MACHINE_CODE, DEVICE_NAME) VALUES (?, ?, ?)";
        Object[] args = new Object[]{
                device.getSerialNumber(),
                device.getMachineCode(),
                device.getDeviceName()
        };
        boolean isInserted =  jdbcTemplate.update(query, args) > 0;
        if (isInserted) {
            return device;
        }
        return null;
    }

    @Override
    @Transactional
    public Device retrieveDeviceDetails(String serialNumber, String machineCode) {
        String query = "SELECT * FROM DEVICE_TABLE WHERE SERIAL_NUMBER = ? AND MACHINE_CODE = ?";
        return jdbcTemplate.queryForObject(
                query,
                new Object[]{
                        serialNumber,
                        machineCode
                },
                new DeviceDetailsMapper()
        );
    }

    @Override
    @Transactional
    public Device retrieveDeviceDetailsBySerialNumber(String serialNumber) {
        String query = "SELECT * FROM DEVICE_TABLE WHERE SERIAL_NUMBER = ?";
        return jdbcTemplate.queryForObject(
                query,
                new Object[]{
                        serialNumber
                },
                new DeviceDetailsMapper()
        );
    }

    @Override
    @Transactional
    public Device retrieveDeviceDetailsByMachineCode(String machineCode) {
        String query = "SELECT * FROM DEVICE_TABLE WHERE MACHINE_CODE = ?";
        return jdbcTemplate.queryForObject(
                query,
                new Object[]{
                        machineCode
                },
                new DeviceDetailsMapper()
        );
    }

    @Override
    @Transactional
    public String updateDeviceDetails(Device device, String serialNumber) {
        String query = "UPDATE DEVICE_TABLE SET DEVICE_NAME = ? WHERE SERIAL_NUMBER = ? AND MACHINE_CODE = ? ";
        Object[] args = new Object[] {
                device.getDeviceName(),
                device.getSerialNumber(),
                device.getMachineCode()
        };
        boolean isUpdated =  jdbcTemplate.update(query, args) > 0;
        if (isUpdated) {
            return serialNumber;
        }
        return null;
    }
}
