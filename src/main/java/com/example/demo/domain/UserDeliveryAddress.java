package com.example.demo.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="NT_USER_DELIVERY_ADDRESS",indexes={@Index(columnList="C_USER_ID")})
public class UserDeliveryAddress {
    @Id
    @Column(length=64)
    private String id;
    
    @Column(name="C_USER_ID")
    private String userId;
    
    /**
     * 城市编码
     */
    private String addressCode;
    
    /**
     * 详细地址
     */
    private String detailAddress;
    
    /**
     * 收件人
     */
    private String receiver;
    
    /**
     * 收件人联系方式。
     */
    private String mobile;
    
    /**
     * 是否是默认收货地址。 1是    0否
     */
    private String defaultAddress;

    /**
     * 更新日期
     */
    private Date updateDate = new Date();
}
