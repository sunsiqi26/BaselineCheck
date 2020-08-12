package com.gxa.child.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxa.child.dto.Response;
import com.gxa.child.dto.ResultDO;
import com.gxa.child.pojo.ConditionVo;
import com.gxa.child.pojo.Type;
import com.gxa.child.dao.TypeMapper;
import com.gxa.child.service.TypeService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Type)表服务实现类
 *
 * @author makejava
 * @since 2020-06-18 14:03:05
 */
@Service("typeService")
public class TypeServiceImpl implements TypeService {
    @Resource
    private TypeMapper typeMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Type queryById(Object id) {
        return this.typeMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Type> queryAllByLimit(int offset, int limit) {
        return this.typeMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 查询显示数据
     * @param conditionVo
     * @return
     */
    @Override
    public ResultDO List(ConditionVo conditionVo) {
        // 进行分页
        PageHelper.startPage(conditionVo.getPage(),conditionVo.getPageSize());

        // 条件处理
        if(!StringUtils.isEmpty(conditionVo.getKeywords())){
            conditionVo.setKeywords("%"+conditionVo.getKeywords()+"%");
        }

        // 先查询数据库
        List<Type> list = typeMapper.queryAll(conditionVo);

        // 进行pageInfo的包装
        PageInfo<Type> pageInfo = new PageInfo<>(list);

        return Response.success("获取数据成功!",pageInfo);
    }

    /**
     * 新增数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    @Override
    public Type insert(Type type) {
        this.typeMapper.insert(type);
        return type;
    }

    /**
     * 修改数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    @Override
    public Type update(Type type) {
        this.typeMapper.update(type);
        return this.queryById(type.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Object id) {
        return this.typeMapper.deleteById(id) > 0;
    }


}