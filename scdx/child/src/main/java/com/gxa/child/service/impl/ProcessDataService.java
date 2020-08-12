package com.gxa.child.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gxa.child.pojo.Device;
import com.gxa.child.pojo.PyData;
import com.gxa.child.pojo.ScanData;
import com.gxa.child.pojo.TypeConst;
import com.gxa.child.service.DeviceService;
import com.gxa.child.service.ScanDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 解析python客户端传来的数据
 */
@Service
public class ProcessDataService {
    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ScanDataService scanDataServcie;

    /**
     * 解析数据并处理
     * @param data
     * @throws JsonProcessingException
     */
    public void process(String data) throws JsonProcessingException{
        //将data转换成PyData
        PyData pyData = (PyData) getEntityData(data,PyData.class);
        //判断操作类型
        if (TypeConst.REGISTER.equals(pyData.getTypeCode())) {
            String deviceData = pyData.getData();
            //替换单引号
            deviceData = deviceData.replaceAll("'","\"");
            Device device = (Device) getEntityData(deviceData,Device.class);
            //上传数据库
            deviceService.insert(device);
        }
        else{
            //存入scan_data表中
            //数据库自动生成创建时间
            ScanData scanData = new ScanData(pyData.getBoardId(), pyData.getTypeCode(), pyData.getData());
            scanDataServcie.insert(scanData);
        }

    }

    /**
     * 获取对应实体的对象
     * @param data
     * @param clazz
     * @return
     * @throws JsonProcessingException
     */
    private Object getEntityData(String data, Class clazz) throws JsonProcessingException{
        //用Jackson进行数据解析
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(data,clazz);
    }

}
