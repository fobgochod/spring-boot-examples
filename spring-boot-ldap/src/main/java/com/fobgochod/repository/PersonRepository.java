package com.fobgochod.repository;

import com.fobgochod.domain.Person;
import org.springframework.data.ldap.repository.LdapRepository;

/**
 * 功能描述
 *
 * @author seven
 * @date 2019/5/19
 */
public interface PersonRepository extends LdapRepository<Person> {

	Person findByPhone(String phone);

}