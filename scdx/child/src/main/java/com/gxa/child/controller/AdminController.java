package com.gxa.child.controller;

import com.gxa.child.dto.Response;
import com.gxa.child.dto.ResultDO;
import com.gxa.child.exception.DataException;
import com.gxa.child.pojo.Admin;
import com.gxa.child.pojo.ConditionVo;
import com.gxa.child.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


/**
 * 总控
 * @author wangyaxian
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 登录页
     * @return
     */
    @GetMapping({"/","/loginPage"})
    public String loginPage(){
        return "login";
    }

    /**
     * 登录方法
     * @return
     */
    @PostMapping("/login/do")
    @ResponseBody
    public ResultDO login(Admin ad, HttpSession session) throws DataException {
        return adminService.login(ad,session);
    }

    /**
     * 退出方法
     * @return
     */
    @PostMapping("/logout")
    @ResponseBody
    public ResultDO logout(HttpSession session) {
        //将session中的数据置为null
        session.setAttribute("admin",null);
        return Response.success("退出成功");
    }


    /**
     * 用户首页
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "index";
    }


    /**
     * 欢迎页
     * @return
     */
    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    /**
     * 打开用户信息页
     * @return
     */
    @GetMapping("/user/userInfo")
    public String userInfo(){return "user/user-info";}

    /**
     * 返回用户信息json数据
     * @return
     */
    @PostMapping("/user/Info")
    @ResponseBody
    public ResultDO Info(){
        ConditionVo conditionVo = new ConditionVo();
        conditionVo.setPage(0);
        conditionVo.setPageSize(5);
        return adminService.list(conditionVo);
    }

    /**
     * 打开管理员列表页面
     * @return
     */
    @GetMapping("/admin/adminList")
    public String adminList(){return "admin/admin-list";}

    /**
     * 打开权限管理页面
     * @return
     */
    @GetMapping("/admin/adminAuthority")
    public String adminAuthority(){return "admin/admin-authority";}

    /**
     * 返回管理员json数据
     * @param conditionVo
     * @return
     */
    @PostMapping("/admin/List")
    @ResponseBody
    public ResultDO List(ConditionVo conditionVo){ return adminService.list(conditionVo); }

    /**
     * 打开管理员添加页
     * @return
     */
    @GetMapping("/admin/adminAdd")
    public String adminAdd(){return "admin/admin-add";}

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    @PostMapping("/admin/Add")
    @ResponseBody
    public ResultDO Add(Admin admin){
        return adminService.add(admin);
    }

    /**
     * 删除
     * @param str
     * @return
     */
    @PostMapping("/admin/DelAll")
    @ResponseBody
    public ResultDO DelAll(@RequestParam(name = "str[]") String [] str){
        return adminService.delall(str);
    }

    /**
     * 改变状态
     * @param admin
     * @return
     */
    @PostMapping("/admin/Change")
    @ResponseBody
    public ResultDO ChangeStatus(Admin admin){
        return adminService.update(admin);
    }

    /**
     * 打开管理员修改页
     * @return
     */
    @GetMapping("/admin/adminEdit")
    public String adminEdit(){return "admin/admin-edit";}

    /**
     * 修改用户信息
     * @param admin
     * @return
     */
    @PostMapping("/admin/Edit")
    @ResponseBody
    public ResultDO Edit(Admin admin){
        return adminService.update(admin);
    }

}
