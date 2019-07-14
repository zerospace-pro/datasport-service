package com.zerospace.datasport.model.domain.entity;/**
 Created by 胡杰 on 2019/3/30. */

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * FileName: com.hjcc.ydItem.dataSport.domain.entity.User.java
 * Author: Jeremy_Hu
 * Date: 2019/3/30 下午8:05
 */
@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    private String Uid;

    private String Upassword;
    private String Uavatar;
    private String Unickname;
    private String Urealname;
    private String Usex;
    private String Uaddress;
    private String Uphone;
    private String Uemail;
    private Date    Ubirthday;
}
