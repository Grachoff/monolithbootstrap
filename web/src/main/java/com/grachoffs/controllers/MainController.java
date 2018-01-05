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
public class MainController extends AbstractWebController {

    @RequestMapping("/")
    public ModelAndView getIndexPage() {
        return createModelAndView("index");
    }
    @RequestMapping("/login")
    public ModelAndView getLoginPage() {
        return createModelAndView("login");
    }
    @RequestMapping("/main")
    public ModelAndView getMainPage() {
        return createModelAndView("main");
    }

}
