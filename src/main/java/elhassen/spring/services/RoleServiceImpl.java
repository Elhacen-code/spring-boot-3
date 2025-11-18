package elhassen.spring.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import elhassen.spring.dao.RoleRepository;
import elhassen.spring.entity.Role;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(long id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            return role;
        } else {
            throw new IllegalArgumentException("Role with ID " + id + " not found");
        }
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Role updatedRole) {
        return roleRepository.save(updatedRole);
    }

    public void deleteRole(long id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            roleRepository.delete(role);
        } else {
            throw new IllegalArgumentException("Role with ID " + id + " not found");
        }
    }
}
