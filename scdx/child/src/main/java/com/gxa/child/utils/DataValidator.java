package com.gxa.child.utils;

import com.gxa.child.exception.DataException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 数据校验的类
 */
@Component
public class DataValidator {

    //获取验证器
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 无分组
     * @param data
     */
    public void validate(Object data){
        //验证并获取错误信息
        Set<ConstraintViolation<Object>> errors = validator.validate(data);
        if (errors.size()!=0) {
            //抛出异常
            throw new DataException(3001,errors.iterator().next().getMessage());
        }
    }

    /**
     * 有分组
     * @param data
     * @param clazz
     */
    public void validate(Object data,Class<?> clazz){
        //验证并获取错误信息
        Set<ConstraintViolation<Object>> errors = validator.validate(data,clazz);
        if (errors.size()!=0) {
            //抛出异常
            throw new DataException(3001,errors.iterator().next().getMessage());
        }
    }
}
