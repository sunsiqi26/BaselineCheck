package com.gxa.child.service;

import com.gxa.child.dto.ResultDO;
import com.gxa.child.pojo.ConditionVo;
import com.gxa.child.pojo.ScanData;
import java.util.List;

/**
 * 扫描数据(ScanData)表服务接口
 *
 * @author makejava
 * @since 2020-06-18 14:02:47
 */
public interface ScanDataService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ResultDO queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ScanData> queryAllByLimit(int offset, int limit);

    /**
     * 通过主板ID和类型进行查询
     * @param conditionVo
     * @return
     */
    ResultDO queryAllByBoardIdAndTypeCode(ConditionVo conditionVo);


    /**
     * 显示扫描数据类型列表
     * @param boardId
     * @return
     */
    ResultDO queryScanDataTypeList(String boardId);

    /**
     * 新增数据
     *
     * @param scanData 实例对象
     * @return 实例对象
     */
    ScanData insert(ScanData scanData);

    /**
     * 修改数据
     *
     * @param scanData 实例对象
     * @return 实例对象
     */
    ResultDO update(ScanData scanData);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

    /**
     * 数据删除
     * @param ids ： 删除对象的id数组
     * @return
     */
    ResultDO delete(String[] ids);

    /**
     * 已检测主机数量
     * @return
     */
    ResultDO countBoardID();

    /**
     * 已检测类型数量
     * @return
     */
    ResultDO countType();

    /**
     * 已检测总数
     * @return
     */
    ResultDO countNum();

}