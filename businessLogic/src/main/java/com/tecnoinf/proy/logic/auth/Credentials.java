package com.tecnoinf.proy.logic.auth;

import com.tecnoinf.proy.logic.auth.enums.Role;

/**
 * Created by JuanPablo on 4/28/2016.
 */
public class Credentials {
    private String username;
    private String password;
    private Role role;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
