package com.my.base.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
 * 该bean用于存储token信息到redis或者从redis读取token
 */
public class TokenDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * 具体token值
     */
    private String            token;

    private String            id;

    private String            userType;

    //姓名
    private String            name;

    //部门名称
    private String            organizeName;

    private List<String>      roleIdList;

    private Date              lastUseTime;

    private Integer           status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganizeName() {
        return organizeName;
    }

    public void setOrganizeName(String organizeName) {
        this.organizeName = organizeName;
    }

    public List<String> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public Date getLastUseTime() {
        return lastUseTime;
    }

    public void setLastUseTime(Date lastUseTime) {
        this.lastUseTime = lastUseTime;
    }

    @Override
    public String toString() {
        return "TokenDTO [token=" + token + ", id=" + id + ", userType=" + userType + ", name="
               + name + ", organizeName=" + organizeName + ", roleIdList=" + roleIdList
               + ", lastUseTime=" + lastUseTime + ", status=" + status + "]";
    }

}
