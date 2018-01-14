package com.altarix.dtos.security;

import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;

import static com.altarix.ConstantsHolder.ROLE_PREFIX;

public enum Role implements GrantedAuthority {
    USER("Пользователь"),
    ADMIN("Администратор");

    private final String jsName;
    private final String roleName;
    private final String uiName;

    Role(@NonNull String uiName) {
        this.jsName = this.name().toLowerCase();
        this.roleName = ROLE_PREFIX + this.name();
        this.uiName = uiName;
    }
    public String getAuthority() {
        return roleName;
    }
}
