package com.fobgochod.repository;

import com.fobgochod.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 功能描述
 *
 * @author seven
 * @date 2019/5/19
 */
public interface UserRepository extends JpaRepository<User, Long> {

}