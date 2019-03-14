package com.hjkj.cloud.attend.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 角色实体
 */
@Entity
@AllArgsConstructor
@NamedEntityGraphs({
        @NamedEntityGraph(name = "Role.findAll", attributeNodes = {@NamedAttributeNode("posts")}),
        @NamedEntityGraph(name = "Role.findRoleByIdIn", attributeNodes = {@NamedAttributeNode("posts")}),
        @NamedEntityGraph(name = "Role.findById",
                attributeNodes = {@NamedAttributeNode(value = "posts", subgraph = "department")},
                subgraphs = {@NamedSubgraph(name = "department", attributeNodes = {@NamedAttributeNode(value = "department")})
                })
})
public class Role extends AbstractEntity {

    @Column(length = 50)
    private String name;
    private int sort;
    private int value;
    @OneToMany(mappedBy = "role",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Post> posts = new HashSet<>();

    public Role() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    @JsonBackReference
    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
