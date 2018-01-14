package com.altarix.services.security;

import com.altarix.ConstantsHolder;
import com.altarix.dtos.security.LoginInfo;
import com.altarix.dtos.security.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Optional;

@Service
public class TokenAuthServiceImpl implements TokenAuthService {
    private static final String AUTH_HEADER_NAME = "X-Auth-Token";
    @Autowired
    private TokenHandler tokenHandler;
    @Resource(name="userProviderDebugImpl")
    UserProvider userProvider;

    @Override
    public Optional<Authentication> getAuthentication(HttpServletRequest servletRequest) {
        return Optional
                .ofNullable(servletRequest.getHeader(AUTH_HEADER_NAME))
                .flatMap(tokenHandler::extractLoginInfo)
                .flatMap(loginInfo -> checkForTimeOut(loginInfo))
                .flatMap(userProvider::loadUserByLoginInfo)
                .map(UserAuthentication::new);
    }

    private Optional<LoginInfo> checkForTimeOut(LoginInfo loginInfo) {
        if (loginInfo.getDate().plusMillis(ConstantsHolder.LOGIN_TOKEN_TTL_MILLIS).isBefore(Instant.now())) {
            return Optional.empty();
        } else {
            return Optional.of(loginInfo);
        }
    }
}
