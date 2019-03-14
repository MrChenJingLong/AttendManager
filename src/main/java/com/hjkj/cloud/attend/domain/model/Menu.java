package com.hjkj.cloud.attend.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hjkj.cloud.attend.infrastructure.utils.StringUtils;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 菜单实体
 */
@Entity
@AllArgsConstructor
public class Menu extends AbstractEntity{

    @Column(length = 50)
    private String name;
    @Column(length = 100)
    private String url;
    @Column(length = 100)
    private String ref;
    @Column(length = 100)
    private String icon;
    private int sort;
    private int userLimit;
    private int type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Menu parentMenu;

//    @OneToMany(mappedBy = "parentMenu", fetch = FetchType.LAZY)
//    private Set<Menu> children = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "menu_post",joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id"))
    private Set<Post> posts= new HashSet<>();

    @Transient
    private boolean hidden;

    public Menu() {
    }

    public Menu(String name, String url, String icon, int sort) {
        this.setId(StringUtils.genUUID(8));
        this.name = name;
        this.url = url;
        this.icon = icon;
        this.sort = sort;
    }

    public Menu(String name, String url, String icon, int sort,String parentId) {
        this.setId(StringUtils.genUUID(8));
        this.name = name;
        this.url = url;
        this.icon = icon;
        this.sort = sort;
        this.relateParentId(parentId);
    }

    public void relateParentId(String parentId) {
        Menu menu = new Menu();
        menu.setId(parentId);
        this.parentMenu = menu;
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

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getUserLimit() {
        return userLimit;
    }

    public void setUserLimit(int userLimit) {
        this.userLimit = userLimit;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Menu getParentMenu() {
        return parentMenu;
    }

    @JsonBackReference
    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    @JsonBackReference
    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
