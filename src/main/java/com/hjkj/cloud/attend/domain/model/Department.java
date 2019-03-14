package com.hjkj.cloud.attend.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 部门实体
 */

@Entity
@AllArgsConstructor
@NamedEntityGraphs({
        @NamedEntityGraph(name = "Department.findById", attributeNodes = {@NamedAttributeNode("posts"),@NamedAttributeNode("terminals")}),
        @NamedEntityGraph(name = "Department.findDepartmentByLevel", attributeNodes = {@NamedAttributeNode("posts"),@NamedAttributeNode("terminals")})
})
public class Department extends AbstractEntity{


    @Column(length = 50)
    private String name;
    @Column(length = 100)
    private String tag;
    private int level;
    private int is_private;
    private int sort;
    private int flag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Department parentDepartment;

    @Transient
    private List<Department> children = new ArrayList<>();

    @OneToMany(mappedBy = "department",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Post> posts= new HashSet<>();

    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY)
    private Set<Terminal> terminals = new HashSet<>();

    public Department() {
    }

    public Department(String id) {
        setId(id);
    }

    public void relateParentId(String parentId) {
        Department department = new Department();
        department.setId(parentId);
        this.parentDepartment = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getIs_private() {
        return is_private;
    }

    public void setIs_private(int is_private) {
        this.is_private = is_private;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Terminal> getTerminals() {
        return terminals;
    }

    public void setTerminals(Set<Terminal> terminals) {
        this.terminals = terminals;
    }

    public Department getParentDepartment() {
        return parentDepartment;
    }

    @JsonBackReference
    public void setParentDepartment(Department parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

    public List<Department> getChildren() {
        return children;
    }

    public void setChildren(List<Department> children) {
        this.children = children;
    }
}
