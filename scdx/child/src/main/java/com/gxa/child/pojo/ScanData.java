package com.gxa.child.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.io.Serializable;

/**
 * 扫描数据(ScanData)实体类
 *
 * @author makejava
 * @since 2020-06-18 14:02:44
 */
public class ScanData implements Serializable {
    private static final long serialVersionUID = 510728625930565807L;
    
    private Long id;
    /**
    * 主板ID，设备唯一标识
    */
    @NotBlank(message = "主板ID不能为空")
    private String boardId;
    /**
    * 扫描类型
    */
    private Integer typeCode;
    /**
    * 扫描数据
    */
    @NotNull(message = "扫描数据不能为空")
    private Object data;

    /**
     * 扫描数据所属对象
     */
    private Type type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public ScanData() {
    }

    public ScanData(String boardId, Integer typeCode, Object data) {
        this.boardId = boardId;
        this.typeCode = typeCode;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}