package com.example.demo.domain;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Proxy;

import com.example.demo.mapper.UserType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@ToString
@Table(
        name = "NT_USER",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"C_LOGIN_NAME"}),
                @UniqueConstraint(columnNames = {"C_LOGIN_NAME2"})})
@Proxy(lazy = false)
public class User implements Serializable {

    /**
     * 主键.
     */
    @Id
    @Column(length = 64)
    private String id;

    /**
     * 登录系统的名称.
     */
    @Column(name = "C_LOGIN_NAME", length = 64)
    private String loginName;

    @Column(name = "C_LOGIN_NAME2", length = 64)
    private String loginName2;

    /**
     * 扫描二维码得到的信息
     */
    @Column(name = "C_QR_CODE", length = 128)
    private String qrCode;

    /**
     * 扫描二维码得到的信息
     */
    @Column(name = "C_QR_CODE_INDEX")
    private int qrCodeIndex = 0;
    /**
     * 姓名.
     */
    @Column
    private String name;

    /**
     * 性别，0:未知，1：男，2：女
     */
    @Column
    private int sex;

    @Column
    private int age;

    @Column
    private String mobile;

    @Column
    private String password0;

    @Column
    private String password1;

    @Column
    private Date createdDate = new Date();

    @Column
    private Date birthday = new Date();

    @Enumerated(EnumType.STRING)
    @Column
    private UserType type = UserType.DOCTOR;
    // @Column
    // private String type = UserType.DOCTOR.name();

    /**
     * 头像地址
     */
    @Column
    private String headImageUrl;

    /**
     * 状态,0:禁用，不可登录，1：启用，可登录系统
     */
    @Column
    private int status;

    /**
     * 区域编码
     */
    @Column
    private String areaCode;
    /**
     * 昵称首字母
     */
    @Column
    private String firstSpell;

    @Column
    private String source;

    /**
     * 系统标识ID
     */
    @Column
    private String systemId;
    /**
     * 小诊所ID
     */
    @Column
    private String clinicId;
    /**
     * 用户角色
     */
    @Column
    private String role;

}
