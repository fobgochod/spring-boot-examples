package com.fobgochod.service;


import com.fobgochod.entity.User;

import java.util.List;

public interface UserService {

    public List<User> getUserList();

    public User findUserById(long sid);

    public void save(User user);

    public void edit(User user);

    public void delete(long sid);


}
