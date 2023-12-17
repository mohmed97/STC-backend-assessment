package com.managefiles.repository;

import com.managefiles.entity.Permission;
import com.managefiles.enums.PermissionLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    boolean existsByUserEmailAndPermissionLevel(String userEmail, String permissionLevel);

    Optional<Permission> findByUserEmail(String username);
}
