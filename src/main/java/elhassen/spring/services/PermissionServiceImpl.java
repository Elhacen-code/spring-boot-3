package elhassen.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import elhassen.spring.dao.PermissionRepository;
import elhassen.spring.entity.Permission;
import java.util.List;
import java.util.Optional;

@Service
public class PermissionServiceImpl {

    @Autowired
    private PermissionRepository permissionRepository;

    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    public Permission getPermissionById(long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Permission not found"));
    }

    public Permission updatePermission(long id, Permission updatedPermission) {
        Optional<Permission> optionalPermission = permissionRepository.findById(id);
        if (optionalPermission.isPresent()) {
            Permission permission = optionalPermission.get();
            permission.setName(updatedPermission.getName());
            return permissionRepository.save(permission);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Permission not found");
        }
    }

    public Boolean deletePermission(long id) {
        permissionRepository.deleteById(id);
        return true;
    }
}
