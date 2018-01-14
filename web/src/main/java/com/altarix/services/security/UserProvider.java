package com.altarix.services.security;

import com.altarix.dtos.security.LoginInfo;
import com.altarix.dtos.security.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserProvider {
    UserDetails findUser(String username);

    Optional<User> loadUserByLoginInfo(LoginInfo loginInfo);
}
