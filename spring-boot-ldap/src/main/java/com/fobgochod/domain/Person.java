package com.fobgochod.domain;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Entry(objectClasses = {"user", "organizationalPerson", "person", "top"})
public class Person {

    @Id
    private Name dn;

    @Attribute(name = "mobile")
    private String phone;

    public Name getDn() {
        return dn;
    }

    public void setDn(Name dn) {
        this.dn = dn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("Customer[dn=%s, phone='%s']", this.dn, this.phone);
    }

}