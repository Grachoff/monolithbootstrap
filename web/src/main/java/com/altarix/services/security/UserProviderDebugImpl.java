package com.altarix.services.security;

import com.altarix.dtos.security.LoginInfo;
import com.altarix.dtos.security.Role;
import com.altarix.dtos.security.User;
import com.altarix.repositories.security.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserProviderDebugImpl implements UserProvider {
    @Autowired
    UserRepository userRepository;

    @Override
    public User findUser(String username) {
        return mapUserEntity(userRepository.findByUsername(username), new User().builder()
                .accountNonExpired(true).accountNonLocked(true).enabled(true).accountNonExpired(true).credentialsNonExpired(true)
                .build());
    }

    @Override
    public Optional<User> loadUserByLoginInfo(LoginInfo loginInfo) {
        return Optional.ofNullable(findUser(loginInfo.getUsername()));
    }

    private User mapUserEntity( @NonNull com.altarix.repoentities.security.User userEntity, @NonNull User user) {
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        user.setAuthorities(Collections.unmodifiableCollection(Arrays.asList(Role.USER, Role.ADMIN)));
        return user;
    }
}
