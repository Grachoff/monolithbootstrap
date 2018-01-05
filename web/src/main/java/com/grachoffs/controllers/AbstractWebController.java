package com.grachoffs.controllers;

import com.grachoffs.services.BogusService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ModelAndView;

public abstract class AbstractWebController {
    protected String activeProfiles;
    @Autowired
    protected BogusService bogusService;

    @Autowired
    public void setEnv(Environment env) {
        activeProfiles = StringUtils.join(env.getActiveProfiles(), ", ").intern();
    }

    protected ModelAndView createModelAndView(String viewName) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("activeprofiles", activeProfiles);
        return modelAndView;
    }
}
