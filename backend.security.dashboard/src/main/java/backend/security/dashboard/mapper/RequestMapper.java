package backend.security.dashboard.mapper;

import backend.security.dashboard.dto.RequestDTO;
import backend.security.dashboard.model.Request;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    RequestDTO toRequestDTO(Request request);

    List<RequestDTO> toRequestListDTO(List<Request> requestList);

    @AfterMapping
    default void addActions(@MappingTarget  RequestDTO requestDTO){
        Set<String> actions = Set.of("DELETE");
        requestDTO.setActions(actions);
    }
}
