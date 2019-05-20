package com.fobgochod.service.impl;

import com.fobgochod.domain.UserVO;
import com.fobgochod.mapper.UserAttributesMapper;
import com.fobgochod.service.LdapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.naming.Context;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * 功能描述
 *
 * @author seven
 * @date 2019/5/19
 */
@Service("traditionalLdapService")
public class TraditionalLdapServiceImpl implements LdapService {

    private static final Logger logger = LoggerFactory.getLogger(TraditionalLdapServiceImpl.class);
    @Autowired
    UserAttributesMapper userAttributesMapper;
    @Value("${spring.ldap.urls}")
    private String urls;
    @Value("${spring.ldap.base}")
    private String base;
    @Value("${spring.ldap.username}")
    private String username;
    @Value("${spring.ldap.password}")
    private String password;

    @Override
    public boolean authenticate(String userId, String password) {
        try {
            getDirContext(String.format("%s@digiwin.com", userId), password);
        } catch (Exception e) {
            logger.error("LDAP验证失败", e);
            return false;
        }
        return true;
    }

    @Override
    public UserVO getUser(String userId) {
        String filter = String.format("(&(sAMAccountName=%s)(objectCategory=person)(objectClass=user))", userId);
        DirContext ctx = getDirContext();
        List<UserVO> list = search(ctx, filter);
        return list.get(0);
    }

    @Override
    public List<UserVO> getUsers() {
        String filter = "(&(objectCategory=person)(objectClass=top))";
        DirContext ctx = getDirContext();
        List<UserVO> list = search(ctx, filter);
        return list;
    }

    private List<UserVO> search(DirContext ctx, String filter) {
        List<UserVO> list = new ArrayList<>();
        NamingEnumeration<SearchResult> results = null;
        try {
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            results = ctx.search("", filter, controls);
            while (results.hasMoreElements()) {
                SearchResult searchResult = results.next();
                Attributes attributes = searchResult.getAttributes();

                UserVO user = userAttributesMapper.mapFromAttributes(attributes);
                list.add(user);
            }
        } catch (NameNotFoundException e) {
            // The base context was not found.
            // Just clean up and exit.
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } finally {
            if (results != null) {
                try {
                    results.close();
                } catch (Exception e) {
                    // Never mind this.
                }
            }
            if (ctx != null) {
                try {
                    ctx.close();
                } catch (Exception e) {
                    // Never mind this.
                }
            }
        }
        return list;
    }

    private DirContext getDirContext() {
        return getDirContext(username, password);
    }

    private DirContext getDirContext(String principal, String credentials) {
        Hashtable env = new Hashtable();

        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, urls);
        env.put(Context.SECURITY_PRINCIPAL, principal);
        env.put(Context.SECURITY_CREDENTIALS, credentials);
        env.put("com.sun.jndi.ldap.connect.timeout", "3000");

        DirContext ctx;
        try {
            ctx = new InitialDirContext(env);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return ctx;
    }


}

