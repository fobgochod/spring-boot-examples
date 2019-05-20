package com.fobgochod.service;

import com.fobgochod.entity.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User findBySid(long sid);

    void save(User user);

    void update(User user);

    void delete(long sid);


}
