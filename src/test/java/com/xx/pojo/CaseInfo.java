package com.xx.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @author xuxiong
 * @date 2020/6/15 - 11:19
 * JAVA自动化学习
 **/
public class CaseInfo {

    //CaseId(用例编号)	Name(接口名)	Url(接口地址)	Type(接口提交类型)
    // Desc(用例描述)	Params(参数)	Content-Type

    //和测试用例上面第一行一一对应，映射关系
    @Excel(name  = "CaseId(用例编号)")
    private int id;
    @Excel(name  = "Name(接口名)")
    private String name;
    @Excel(name  = "Url(接口地址)")
    private String url;
    @Excel(name  = "Type(接口提交类型)")
    private String type;
    @Excel(name  = "Desc(用例描述)")
    private String desc;
    @Excel(name  = "Params(参数)")
    private String params;
    @Excel(name  = "Content-Type")
    private String contentType;
    @Excel(name = "期望值")
    private String except;

    //必须要有空参有参构造和get/set方法

    public CaseInfo() {
    }

    public CaseInfo(int id, String name, String url, String type, String desc, String params, String contentType, String except) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.type = type;
        this.desc = desc;
        this.params = params;
        this.contentType = contentType;
        this.except = except;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getExcept() {
        return except;
    }

    public void setExcept(String except) {
        this.except = except;
    }

    @Override
    public String toString() {
        return "CaseInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", desc='" + desc + '\'' +
                ", params='" + params + '\'' +
                ", contentType='" + contentType + '\'' +
                ", except='" + except + '\'' +
                '}';
    }
}
