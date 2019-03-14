package com.hjkj.cloud.attend.ui.dto.terminal;

import com.hjkj.cloud.attend.ui.dto.web.BasePage;
import hjkj.springframework.boot.doc.annotation.JLApiParam;
import hjkj.springframework.boot.doc.annotation.PropType;

import java.io.Serializable;

public class TerminalDto extends BasePage implements Serializable {
    private static final long serialVersionUID = -6076143179545655159L;

    private String id;
    private String name;
    private int status;
    @JLApiParam(name = "ip",value = "IP地址",desc = "IP地址")
    private String ip;
    @JLApiParam(name = "port",desc = "端口号",type = PropType.INTEGER)
    private int port;
    @JLApiParam(name = "mac_addr",value = "mac地址",desc = "mac地址")
    private String mac_addr;
    @JLApiParam(name = "reg_time",value = "注册时间",desc = "注册时间")
    private String reg_time;
    @JLApiParam(name = "allow_user_num",desc = "终端许可用户人数",type = PropType.INTEGER)
    private int allow_user_num;
    @JLApiParam(name = "reg_user_num",desc = "终端注册用户个数",type = PropType.INTEGER)
    private int reg_user_num;
    @JLApiParam(name = "comp_records",desc = "识别记录个数",type = PropType.INTEGER)
    private int comp_records;
    @JLApiParam(name = "stra_records",desc = "陌生人记录个数",type = PropType.INTEGER)
    private int stra_records;
    @JLApiParam(name = "total_cap",desc = "总的容量",type = PropType.INTEGER)
    private int total_cap;
    @JLApiParam(name = "usag_cap",desc = "已使用容量",type = PropType.INTEGER)
    private int usag_cap;
    @JLApiParam(name = "version",value = "软件版本",desc = "软件版本")
    private String version;
    @JLApiParam(name = "depart_id",value = "部门ID",desc = "部门ID")
    private String depart_id;
    private String depart_name;

    private boolean isAutoTrans;
    private boolean isAutoAccept;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getReg_time() {
        return reg_time;
    }

    public void setReg_time(String reg_time) {
        this.reg_time = reg_time;
    }

    public int getAllow_user_num() {
        return allow_user_num;
    }

    public void setAllow_user_num(int allow_user_num) {
        this.allow_user_num = allow_user_num;
    }

    public int getReg_user_num() {
        return reg_user_num;
    }

    public void setReg_user_num(int reg_user_num) {
        this.reg_user_num = reg_user_num;
    }

    public int getComp_records() {
        return comp_records;
    }

    public void setComp_records(int comp_records) {
        this.comp_records = comp_records;
    }

    public int getStra_records() {
        return stra_records;
    }

    public void setStra_records(int stra_records) {
        this.stra_records = stra_records;
    }

    public int getTotal_cap() {
        return total_cap;
    }

    public void setTotal_cap(int total_cap) {
        this.total_cap = total_cap;
    }

    public int getUsag_cap() {
        return usag_cap;
    }

    public void setUsag_cap(int usag_cap) {
        this.usag_cap = usag_cap;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMac_addr() {
        return mac_addr;
    }

    public void setMac_addr(String mac_addr) {
        this.mac_addr = mac_addr;
    }

    public String getDepart_id() {
        return depart_id;
    }

    public void setDepart_id(String depart_id) {
        this.depart_id = depart_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isAutoTrans() {
        return isAutoTrans;
    }

    public void setAutoTrans(boolean autoTrans) {
        isAutoTrans = autoTrans;
    }

    public boolean isAutoAccept() {
        return isAutoAccept;
    }

    public void setAutoAccept(boolean autoAccept) {
        isAutoAccept = autoAccept;
    }

    public String getDepart_name() {
        return depart_name;
    }

    public void setDepart_name(String depart_name) {
        this.depart_name = depart_name;
    }
}
