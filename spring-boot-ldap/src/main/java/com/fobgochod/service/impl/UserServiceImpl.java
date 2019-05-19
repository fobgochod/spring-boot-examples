package com.fobgochod.service.impl;

import com.fobgochod.entity.User;
import com.fobgochod.repository.UserRepository;
import com.fobgochod.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 功能描述
 *
 * @author seven
 * @date 2019/5/19
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(long sid) {
        Optional<User> optional = userRepository.findById(sid);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void edit(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(long sid) {
        userRepository.deleteById(sid);
    }
}


