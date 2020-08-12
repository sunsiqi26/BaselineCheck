package com.gxa.child.service;

import com.gxa.child.dto.ResultDO;
import com.gxa.child.exception.DataException;
import com.gxa.child.pojo.Admin;
import com.gxa.child.pojo.ConditionVo;

import javax.servlet.http.HttpSession;

public interface AdminService {

    /**
     * 登录判断
     * @param ad
     * @param session
     * @return
     */
    ResultDO login(Admin ad, HttpSession session) throws DataException;

    /**
     * 管理员列表
     * 查询检索二合一
     * @param conditionVo
     * @return
     */
    ResultDO list(ConditionVo conditionVo);

    /**
     * 向数据库添加数据
     * @param admin
     * @return
     */
    ResultDO add(Admin admin);



    /**
     * 批量删除
     * @param str
     * @return
     */
    ResultDO delall(String[] str);



    /**
     * 修改管理员信息
     * @param admin
     * @return
     */
    ResultDO update(Admin admin);


}
