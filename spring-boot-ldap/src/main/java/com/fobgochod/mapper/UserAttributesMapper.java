package com.fobgochod.mapper;

import com.fobgochod.domain.UserVO;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.stereotype.Component;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

/**
 * 功能描述
 *
 * @author seven
 * @date 2019/5/19
 */
@Component
public class UserAttributesMapper implements AttributesMapper<UserVO> {

    @Override
    public UserVO mapFromAttributes(Attributes attrs) throws NamingException {

        String userId = null == attrs.get("sAMAccountName") ? null : attrs.get("sAMAccountName").get().toString();
        String userName = null == attrs.get("name") ? null : attrs.get("name").get().toString();
        String password = null == attrs.get("userPassword") ? null : attrs.get("userPassword").get().toString();
        String telephone = null == attrs.get("mobile") ? null : attrs.get("mobile").get().toString();
        String email = null == attrs.get("mail") ? null : attrs.get("mail").get().toString();

        UserVO user = new UserVO();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setPassword(password);
        user.setTelephone(telephone);
        user.setEmail(email);
        return user;
    }

}