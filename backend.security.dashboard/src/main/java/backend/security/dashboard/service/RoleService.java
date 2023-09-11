package backend.security.dashboard.service;

import backend.security.dashboard.dto.RoleDTO;
import backend.security.dashboard.mapper.RoleMapper;
import backend.security.dashboard.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    @Autowired
    public RoleService(RoleMapper roleMapper , RoleRepository roleRepository){
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
    }

    public Set<RoleDTO> getAllRoles(){
        return this.roleRepository.findAll().stream().map(roleMapper::toRoleDTO).collect(Collectors.toSet());
    }
}
