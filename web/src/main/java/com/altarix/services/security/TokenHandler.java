package com.altarix.services.security;

import com.altarix.dtos.security.LoginInfo;

import java.util.Optional;

public interface TokenHandler {
    Optional<LoginInfo> extractLoginInfo(String s);
}
