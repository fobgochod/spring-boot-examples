package com.fobgochod.service;

import com.fobgochod.domain.UserVO;

import java.util.List;

/**
 * 功能描述
 *
 * @author seven
 * @date 2019/5/20
 */
public interface LdapService {

    boolean authenticate(String userId, String password);

    UserVO getUser(String userId);

    List<UserVO> getUsers();
}
