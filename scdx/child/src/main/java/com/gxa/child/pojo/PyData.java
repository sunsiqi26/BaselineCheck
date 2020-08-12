package com.gxa.child.pojo;

/**
 * 接收python客户端数据
 */
public class PyData {
    private Integer typeCode;//操作类型
    private String data;//提交的数据
    private String boardId;//主板ID

    public PyData() {
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    @Override
    public String toString() {
        return "PyData{" +
                "typeCode=" + typeCode +
                ", data='" + data + '\'' +
                ", boardId='" + boardId + '\'' +
                '}';
    }
}
