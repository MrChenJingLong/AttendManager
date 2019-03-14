package com.hjkj.cloud.attend.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 岗位实体
 */
@Entity
@AllArgsConstructor
@NamedEntityGraphs({
        @NamedEntityGraph(name = "Post.findAll", attributeNodes = {@NamedAttributeNode("department"),@NamedAttributeNode("role"),@NamedAttributeNode("menus")}),
        @NamedEntityGraph(name = "Post.findUsers", attributeNodes = {@NamedAttributeNode("users")}),
        @NamedEntityGraph(name = "Post.findById", attributeNodes = {@NamedAttributeNode("department"),@NamedAttributeNode("role"),@NamedAttributeNode("menus"),@NamedAttributeNode("users")})
})
public class Post extends AbstractEntity{

    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String tag;
    private int sort;
    private int value;
    private int flag;

    @ManyToMany(mappedBy = "posts",fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false,fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false,fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany(mappedBy = "posts")
    private Set<Menu> menus = new HashSet<>();

    public Post() {
    }

    public Post(String id) {
        setId(id);
    }


    public void relateDepartId(String departId) {
        Department department = new Department();
        department.setId(departId);
        this.department = department;
    }

    public void relateRoleId(String roleId) {
        Role role = new Role();
        role.setId(roleId);
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Department getDepartment() {
        return department;
    }

    @JsonBackReference
    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Role getRole() {
        return role;
    }

    @JsonBackReference
    public void setRole(Role role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

}
