package com.gxa.child.controller;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

//@RestController
public class IndexController {
/*
 *测试
 * @return
 */
    @GetMapping("/index")
    public String index(){
        return  "hello";
    }

    /**
     * Request
     * @return
     */
    @RequestMapping(path = "/index2",method = RequestMethod.GET)
    public String index2(){
        return "request";
    }

    /**
     * post
     * @return
     */
    @PostMapping("/index3")
    public String index3(@RequestParam(name = "myId",defaultValue = "9") Integer id){
        return "post-"+id;
    }

    /**
     * 路径传参
     * @return
     */
    @PostMapping("/index4/{id}")
    public String index4(@PathVariable("id") Integer id){
        return "path "+id;
    }

    /**
     * 表单请求
     * @return
     */
    @PostMapping("/index5")
    public User index5(User user){
        return user;
    }

    /**
     * json
     * @return
     */
    @PostMapping("/index6")
    public User index6(@RequestBody User user){
        return user;
    }

}
