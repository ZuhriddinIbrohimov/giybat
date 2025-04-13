package zuhriddinscode.dto;

import jakarta.validation.constraints.NotBlank;

public class RegistrationDTO {

    @NotBlank(message = "name required")
    private String name;

    @NotBlank(message = "username required")
    private String username;

    @NotBlank(message = "password required")
    private String password;

    @Override
    public String toString() {
        return "RegistrationDTO{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}