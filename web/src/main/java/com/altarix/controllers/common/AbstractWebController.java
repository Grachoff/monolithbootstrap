package com.altarix.controllers.common;

import com.altarix.entities.files.File;
import com.altarix.entities.security.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import static java.util.stream.Collectors.joining;

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username, roles;
        if (authentication !=null) {
            Object user = authentication.getPrincipal();
            if (user instanceof User) {
                username = ((User) user).getUsername();
                roles = ((User) user).getAuthorities().stream().map(role -> {return "'"+role.getName()+"'";}).collect(joining(","));
            } else {
                username = user.toString();
                roles = authentication.getAuthorities().stream().map(role -> {return "'"+role.getAuthority()+"'";}).collect(joining(","));
            }
        } else {
            username=StringUtils.EMPTY;
            roles=StringUtils.EMPTY;
        }

        modelAndView.addObject("activeprofiles", activeProfiles);
        modelAndView.addObject("usecompressedresources", useCompressedResources);
        modelAndView.addObject("version", version);
        modelAndView.addObject("username", username);
        modelAndView.addObject("roles", roles);
    }

    protected ResponseEntity<Resource> getResponseEntityWithFile(File file) {
        InputStreamResource inputStreamResource = new InputStreamResource(file.getData());

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\""+ file.getFileName() +"\"")
                .contentLength(file.getSize())
                .contentType(MediaType.parseMediaType(file.getMimeType()))
                .body(inputStreamResource);
    }
}
