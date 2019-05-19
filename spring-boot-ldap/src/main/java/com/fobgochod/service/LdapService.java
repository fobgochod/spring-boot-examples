package com.fobgochod.service;

import com.fobgochod.domain.UserVO;

import java.util.List;

/**
 * @auther: Xiao
 * @date: 2019/5/19 13:08
 * @description: 功能描述
 */
public interface LdapService {

    boolean authenticate(String userId, String password);

    UserVO getUser(String userId);

    List<UserVO> getUsers();
}
