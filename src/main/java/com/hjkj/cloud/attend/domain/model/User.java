package com.hjkj.cloud.attend.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.*;

/**
 * 用户实体
 */
@Entity
@Table(name = "user")
@org.hibernate.annotations.Table(appliesTo = "user",comment="用户表")
@AllArgsConstructor
@NamedEntityGraphs({
        @NamedEntityGraph(name = "User.findById",
                attributeNodes = {@NamedAttributeNode("agent"),@NamedAttributeNode(value = "posts", subgraph = "post"),@NamedAttributeNode("terminals")},
                subgraphs = {@NamedSubgraph(name = "post", attributeNodes = {@NamedAttributeNode("department"),@NamedAttributeNode("role"),@NamedAttributeNode(value = "menus")})}),
        @NamedEntityGraph(name = "User.findAll",
                attributeNodes = {@NamedAttributeNode("agent"),@NamedAttributeNode(value = "posts", subgraph = "post"),@NamedAttributeNode("terminals")},
                subgraphs = {@NamedSubgraph(name = "post", attributeNodes = {@NamedAttributeNode(value = "department"),@NamedAttributeNode(value = "role")})
        })
})
public class User extends AbstractEntity {

    //工号
    @Column(nullable = false,columnDefinition = "varchar(50) comment '用户工号'")
    private String cardNum;
    //省份证号
    @Column(length = 50)
    private String IcNo;
    //姓名
    @Column(length = 50)
    private String name;
    //密码
    @Column(length = 50)
    private String password;
    //年龄
    private int age;
    @Column(length = 4)
    //性别
    private String sex;
    @Column(length = 10)
    //用户在终端的角色
    private String terminalRole;
    //入职时间
    private Date hireDate;
    //离职时间
    private Date leaveDate;
    //工作状态
    private int WorkState;
    //出生日期
    private Date bornDate;
    //代理人
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id")
    private User agent;
    //是否为考勤人员
    private int isAttendancer;
    @Lob
    private String avatar;
    @Lob
    private String template;
    private Date regTime;

    @ManyToMany
    @JoinTable(name = "user_terminal",joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "terminal_id"))
    private Set<Terminal> terminals = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "user_post",joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id"))
    private Set<Post> posts = new HashSet<>();

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Duty> duties = new HashSet<>();

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<MonthDuty> monthDuties = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @OneToMany(mappedBy = "user",cascade= CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<UserClasses> userClasses = new HashSet<>();

    @Transient
    private String depart_id;

    @Transient
    private String role_id;

    @Transient
    private String post_id;

    //注册状态
    @Transient
    private int reg_state;

    public User() {
    }

    public boolean isExistsTerminal(String tmId) {
        for (Terminal tm : terminals) {
            if (tm.getId().equals(tmId)) {
                return true;
            }
        }
        return false;
    }

    public void removeTerminalById(String tmId) {
        terminals.iterator().forEachRemaining(tm -> {
            if (tm.getId().equals(tmId)) {
                terminals.remove(tm);
            }
        });
    }

    public synchronized boolean addTerminal(Terminal terminal) {
        if (!isExistsTerminal(terminal.getId())) {
            terminals.add(terminal);
            return true;
        }
        return false;
    }

    public synchronized boolean removeTerminal(String tmId) {
        if (isExistsTerminal(tmId)) {
            removeTerminalById(tmId);
            return true;
        }
        return false;
    }


    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }


    public String getTerminalRole() {
        return terminalRole;
    }

    public void setTerminalRole(String terminalRole) {
        this.terminalRole = terminalRole;
    }

    public String getIcNo() {
        return IcNo;
    }

    public void setIcNo(String icNo) {
        IcNo = icNo;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public int getWorkState() {
        return WorkState;
    }

    public void setWorkState(int workState) {
        WorkState = workState;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public User getAgent() {
        return agent;
    }

    @JsonBackReference
    public void setAgent(User agent) {
        this.agent = agent;
    }

    public int getIsAttendancer() {
        return isAttendancer;
    }

    public void setIsAttendancer(int isAttendancer) {
        this.isAttendancer = isAttendancer;
    }

    public Set<Terminal> getTerminals() {
        return terminals;
    }

    @JsonBackReference
    public void setTerminals(Set<Terminal> terminals) {
        this.terminals = terminals;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    @JsonBackReference
    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Duty> getDuties() {
        return duties;
    }

    public void setDuties(Set<Duty> duties) {
        this.duties = duties;
    }

    public String getDepart_id() {
        return depart_id;
    }

    public void setDepart_id(String depart_id) {
        this.depart_id = depart_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public int getReg_state() {
        return reg_state;
    }

    public void setReg_state(int reg_state) {
        this.reg_state = reg_state;
    }

    public Set<MonthDuty> getMonthDuties() {
        return monthDuties;
    }

    public void setMonthDuties(Set<MonthDuty> monthDuties) {
        this.monthDuties = monthDuties;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    @JsonBackReference
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Set<UserClasses> getUserClasses() {
        return userClasses;
    }

    public void setUserClasses(Set<UserClasses> userClasses) {
        this.userClasses = userClasses;
    }
}
