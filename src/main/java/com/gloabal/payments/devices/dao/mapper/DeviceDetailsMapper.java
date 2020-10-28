package com.gloabal.payments.devices.dao.mapper;

import com.gloabal.payments.devices.common.modal.Device;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class DeviceDetailsMapper implements RowMapper<Device> {
    @Override
    public Device mapRow(ResultSet rs, int rowNum) throws SQLException {
        Device device = new Device();
        device.setSerialNumber(rs.getString(""));
        device.setMachineCode(rs.getString(""));
        device.setDeviceName(rs.getString(""));
        return device;
    }
}
