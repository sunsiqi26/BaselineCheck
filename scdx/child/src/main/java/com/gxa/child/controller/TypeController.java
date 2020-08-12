package com.gxa.child.controller;

import com.gxa.child.dto.ResultDO;
import com.gxa.child.pojo.ConditionVo;
import com.gxa.child.pojo.Type;
import com.gxa.child.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Type)表控制层
 *
 * @author makejava
 * @since 2020-06-18 14:03:05
 */
@Controller
@RequestMapping("type")
public class TypeController {
    /**
     * 服务对象
     */
    @Resource
    private TypeService typeService;

    /**
     * 类型列表页面
     * @return
     */
    @GetMapping("/list/page")
    public String listPage(){
        return "type/type-list";
    }

    /**
     * 列表数据
     * @param conditionVo
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public ResultDO list(ConditionVo conditionVo){
        return typeService.List(conditionVo);
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Type selectOne(Object id) {
        return this.typeService.queryById(id);
    }

}