package backend.security.dashboard.dto;

import javax.validation.constraints.NotBlank;

public class UserRequestDTO extends UserCommonDTO {

    @NotBlank
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
