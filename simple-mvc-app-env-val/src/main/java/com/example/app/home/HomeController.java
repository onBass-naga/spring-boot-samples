package com.example.app.home;

import com.example.app.conf.ExternalizedConfig;
import com.example.app.conf.RelaxedConfig;
import com.example.app.user.User;
import com.example.app.user.UserForm;
import com.example.app.user.UserService;
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
@RequestMapping("/")
class HomeController {

    @Autowired
    ExternalizedConfig externalizedConfig;

    @Autowired
    RelaxedConfig relaxedConfig;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("relConf", relaxedConfig);
        model.addAttribute("extConf", externalizedConfig);
        model.addAttribute("javaHomeViaController", System.getenv("JAVA_HOME"));
        return "index";
    }
}
