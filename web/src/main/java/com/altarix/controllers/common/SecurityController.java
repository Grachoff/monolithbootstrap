package com.altarix.controllers.common;

import com.altarix.services.ReCaptchaApiService;
import dtos.security.LoginDto;
import dtos.security.ReCaptchaResponseDto;
import dtos.security.RegisterDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
public class SecurityController extends AbstractWebController {
    @Autowired
    private ReCaptchaApiService reCaptchaApiClient;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(RegisterDto registerDto) {
        log.info("User registration: {}", registerDto);
        if (!reCaptchaApiClient.verify(registerDto.getRecaptchaResponse())) {
            throw new RuntimeException();
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RedirectView login(LoginDto loginDto, RedirectAttributes attributes) {
        log.info("User login: {}", loginDto);
        return new RedirectView("main");
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage() {
        return createModelAndView("login");
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView getRegisterPage() {
        return createModelAndView("register");
    }

}
