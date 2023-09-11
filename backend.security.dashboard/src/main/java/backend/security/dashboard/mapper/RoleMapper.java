package backend.security.dashboard.mapper;

import backend.security.dashboard.dto.RoleDTO;
import backend.security.dashboard.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO toRoleDTO(Role role);
}
