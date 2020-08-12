package com.gxa.child.service;

import com.gxa.child.dto.ResultDO;
import com.gxa.child.pojo.ConditionVo;
import com.gxa.child.pojo.Device;
import java.util.List;

/**
 * 设备表(Device)表服务接口
 *
 * @author makejava
 * @since 2020-06-17 17:32:20
 */
public interface DeviceService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Device queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Device> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param device 实例对象
     * @return 实例对象
     */
    ResultDO insert(Device device);

    /**
     * 修改数据
     *
     * @param device 实例对象
     * @return 实例对象
     */
    Device update(Device device);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 设备列表
     * 查询检索二合一
     * @param conditionVo
     * @return
     */
    ResultDO list(ConditionVo conditionVo);

    /**
     * 设备删除
     * @param ids
     * @return
     */
    ResultDO delete(String[] ids);

    /**
     * 查询已注册设备数量
     * @return
     */
    ResultDO count();
}