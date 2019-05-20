package com.fobgochod.api;

import com.fobgochod.domain.UserVO;
import com.fobgochod.entity.User;
import com.fobgochod.service.LdapService;
import com.fobgochod.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能描述
 *
 * @author seven
 * @date 2019/5/19
 */
@Controller
public class UserController {

    @Resource
    UserService userService;
    @Autowired
    private LdapService ldapService;

    @RequestMapping("/list")
    public String list(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    @RequestMapping("/add")
    public String add(User user) {
        UserVO adUser = ldapService.getUser(user.getId());
        user.setSid(0);
        user.setId(adUser.getUserId());
        user.setName(adUser.getUserName());
        user.setPassword(adUser.getPassword());
        userService.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, Long sid) {
        User user = userService.findBySid(sid);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(User user) {
        userService.update(user);
        return "redirect:/list";
    }

    @RequestMapping("/delete")
    public String delete(Long sid) {
        userService.delete(sid);
        return "redirect:/list";
    }
}
