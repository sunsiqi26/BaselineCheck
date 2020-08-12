package com.gxa.child.pojo;

import java.io.Serializable;

/**
 * (Type)实体类
 *
 * @author makejava
 * @since 2020-06-18 14:03:05
 */
public class Type implements Serializable {
    private static final long serialVersionUID = -20168108796340915L;
    
    private Long id;
    /**
    * 类型码
    */
    private Integer typeCode;
    /**
    * 类型名
    */
    private String typeName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}