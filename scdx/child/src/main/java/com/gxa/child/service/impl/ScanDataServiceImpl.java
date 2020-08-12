package com.gxa.child.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxa.child.dao.TypeMapper;
import com.gxa.child.dto.Response;
import com.gxa.child.dto.ResultDO;
import com.gxa.child.exception.DataException;
import com.gxa.child.pojo.ConditionVo;
import com.gxa.child.pojo.ScanData;
import com.gxa.child.dao.ScanDataMapper;
import com.gxa.child.pojo.Type;
import com.gxa.child.service.ScanDataService;
import com.gxa.child.utils.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 扫描数据(ScanData)表服务实现类
 *
 * @author makejava
 * @since 2020-06-18 14:02:47
 */
@Service("scanDataService")
public class ScanDataServiceImpl implements ScanDataService {
    @Resource
    private ScanDataMapper scanDataMapper;

    @Resource
    private TypeMapper typeMapper;

    @Autowired
    private DataValidator dataValidator;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ResultDO queryById(Long id) {
        ScanData scanData = this.scanDataMapper.queryById(id);
        return Response.success("查询单条数据成功",scanData);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ScanData> queryAllByLimit(int offset, int limit) {
        return this.scanDataMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 通过主板ID和类型进行查询
     * @param conditionVo
     * @return
     */
    @Override
    public ResultDO queryAllByBoardIdAndTypeCode(ConditionVo conditionVo) {
        // 分页
        PageHelper.startPage(conditionVo.getPage(),conditionVo.getPageSize());
        // 查询数据库
        List<ScanData> list = scanDataMapper.queryAll(conditionVo);
        PageInfo<ScanData> pageInfo = new PageInfo<>(list);

        return Response.success("获取数据成功!",pageInfo);
    }

    /**
     * 显示扫描数据类型列表
     * @param boardId
     * @return
     */
    @Override
    public ResultDO queryScanDataTypeList(String boardId) {
        if (boardId == null){
            throw new DataException(3009,"设备ID不能为空");
        }
        List<Type> types = this.scanDataMapper.queryScanTypeByBoardId(boardId);
        return Response.success("获取扫描类型列表成功",types);
    }

    /**
     * 新增数据
     *
     * @param scanData 实例对象
     * @return 实例对象
     */
    @Override
    public ScanData insert(ScanData scanData) {
        //数据校验
        dataValidator.validate(scanData);
        //检测提交类型是否存在
        Type type = this.typeMapper.queryByTypeCode(scanData.getTypeCode());
        if (type==null){
            throw new DataException(3008,"不存在的数据类型");
        }


        this.scanDataMapper.insert(scanData);
        return scanData;
    }

    /**
     * 修改数据
     *
     * @param scanData 实例对象
     * @return 实例对象
     */
    @Override
    public ResultDO update(ScanData scanData) {
        this.scanDataMapper.update(scanData);
        return Response.success("更新成功");
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Object id) {
        return this.scanDataMapper.deleteById(id) > 0;
    }

    /**
     * 通过id数组删除对象
     * @param ids ： 删除对象的id数组
     * @return
     */
    @Override
    public ResultDO delete(String[] ids) {
        scanDataMapper.deleteAllByPk(ids);
        return Response.success("删除成功!");
    }

    /**
     * 已检测主机数量
     * @return
     */
    @Override
    public ResultDO countBoardID() {
        Integer num = scanDataMapper.countByBoardId();
        return Response.success("检测成功!",num);
    }

    /**
     * 已检测类型数量
     * @return
     */
    @Override
    public ResultDO countType() {
        Integer num = scanDataMapper.countByTypeCode();
        return Response.success("检测成功!",num);
    }

    /**
     * 已检测总数
     * @return
     */
    @Override
    public ResultDO countNum() {
        Integer num = scanDataMapper.countAll();
        return Response.success("检测成功!",num);
    }
}