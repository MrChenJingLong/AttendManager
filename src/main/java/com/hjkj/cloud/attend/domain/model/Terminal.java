package com.hjkj.cloud.attend.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 终端实体
 */
@Entity
@AllArgsConstructor
@NamedEntityGraphs({
        @NamedEntityGraph(name = "Terminal.findAll", attributeNodes = {@NamedAttributeNode("department")}),
        @NamedEntityGraph(name = "Terminal.findByUsers", attributeNodes = {@NamedAttributeNode("users")})
})
public class Terminal extends AbstractEntity {

    //终端mac地址
    @Column(length = 64)
    private String macAddr;
    //终端IP
    @Column(name = "ip",length = 15)
    private String tmIp;
    //终端端口
    @Column(name = "port",length = 5)
    private int tmPort;
    //终端名称
    @Column(name = "name",length = 64)
    private String name;
    //终端密码
    @Column(name = "password",length = 36)
    private String password;
    //终端注册时间
    @Column(name = "register_time")
    private Date tmRegTime;
    //终端许可用户人数
    @Column(name = "limited_num")
    private int tmAllowNum;
    //终端注册用户个数
    @Column(name = "registered_num")
    private int tmRegnum;
    //识别记录个数
    @Column(name = "identify_num")
    private int tmCompRecords;
    //陌生人记录个数
    @Column(name = "stranger_num")
    private int tmStrangerRecords;
    //总的容量
    @Column(name = "storage_space")
    private int tmTotalCap;
    //已使用容量
    @Column(name = "storage_used")
    private int tmUsableCap;
    //软件版本
    @Column(length = 20)
    private String version;
    //是否接受其他终端转发的信息
    private int acceptState;
    //是否自动转发信息
    private int transmitState;
    //在线情况
    private int onlineState;

    @OneToMany(mappedBy = "terminal",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Duty> duties = new HashSet<>();

    @ManyToMany(mappedBy = "terminals")
    private Set<User> users = new HashSet<>();

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false,fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public Terminal() {
    }

    public void relateDepartId(String departId) {
        Department department = new Department();
        department.setId(departId);
        this.department = department;
    }



    //检测ip的有效性
    public void checkValidFromIp() {

    }

    //检测mac地址的有效性
    public void checkValidFromMacAddr() {
    }


    //增加注册人数
    public synchronized void incRegister() {
        this.tmRegnum += 1;
    }

    //减少注册人数
    public synchronized void disRegister() {
        this.tmRegnum -= 1;
    }

    //增加识别记录
    public synchronized void incCompRecords() {
        this.tmCompRecords++;
    }

    //增加陌生人个数
    public synchronized void incStranger() {
        this.tmStrangerRecords++;
    }

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    public String getTmIp() {
        return tmIp;
    }

    public void setTmIp(String tmIp) {
        this.tmIp = tmIp;
    }

    public int getTmPort() {
        return tmPort;
    }

    public void setTmPort(int tmPort) {
        this.tmPort = tmPort;
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

    public Date getTmRegTime() {
        return tmRegTime;
    }

    public void setTmRegTime(Date tmRegTime) {
        this.tmRegTime = tmRegTime;
    }

    public int getTmAllowNum() {
        return tmAllowNum;
    }

    public void setTmAllowNum(int tmAllowNum) {
        this.tmAllowNum = tmAllowNum;
    }

    public int getTmRegnum() {
        return tmRegnum;
    }

    public void setTmRegnum(int tmRegnum) {
        this.tmRegnum = tmRegnum;
    }

    public int getTmCompRecords() {
        return tmCompRecords;
    }

    public void setTmCompRecords(int tmCompRecords) {
        this.tmCompRecords = tmCompRecords;
    }

    public int getTmStrangerRecords() {
        return tmStrangerRecords;
    }

    public void setTmStrangerRecords(int tmStrangerRecords) {
        this.tmStrangerRecords = tmStrangerRecords;
    }

    public int getTmTotalCap() {
        return tmTotalCap;
    }

    public void setTmTotalCap(int tmTotalCap) {
        this.tmTotalCap = tmTotalCap;
    }

    public int getTmUsableCap() {
        return tmUsableCap;
    }

    public void setTmUsableCap(int tmUsableCap) {
        this.tmUsableCap = tmUsableCap;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getAcceptState() {
        return acceptState;
    }

    public void setAcceptState(int acceptState) {
        this.acceptState = acceptState;
    }

    public int getTransmitState() {
        return transmitState;
    }

    public void setTransmitState(int transmitState) {
        this.transmitState = transmitState;
    }

    public int getOnlineState() {
        return onlineState;
    }

    public void setOnlineState(int onlineState) {
        this.onlineState = onlineState;
    }

    public Set<Duty> getDuties() {
        return duties;
    }

    public void setDuties(Set<Duty> duties) {
        this.duties = duties;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Department getDepartment() {
        return department;
    }

    @JsonBackReference
    public void setDepartment(Department department) {
        this.department = department;
    }
}
