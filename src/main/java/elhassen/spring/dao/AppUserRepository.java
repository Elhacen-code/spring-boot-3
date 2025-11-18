package elhassen.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import elhassen.spring.entity.AppUser;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByUsername(String username);
    List<AppUser> findByUsernameContains(String username);
    Optional<AppUser> findByEmail(String email);
}