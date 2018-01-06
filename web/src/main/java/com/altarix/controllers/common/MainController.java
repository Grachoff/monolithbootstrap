package com.altarix.controllers.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController extends AbstractWebController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndexPage() {
        return createModelAndView("index");
    }
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView getMainPage() {
        return createModelAndView("main");
    }
}
