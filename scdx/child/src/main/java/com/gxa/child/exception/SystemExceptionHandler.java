package com.gxa.child.exception;

import com.gxa.child.dto.Response;
import com.gxa.child.dto.ResultDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class SystemExceptionHandler{

    //日志记录器
    private Logger logger = LoggerFactory.getLogger(SystemExceptionHandler.class);

    /**
     * 捕获数据校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(DataException.class)
    public ResultDO DataExceptionHandler(DataException e){
        logger.info("数据校验异常状态码={}",e.getCode());
        logger.info("数据校验异常状态信息={}",e.getMsg());
        return Response.error(e.getCode(),e.getMsg());
    }
}
