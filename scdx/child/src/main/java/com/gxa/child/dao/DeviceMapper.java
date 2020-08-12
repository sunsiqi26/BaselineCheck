package com.gxa.child.dao;

import com.gxa.child.pojo.ConditionVo;
import com.gxa.child.pojo.Device;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 设备表(Device)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-17 17:32:20
 */
public interface DeviceMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Device queryById(Long id);

    /**
     * 通过主板ID查询单条数据
     *
     * @param boardId 主键
     * @return 实例对象
     */
    Device queryByBoradId(@Param("boardId") String boardId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Device> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param conditionVo 实例对象
     * @return 对象列表
     */
    List<Device> queryAll(@Param("conditionVo") ConditionVo conditionVo);

    /**
     * 新增数据
     *
     * @param device 实例对象
     * @return 影响行数
     */
    int insert(Device device);

    /**
     * 修改数据
     *
     * @param device 实例对象
     * @return 影响行数
     */
    int update(Device device);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 删除所有
     * @param ids : id数组
     */
    void deleteAllByPk(@Param("ids") String[] ids);

    /**
     * 查询已注册主机数量
     * @return
     */
    Integer countByBoardId();

}