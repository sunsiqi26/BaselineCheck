package com.gxa.child.controller;

import com.gxa.child.dto.ResultDO;
import com.gxa.child.pojo.ConditionVo;
import com.gxa.child.service.ScanDataService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 扫描数据(ScanData)表控制层
 *
 * @author makejava
 * @since 2020-06-18 14:02:47
 */
@Controller
@RequestMapping("scanData")
public class ScanDataController {
    /**
     * 服务对象
     */
    @Resource
    private ScanDataService scanDataService;


    /**
     * 扫描类型页
     * @return
     */
    @GetMapping("typeList")
    public String TypeList(){
        return "scandata/scandata-typelist";
    }

    /**
     * 扫描类型列表
     * @param boardId
     * @return
     */
    @PostMapping("typeCodeList")
    @ResponseBody
    public ResultDO scanTypeList(String boardId){
        return this.scanDataService.queryScanDataTypeList(boardId);
    }

    /**
     * 具体类型的扫描数据列表页
     * @return
     */
    @GetMapping("historyList")
    public String HistoryList(){
        return "scandata/scandata-historylist";
    }

    /**
     * 扫描历史列表
     * @param conditionVo
     * @return
     */
    @PostMapping("scanHistoryList")
    @ResponseBody
    public ResultDO History(ConditionVo conditionVo){
        return this.scanDataService.queryAllByBoardIdAndTypeCode(conditionVo);
    }

    /**
     * 通过id删除数据
     * @param ids
     * @return
     */
    @PostMapping("/data/delete")
    @ResponseBody
    public ResultDO delete(@RequestParam(name = "ids[]") String[] ids){
        return scanDataService.delete(ids);
    }


    /**
     * 基本信息展示页面
     * @return
     */
    @GetMapping("scanDetail/2")
    public String baseInfoPage(){
        return "scandata/base-info";
    }

    /**
     * 安装软件信息展示页面
     * @return
     */
    @GetMapping("scanDetail/3")
    public String installInfoPage(){
        return "scandata/install-info";
    }

    /**
     * 网络信息展示页面
     * @return
     */
    @GetMapping("scanDetail/4")
    public String networkInfoPage(){
        return "scandata/network-info";
    }
    /**
     * 进程信息展示页面
     * @return
     */
    @GetMapping("scanDetail/5")
    public String processInfoPage(){
        return "scandata/process-info";
    }
    /**
     * 服务信息展示页面
     * @return
     */
    @GetMapping("scanDetail/6")
    public String serviceInfoPage(){
        return "scandata/service-info";
    }
    /**
     * 主机信息展示页面
     * @return
     */
    @GetMapping("scanDetail/7")
    public String hostInfoPage(){
        return "scandata/host-info";
    }
    /**
     * 主机信息展示页面
     * @return
     */
    @GetMapping("scanDetail/8")
    public String disableInfoPage(){
        return "scandata/disable-service";
    }
    /**
     * 主机信息展示页面
     * @return
     */
    @GetMapping("scanDetail/9")
    public String baselineInfoPage(){
        return "scandata/baseline-inspection";
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @PostMapping("scanDetail")
    @ResponseBody
    public ResultDO scanDetail(Long id) {
        return this.scanDataService.queryById(id);
    }

    /**
     * 查询已检测主机数量
     * @return
     */
    @PostMapping("countDevice")
    @ResponseBody
    public ResultDO countDevice(){
        return this.scanDataService.countBoardID();
    }

    /**
     * 查询已检测类型数量
     * @return
     */
    @PostMapping("countType")
    @ResponseBody
    public ResultDO countType(){
        return this.scanDataService.countType();
    }

    /**
     * 查询注册主机数量
     * @return
     */
    @PostMapping("countAll")
    @ResponseBody
    public ResultDO countAll(){
        return this.scanDataService.countNum();
    }

}