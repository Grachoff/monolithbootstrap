package com.altarix.controllers.security;

import com.altarix.dtos.security.Role;
import com.altarix.controllers.common.AbstractWebController;
import com.altarix.repoentities.security.User;
import com.altarix.repositories.security.UserRepository;
import com.altarix.services.security.ReCaptchaApiService;
import com.altarix.dtos.security.RegisterDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
public class SecurityController extends AbstractWebController {
    @Autowired
    private ReCaptchaApiService reCaptchaApiClient;

    @Autowired
    private UserRepository userRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(RegisterDto registerDto) {
        log.info("User registration: {}", registerDto);
        if (!reCaptchaApiClient.verify(registerDto.getRecaptchaResponse())) {
            throw new RuntimeException();
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(@RequestParam(value = "error", required = false) String error,
                                     @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView view = createModelAndView("login");
        view.addObject("error", error!=null);
        view.addObject("logout", logout!=null);
        return view;
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView getRegisterPage() {
        return createModelAndView("register");
    }

    @RequestMapping(value = "/secured/all-roles", method = RequestMethod.GET)
    @ResponseBody
    public List<Role> getRolesList() {
        return Arrays.asList(Role.values());
    }

    @RequestMapping(value = "/secured/all-users", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
