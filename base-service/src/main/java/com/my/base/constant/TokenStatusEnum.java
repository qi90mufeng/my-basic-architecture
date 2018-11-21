package com.my.base.constant;

/**
 * token状态枚举
 */
public enum TokenStatusEnum {

    ACTIVE(1, "正常"),

    REMOVED_BY_ANOTHER(2, "账号在另一个设备上登陆"),

    ;

    private int    index;
    private String code;
    private String name;

    private TokenStatusEnum(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
