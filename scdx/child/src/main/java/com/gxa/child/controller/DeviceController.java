package com.gxa.child.controller;

import com.gxa.child.dto.ResultDO;
import com.gxa.child.pojo.ConditionVo;
import com.gxa.child.pojo.Device;
import com.gxa.child.service.DeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 设备表(Device)表控制层
 *
 * @author makejava
 * @since 2020-06-17 17:32:20
 */
@Controller
@RequestMapping("device")
public class DeviceController {
    /**
     * 服务对象
     */
    @Resource
    private DeviceService deviceService;


    /**
     * 打开设备列表
     * @return
     */
    @GetMapping("deviceList")
    public String deviceList(){
        return "device/device-list";
    }


    /**
     * 返回设备json数据
     * @param conditionVo
     * @return
     */
    @PostMapping("List")
    @ResponseBody
    public ResultDO List(ConditionVo conditionVo){ return deviceService.list(conditionVo); }


    /**
     * 删除的操作
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public ResultDO delete(@RequestParam(name = "ids[]") String[] ids){
        return deviceService.delete(ids);
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Device selectOne(Long id) {
        return this.deviceService.queryById(id);
    }

    /**
     * 查询注册主机数量
     * @return
     */
    @PostMapping("countDevice")
    @ResponseBody
    public ResultDO countDevice(){
        return this.deviceService.count();
    }

}