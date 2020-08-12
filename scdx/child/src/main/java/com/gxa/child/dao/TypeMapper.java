package com.gxa.child.dao;

import com.gxa.child.pojo.ConditionVo;
import com.gxa.child.pojo.Type;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Type)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-18 14:03:05
 */
public interface TypeMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Type queryById(Object id);

    /**
     * 通过typeCode查询单条数据
     *
     * @param typeCode
     * @return 实例对象
     */
    Type queryByTypeCode(@Param("typeCode") Integer typeCode);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Type> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param conditionVo 实例对象
     * @return 对象列表
     */
    List<Type> queryAll(@Param("conditionVo") ConditionVo conditionVo);

    /**
     * 新增数据
     *
     * @param type 实例对象
     * @return 影响行数
     */
    int insert(Type type);

    /**
     * 修改数据
     *
     * @param type 实例对象
     * @return 影响行数
     */
    int update(Type type);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Object id);


}