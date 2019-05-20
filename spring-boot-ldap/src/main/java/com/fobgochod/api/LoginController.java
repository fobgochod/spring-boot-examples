package com.fobgochod.api;

import com.fobgochod.entity.User;
import com.fobgochod.service.LdapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能描述
 *
 * @author seven
 * @date 2019/5/19
 */
@Controller
public class LoginController {

    @Autowired
    @Qualifier("traditionalLdapService")
    LdapService ldapService;

    @RequestMapping({"/", "index"})
    public String index() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(User user) {
        boolean bool = ldapService.authenticate(user.getId(), user.getPassword());
        if (bool) {
            return "redirect:/list";
        }
        return "login";
    }

}
