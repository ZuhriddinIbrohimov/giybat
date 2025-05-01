package zuhriddinscode.dto;

import zuhriddinscode.enums.ProfileRole;
import java.util.List;

public class ProfileDTO {

    private String username;
    private String name;
    private List<ProfileRole> roleList;
    private String jwt;


    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public List<ProfileRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<ProfileRole> roleList) {
        this.roleList = roleList;
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