package backend.security.dashboard.mapper;

import backend.security.dashboard.dto.IpDTO;
import backend.security.dashboard.model.Ip;
import org.mapstruct.Mapper;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface IpMapper {

    IpDTO toIpDTO(Ip ip);

    Set<IpDTO> toIpsDTO(Set<Ip> ips);
}
