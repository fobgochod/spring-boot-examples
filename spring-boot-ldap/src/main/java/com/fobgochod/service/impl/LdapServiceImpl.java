package com.fobgochod.service.impl;

import com.fobgochod.domain.UserVO;
import com.fobgochod.mapper.UserAttributesMapper;
import com.fobgochod.service.LdapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述
 *
 * @author seven
 * @date 2019/5/19
 */
@Service
public class LdapServiceImpl implements LdapService {

    @Autowired
    private LdapTemplate ldapTemplate;

    @Override
    public boolean authenticate(String userId, String password) {
        EqualsFilter filter = new EqualsFilter("sAMAccountName", userId);
        ldapTemplate.setIgnorePartialResultException(true);
        return ldapTemplate.authenticate("", filter.toString(), password);
    }

    @Override
    public UserVO getUser(String userId) {
        LdapQuery ldapQuery = LdapQueryBuilder.query()
                .attributes("sAMAccountName", "name", "userPassword", "mobile", "mail")
                .where("objectClass").is("user")
                .and("objectCategory").is("person")
                .and("sAMAccountName").is(userId);
        ldapTemplate.setIgnorePartialResultException(true);
        List<UserVO> users = ldapTemplate.search(ldapQuery, new UserAttributesMapper());
        return users.get(0);
    }

    @Override
    public List<UserVO> getUsers() {
        LdapQuery ldapQuery = LdapQueryBuilder.query()
                .attributes("sAMAccountName", "name", "userPassword", "mobile", "mail")
                .where("objectClass").is("user")
                .and("objectCategory").is("person");
        ldapTemplate.setIgnorePartialResultException(true);
        return ldapTemplate.search(ldapQuery, new UserAttributesMapper());
    }
}
