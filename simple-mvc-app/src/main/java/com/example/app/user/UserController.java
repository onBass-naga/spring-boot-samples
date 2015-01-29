package com.example.app.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Locale;

/**
 * Created by naga on 2015/01/29.
 */
@Controller
@RequestMapping("users")
class UserController {

    @Autowired
    UserService userService;

    @ModelAttribute
    UserForm setUpForm() {
        return new UserForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users/index";
    }

    @RequestMapping(value="create", method = RequestMethod.GET)
    public String create() {
        return "users/create";
    }

    @RequestMapping(value="save", method = RequestMethod.POST)
    public String save(@Validated UserForm form, BindingResult result, Locale locale, Model model) {

        if (result.hasErrors()) {
            return create();
        }

        User user = new User();
        user.setName(form.getName());
        user.setEmail(form.getEmail());

        userService.save(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@Validated UserForm form, BindingResult result, Model model) {
        userService.delete(Long.parseLong(form.getId()));
        return "redirect:/users";
    }
}
