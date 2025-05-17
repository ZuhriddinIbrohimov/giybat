package zuhriddinscode.dto;

import zuhriddinscode.enums.ProfileRole;

import java.util.List;
import java.util.Objects;

public class JwtDTO {

    private Integer id;
    private List<ProfileRole> rolesList;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "JwtDTO{" +
                "id=" + id +
                ", rolesList=" + rolesList +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JwtDTO jwtDTO = (JwtDTO) o;
        return Objects.equals(id, jwtDTO.id) && Objects.equals(rolesList, jwtDTO.rolesList) && Objects.equals(username, jwtDTO.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rolesList, username);
    }

    public JwtDTO(Integer id, List<ProfileRole> rolesList, String username) {
        this.id = id;
        this.rolesList = rolesList;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ProfileRole> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<ProfileRole> rolesList) {
        this.rolesList = rolesList;
    }
}