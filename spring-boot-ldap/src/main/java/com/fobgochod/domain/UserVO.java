package com.fobgochod.domain;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

/**
 * @auther: Xiao
 * @date: 2019/5/19 15:52
 * @description: 功能描述
 */
@Entry(objectClasses = {"user", "organizationalPerson", "person", "top"})
public class UserVO {

    @Id
    private Name dn;
    @Attribute(name = "sAMAccountName")
    private String userId;
    @Attribute(name = "name")
    private String userName;
    @Attribute(name = "userPassword")
    private String password;
    @Attribute(name = "mobile")
    private String telephone;
    @Attribute(name = "mail")
    private String email;

    public Name getDn() {
        return dn;
    }

    public void setDn(Name dn) {
        this.dn = dn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
