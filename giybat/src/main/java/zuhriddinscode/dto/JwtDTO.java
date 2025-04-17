package zuhriddinscode.dto;

import zuhriddinscode.enums.ProfileRoles;

import java.util.List;
import java.util.Objects;

public class JwtDTO {

    private Integer id;
    private List<ProfileRoles> rolesList;


    public JwtDTO(Integer id, List<ProfileRoles> rolesList) {
        this.id = id;
        this.rolesList = rolesList;
    }

    @Override
    public String toString() {
        return "JwtDTO{" +
                "id=" + id +
                ", rolesList=" + rolesList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JwtDTO jwtDTO = (JwtDTO) o;
        return Objects.equals(id, jwtDTO.id) && Objects.equals(rolesList, jwtDTO.rolesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rolesList);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ProfileRoles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<ProfileRoles> rolesList) {
        this.rolesList = rolesList;
    }
}