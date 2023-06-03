package com.learn.health.entity;

import java.util.List;

/**
 * @Data 2022/11/5
 * @Time 20:24
 * @Author Yan Taixin
 */
public class Permission {
    private Long id;
    private String pattern;
    private List<Role> roles;

    public Permission() {
    }

    public Permission(Long id, String pattern, List<Role> roles) {
        this.id = id;
        this.pattern = pattern;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", pattern='" + pattern + '\'' +
                ", roles=" + roles +
                '}';
    }
}
