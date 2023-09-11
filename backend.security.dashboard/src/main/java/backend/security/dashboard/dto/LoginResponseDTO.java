package backend.security.dashboard.dto;

public class LoginResponseDTO {

    private boolean otpRequired;

    private String jwt;

    private Long id;

    private String username;

    private String email;

    private String role;

    public LoginResponseDTO(String jwt, Long id, String username, String email, String role) {
        this.jwt = jwt;
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.otpRequired = false;
    }

    public LoginResponseDTO(boolean otpRequired){
        this.otpRequired = otpRequired;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isOtpRequired() {
        return otpRequired;
    }

    public void setOtpRequired(boolean otpRequired) {
        this.otpRequired = otpRequired;
    }
}
