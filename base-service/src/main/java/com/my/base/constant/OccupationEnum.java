package com.my.base.constant;

/**
 * 职业枚举
 */
public enum OccupationEnum {

    WORKER(1, "工人（包括生产、加工、建筑和设备等）"),

    SECURITY(2, "保安/治安/防损"),

    SALE(3, "销售/中介/业务代表/促销 "),

    BUSINESS(4, "商业/服务业人员"),

    INDIVIDUAL(5, "个体"),

    BASICLEVELMANAGEMENTSTAFF(6, "基层管理人员/主管组长/科员 "),

    GENERALSTAFF(7, "一般员工"),

    TEACHER(8, "老师/专业（技术）人员"),

    MIDDLEMANAGER(9, "中层管理人员/经理以上/科级以上干部"),

    STUDENT(10, "学生"),

    SOLDIER(11, "军人"),

    OTHER(12, "其他"),

    monk(13, "和尚"),

    taoist(14, "道士"),

    nun(15, "尼姑");

    private String name;

    private int code;

    private OccupationEnum(int code, String name) {
        this.name = name;
        this.code = code;
    }

    public static String getName(int code) {
        for (OccupationEnum occupationEnum : OccupationEnum.values()) {
            if (occupationEnum.getCode() == code) {
                return occupationEnum.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
