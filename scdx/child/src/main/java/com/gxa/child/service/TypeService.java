package com.gxa.child.service;

import com.gxa.child.dto.ResultDO;
import com.gxa.child.pojo.ConditionVo;
import com.gxa.child.pojo.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Type)表服务接口
 *
 * @author makejava
 * @since 2020-06-18 14:03:05
 */
public interface TypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Type queryById(Object id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Type> queryAllByLimit(int offset, int limit);

    /**
     * 类型列表
     * @param conditionVo
     * @return
     */
    ResultDO List(@Param("conditionVo") ConditionVo conditionVo);

    /**
     * 新增数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    Type insert(Type type);

    /**
     * 修改数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    Type update(Type type);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);


}