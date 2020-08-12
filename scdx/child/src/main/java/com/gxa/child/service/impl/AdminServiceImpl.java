package com.gxa.child.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxa.child.dao.AdminMapper;
import com.gxa.child.dto.Response;
import com.gxa.child.dto.ResultDO;
import com.gxa.child.exception.DataException;
import com.gxa.child.groups.Add;
import com.gxa.child.groups.Login;
import com.gxa.child.pojo.Admin;
import com.gxa.child.pojo.ConditionVo;
import com.gxa.child.service.AdminService;
import com.gxa.child.utils.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Service
@SuppressWarnings("all")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private DataValidator dataValidator;
    /**
     * 登录实现
     * @param ad
     * @param session
     * @return
     */
    @Override
    public ResultDO login(Admin ad, HttpSession session) throws DataException {
        //数据查验，排除ad为null的情况
        dataValidator.validate(ad, Login.class);

        //1.通过名字查询
        Admin dbAdmin = adminMapper.findByName(ad.getAdminName());
        //2.判断用户是否存在
        if (dbAdmin==null) {
            throw new DataException(1001,"用户不存在！");
        }

        //4.判断密码是否正确
        if(!dbAdmin.getAdminPwd().equals(DigestUtils.md5DigestAsHex(ad.getAdminPwd().getBytes()))){
            throw new DataException(1003,"密码不正确！");
        }

        //3.判断用户是被禁用
        if (dbAdmin.getAdminStatus()==0) {
            throw new DataException(1002,"用户已被禁用！");
        }

        //修改最后登录时间
        dbAdmin.setLastLoginTime(new Timestamp((System.currentTimeMillis())));
        adminMapper.update(dbAdmin);

        //用户名和密码正确，登录成功，数据存入session
        session.setAttribute("admin",dbAdmin);

        return Response.success("登录成功");
    }

    /**
     * 管理员列表
     * @param adminVo ：管理员对象，存放搜索数据
     * @return
     */
    @Override
    public ResultDO list(ConditionVo conditionVo) {
        //分页操作
        PageHelper.startPage(conditionVo.getPage(),conditionVo.getPageSize());
        //扩大条件匹配范围
        if(!StringUtils.isEmpty(conditionVo.getKeywords())){
            conditionVo.setKeywords("%"+conditionVo.getKeywords()+"%");
        }
        //查询数据库
        List<Admin> list = adminMapper.search(conditionVo);
        //封装pageinfo
        PageInfo<Admin> pageInfo = new PageInfo<>(list);
        return Response.success("获取数据成功",pageInfo);
    }

    /**
     * 添加用户
     * @param admin
     * @return
     */
    @Override
    public ResultDO add(Admin admin) {
        //数据校验
        dataValidator.validate(admin, Add.class);
        //查看用户名是否已存在
        Admin dbadmin = adminMapper.findByName(admin.getAdminName());
        if (dbadmin !=null) {
            //该用户已存在
            throw new DataException(3002,"该用户已存在!");
        }
        //散列密码
        admin.setAdminPwd(DigestUtils.md5DigestAsHex(admin.getAdminPwd().getBytes()));
        //添加数据（数据库自动添加创建时间）
        adminMapper.save(admin);
        return Response.success("添加用户成功");
    }

    /**
     * 批量删除
     * @param str
     * @return
     */
    @Override
    public ResultDO delall(String[] str) {
        adminMapper.deleteAll(str);
        return Response.success("批量删除成功");
    }

    /**
     *修改信息
     * @param admin
     * @return
     */
    @Override
    public ResultDO update(Admin admin) {

        //查看是否需要防止非本人操作
        Long loginId = admin.getLoginId();
        if (loginId != null && loginId != admin.getId()){
            throw new DataException(3005,"非本人操作！");
        }
        System.out.print(loginId);
        System.out.print(admin.getId());
        //查看是否新设置密码
        String pwd = admin.getAdminPwd();
        if (pwd != null){
            admin.setAdminPwd(DigestUtils.md5DigestAsHex(pwd.getBytes()));
        }
        adminMapper.update(admin);
        return Response.success("修改成功");
    }


}
