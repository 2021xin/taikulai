package com.learn.health.entity;


/**
 * @Data 2022/11/4
 * @Time 21:34
 * @Author Yan Taixin
 */
public class Role {
    private Long id;
    private String name;
    private String nameZh;

    public Role() {
    }

    public Role(Long id, String name, String nameZh) {
        this.id = id;
        this.name = name;
        this.nameZh = nameZh;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameZh='" + nameZh +
                '}';
    }
}
