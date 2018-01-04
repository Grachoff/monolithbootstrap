package com.grachoffs.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
public class MainController {


    private String activeProfiles;

    @Autowired
    public MainController(Environment env) {
        activeProfiles = StringUtils.join(env.getActiveProfiles(), ", ").intern();
    }

    @RequestMapping("/")
    public ModelAndView getIndexPage() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("activeprofiles", activeProfiles);
        return modelAndView;
    }

    @RequestMapping("/login")
    public String getLoginPage() {
        return "login";
    }
    @RequestMapping("/main")
    public String getMainPage() {
        return "main";
    }

}
