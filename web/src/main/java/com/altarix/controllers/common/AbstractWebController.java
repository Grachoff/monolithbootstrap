package com.altarix.controllers.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ModelAndView;

public abstract class AbstractWebController {
    private String activeProfiles;

    @Value("${app.useCompressedResources}")
    private String useCompressedResources;

    @Value("${app.version}")
    private String version;

    @Autowired
    public void setEnv(Environment env) {
        activeProfiles = StringUtils.join(env.getActiveProfiles(), ", ").intern();
    }

    protected ModelAndView createModelAndView(String viewName) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        fillPropperties(modelAndView);
        return modelAndView;
    }

    private void fillPropperties(ModelAndView modelAndView) {
        modelAndView.addObject("activeprofiles", activeProfiles);
        modelAndView.addObject("usecompressedresources", useCompressedResources);
        modelAndView.addObject("version", version);
    }
}
