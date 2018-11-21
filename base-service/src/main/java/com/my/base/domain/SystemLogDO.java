package com.my.base.domain;

import java.text.ParseException;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.validator.constraints.Length;

/**SystemLog数据模型
 */
@Table(name = "t_system_log")
public class SystemLogDO extends BaseDO {
    private static final long  serialVersionUID  = 1L;

    //date formats
    public static final String FORMAT_GMT_CREATE = DATE_TIME_FORMAT;

    /** 主键ID*/
    @Id
    private java.lang.Long     id;
    /** 操作人姓名*/
    @Length(max = 20)
    private java.lang.String   operatorName;
    /** 操作参数*/
    @Length(max = 200)
    private java.lang.String   parameters;
    /** 请求类型*/
    @Length(max = 10)
    private java.lang.String   method;
    /** 操作url*/
    @Length(max = 200)
    private java.lang.String   url;
    /** ip地址*/
    @Length(max = 20)
    private java.lang.String   ip;
    /** 操作action名称*/
    @Length(max = 100)
    private java.lang.String   organize;
    /** 访问模块*/
    @Length(max = 100)
    private java.lang.String   accessModule;
    /** 创建时间*/
    @NotNull
    private java.util.Date     gmtCreate;

    //columns END

    public SystemLogDO() {
    }

    public SystemLogDO(java.lang.Long id) {
        this.id = id;
    }

    public void setId(java.lang.Long value) {
        this.id = value;
    }

    public java.lang.Long getId() {
        return this.id;
    }

    public void setOperatorName(java.lang.String value) {
        this.operatorName = value;
    }

    public java.lang.String getOperatorName() {
        return this.operatorName;
    }

    public void setParameters(java.lang.String value) {
        this.parameters = value;
    }

    public java.lang.String getParameters() {
        return this.parameters;
    }

    public void setMethod(java.lang.String value) {
        this.method = value;
    }

    public java.lang.String getMethod() {
        return this.method;
    }

    public void setUrl(java.lang.String value) {
        this.url = value;
    }

    public java.lang.String getUrl() {
        return this.url;
    }

    public void setIp(java.lang.String value) {
        this.ip = value;
    }

    public java.lang.String getIp() {
        return this.ip;
    }

    public void setOrganize(java.lang.String value) {
        this.organize = value;
    }

    public java.lang.String getOrganize() {
        return this.organize;
    }

    public void setAccessModule(java.lang.String value) {
        this.accessModule = value;
    }

    public java.lang.String getAccessModule() {
        return this.accessModule;
    }

    public String getGmtCreateString() {
        return getGmtCreate() == null ? null : DateFormatUtils.format(getGmtCreate(),
            FORMAT_GMT_CREATE);
    }

    public void setGmtCreateString(String value) {
        try {
            setGmtCreate(DateUtils.parseDate(value, new String[] { FORMAT_GMT_CREATE,
                    DATE_TIME_FORMAT }));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setGmtCreate(java.util.Date value) {
        this.gmtCreate = value;
    }

    public java.util.Date getGmtCreate() {
        return this.gmtCreate;
    }

    @Override
    public String toString() {
        return "SystemLogDO [id=" + id + ", operatorName=" + operatorName + ", parameters="
               + parameters + ", method=" + method + ", url=" + url + ", ip=" + ip + ", organize="
               + organize + ", accessModule=" + accessModule + ", gmtCreate=" + gmtCreate + "]";
    }

}
