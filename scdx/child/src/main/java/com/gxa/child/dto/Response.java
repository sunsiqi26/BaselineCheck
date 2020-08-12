package com.gxa.child.dto;

/**
 * 响应数据类
 */
public class Response {

    /**
     * 成功登录的响应
     * @return
     */
    public static ResultDO success(){
        return new ResultDO(200,"登录成功");
    }

    /**
     * 成功响应
     * @Param msg
     * @return
     */
    public static ResultDO success(String msg){ return new ResultDO(200,msg); }

    /**
     * 成功响应（带data）
     * @Param msg
     * @Param data
     * @return
     */
    public static ResultDO success(String msg,Object data){ return new ResultDO(200,msg,data); }

    /**
     * 错误响应
     * @return
     */
    public static ResultDO error(){
        return new ResultDO(400,"处理请求失败！");
    }

    /**
     * 错误响应
     * @Param code
     * @Param msg
     * @return
     */
    public static ResultDO error(Integer code,String msg){
        return new ResultDO(code,msg);
    }
}
