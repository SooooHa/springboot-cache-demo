package cn.cache.entity;

import java.io.Serializable;

/**
 * (Department)实体类
 *
 * @author makejava
 * @since 2020-02-12 21:10:52
 */
public class Department implements Serializable {
    private static final long serialVersionUID = -53186398179341286L;
    
    private Integer id;
    
    private String departmentname;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

}