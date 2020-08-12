package com.gxa.child.dao;

import com.gxa.child.pojo.ConditionVo;
import com.gxa.child.pojo.ScanData;
import com.gxa.child.pojo.Type;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 扫描数据(ScanData)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-18 14:02:47
 */
public interface ScanDataMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ScanData queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ScanData> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param conditionVo 实例对象
     * @return 对象列表
     */
    List<ScanData> queryAll(@Param("conditionVo") ConditionVo conditionVo);


    /**
     * 通过主板ID查询相关扫描类型
     * @param boardId
     * @return
     */
    List<Type> queryScanTypeByBoardId(@Param("boardId") String boardId);

    /**
     * 新增数据
     *
     * @param scanData 实例对象
     * @return 影响行数
     */
    int insert(ScanData scanData);

    /**
     * 修改数据
     *
     * @param scanData 实例对象
     * @return 影响行数
     */
    int update(ScanData scanData);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Object id);

    /**
     * 删除所有
     * @param ids : id数组
     */
    void deleteAllByPk(@Param("ids") String[] ids);

    /**
     * 显示已检测主机数量
     * @return
     */
    Integer countByBoardId();

    /**
     * 显示已检测类型数量
     * @return
     */
    Integer countByTypeCode();

    /**
     * 显示已检测总数
     * @return
     */
    Integer countAll();

}