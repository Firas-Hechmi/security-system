package backend.security.dashboard.mapper;

import backend.security.dashboard.dto.UserDTO;
import backend.security.dashboard.model.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring",uses = {RoleMapper.class, IpMapper.class})
public interface UserMapper {

    UserDTO toUserDTO(User user);

    List<UserDTO> toUsersDTO(List<User> users);


    @AfterMapping
    default void addActions(@MappingTarget UserDTO userDTO){
        Set<String> actions = Set.of("EDIT","DELETE");
        userDTO.setActions(actions);
    }

}
