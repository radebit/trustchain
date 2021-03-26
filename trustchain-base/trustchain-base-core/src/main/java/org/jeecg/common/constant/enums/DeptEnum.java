package org.jeecg.common.constant.enums;

/**
 * @Author Rade
 * @Date 2021/3/26 14:19:19
 * @Description
 */
public enum DeptEnum {
    ADMIN("管理员", "0d0c6ea1e1aa44efb00cf4637f8c8740"),
    STUDENT("学生用户", "da1d1e176b7740ffbe8409f260313ace"),
    TEST("测试用户", "ccecddcbe1ce4e4c95eb0df1e8f0123d");

    private String DeptName;
    private String DeptId;

    DeptEnum(String deptName, String deptId) {
        DeptName = deptName;
        DeptId = deptId;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String deptName) {
        DeptName = deptName;
    }

    public String getDeptId() {
        return DeptId;
    }

    public void setDeptId(String deptId) {
        DeptId = deptId;
    }
}
