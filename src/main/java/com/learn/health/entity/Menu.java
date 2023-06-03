package com.learn.health.entity;

import java.util.List;

/**
 * @Data 2022/12/20
 * @Time 17:07
 * @Author Yan Taixin
 */
public class Menu {
    private Long id;
    private String path;
    private String title;
    private String icon;
    private String linkUrl;
    private Long parentId;
    private List<Menu> children;

    public Menu() {
    }

    public Menu(Long id, String path, String title, String icon, String linkUrl, Long parentId, List<Menu> children) {
        this.id = id;
        this.path = path;
        this.title = title;
        this.icon = icon;
        this.linkUrl = linkUrl;
        this.parentId = parentId;
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", parentId=" + parentId +
                ", children=" + children +
                '}';
    }
}
