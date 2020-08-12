package com.gxa.child;

import com.gxa.child.dao.AdminMapper;
import com.gxa.child.pojo.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
class ChildApplicationTests {

    /**
     * 注入mapper对象
     */
    @Autowired
    private AdminMapper adm;
    @Test
    void testFindAll() {
        List<Admin> all = adm.findAll();
        for (Admin admin : all) {
            System.out.println(admin);
        }
    }

    /**
     * 增
     * @Param admin
     */
    @Test
    void testSave(){
        // 1. 准备admin对象
        Admin admin = new Admin();
        admin.setAdminName("jack2");
        admin.setAdminPwd("123123");
        admin.setAdminPhone(15789097890L);
        admin.setCreateTime(new Timestamp(System.currentTimeMillis()));
        // 2. 调用对应的方法即可
        adm.save(admin);
    }

    /**
     * 删
     * @Param id
     */
    @Test
    void testDelete(){
        adm.deleteByPk(3L);
    }

    /**
     * 查（静态sql）
     * @Param name
     */
    @Test
    void testFindByName(){
        Admin admin = adm.findByName("usr1");
        System.out.println(admin);
    }

    /**
     * 查（动态sql）
     * @Param name
     */
//    @Test
//    void testSearch(){
//        // 1. 准备对象
//        Admin admin = new Admin();
//        admin.setAdminName("usr2");
//        //admin.setAdminPhone(6543234L);
//
//        // 查询
//        List<Admin> search = adm.search(admin);
//        System.out.println(search);
//    }

    /**
     * 改
     * @Param admin
     */
    @Test
    void testUpdate(){
        // 1. 准备对象
        //必须设置id
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setAdminName("admin");
        //admin.setAdminPwd("123456");

        // 2.调用方法
        adm.update(admin);
    }


}
