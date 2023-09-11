package backend.security.dashboard.dto;

import java.util.Set;

public class UserDTO extends UserCommonDTO{

    private String username;

    private RoleDTO role ;

    private String creationDate;

    private String lastModificationDate;

    private Set<IpDTO> ips ;

    private Set<String> actions;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(String lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public void setIps(Set<IpDTO> ips) {
        this.ips = ips;
    }

    public Set<IpDTO> getIps() {
        return ips;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public Set<String> getActions() {
        return actions;
    }

    public void setActions(Set<String> actions) {
        this.actions = actions;
    }
}
