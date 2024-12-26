package com.project.library;

import org.springframework.security.core.GrantedAuthority;

public enum UserAuthority  implements GrantedAuthority  {
    READER,
    LIBRARIAN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}