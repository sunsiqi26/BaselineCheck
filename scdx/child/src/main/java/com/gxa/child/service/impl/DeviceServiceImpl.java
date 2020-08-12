package com.gxa.child.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxa.child.dto.Response;
import com.gxa.child.dto.ResultDO;
import com.gxa.child.pojo.ConditionVo;
import com.gxa.child.pojo.Device;
import com.gxa.child.dao.DeviceMapper;
import com.gxa.child.service.DeviceService;
import com.gxa.child.utils.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * 设备表(Device)表服务实现类
 *
 * @author makejava
 * @since 2020-06-17 17:32:20
 */
@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {
    @Resource
    private DeviceMapper deviceMapper;
    @Autowired
    private DataValidator dataValidator;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Device queryById(Long id) {
        return this.deviceMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Device> queryAllByLimit(int offset, int limit) {
        return this.deviceMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param device 实例对象
     * @return 实例对象
     */
    @Override
    public ResultDO insert(Device device) {
        //数据校验
        dataValidator.validate(device);
        //操作时间
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        //插入或更新
        Device dbDevice = this.deviceMapper.queryByBoradId(device.getBoardId());
        if (dbDevice != null){
            dbDevice.setComputerName(device.getComputerName());
            dbDevice.setUpdateTime(timestamp);
            this.deviceMapper.update(dbDevice);
        }else {
            device.setCreateTime(timestamp);
            device.setUpdateTime(timestamp);
            this.deviceMapper.insert(device);
        }

        return Response.success("添加成功");
    }

    /**
     * 修改数据
     *
     * @param device 实例对象
     * @return 实例对象
     */
    @Override
    public Device update(Device device) {
        this.deviceMapper.update(device);
        return this.queryById(device.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.deviceMapper.deleteById(id) > 0;
    }

    /**
     * 设备列表
     * @param conditionVo ：管理员对象，存放搜索数据
     * @return
     */
    @Override
    public ResultDO list(ConditionVo conditionVo) {
        //分页操作
        PageHelper.startPage(conditionVo.getPage(),conditionVo.getPageSize());
        //扩大条件匹配范围
        if(!StringUtils.isEmpty(conditionVo.getKeywords())){
            conditionVo.setKeywords("%"+conditionVo.getKeywords()+"%");
        }
        //查询数据库
        List<Device> list = deviceMapper.queryAll(conditionVo);
        //封装pageinfo
        PageInfo<Device> pageInfo = new PageInfo<>(list);
        return Response.success("获取数据成功",pageInfo);
    }

    /**
     * 设备列表删除
     * @param ids : 删除数据的id数组
     * @return
     */
    @Override
    public ResultDO delete(String[] ids) {
        deviceMapper.deleteAllByPk(ids);
        return Response.success("删除成功!");
    }

    /**
     * 查询已注册设备数量
     * @return
     */
    @Override
    public ResultDO count() {
        Integer num = deviceMapper.countByBoardId();
        return Response.success("查询成功!",num);
    }
}