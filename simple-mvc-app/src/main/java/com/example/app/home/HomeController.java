package com.example.app.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by naga on 2015/01/29.
 */
@Controller
@RequestMapping("/")
class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }
}
