package com.gxa.child.dao;

import com.gxa.child.pojo.Admin;
import com.gxa.child.pojo.ConditionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface AdminMapper {

    /**
     * 查询所有
     * @return
     */
    List<Admin> findAll();

    /**
     * 增
     * @param admin
     */
    void save(@Param("admin")Admin admin);

    /**
     * 删
     * @Param id
     */
    void deleteByPk(@Param("id") Long id);

    /**
     * 查（静态sql）
     * @Param name
     */
    Admin findByName(@Param("adminName") String adminName);

    /**
     * 查（静态sql）
     * @param id
     * @return
     */
    Admin findById(@Param("id") Long id);

    /**
     * 查（动态sql）
     * @Param conditionVo
     * @return
     */
    List<Admin> search(@Param("conditionVo") ConditionVo conditionVo);


    /**
     * 改
     * @Param admin
     */
    void update(@Param("admin") Admin admin);


    /**
     * 软删除
     * @param str :id数组
     */
    void deleteAll(@Param("str")String[] str);




}
