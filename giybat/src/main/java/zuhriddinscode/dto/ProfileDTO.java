package zuhriddinscode.dto;

import zuhriddinscode.enums.ProfileRoles;
import java.util.List;

public class ProfileDTO {

    private String username;
    private String name;
    private List<ProfileRoles> roles;
    private String jwt;


    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public List<ProfileRoles> getRoles() {
        return roles;
    }

    public void setRoles(List<ProfileRoles> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}