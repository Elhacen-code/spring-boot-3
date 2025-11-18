package elhassen.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import elhassen.spring.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    boolean existsByName(String name);
}