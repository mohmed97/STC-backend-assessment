package com.managefiles.service;

import com.managefiles.entity.Permission;
import com.managefiles.enums.PermissionLevel;
import com.managefiles.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public void createPermission(Permission permission) {
        permissionRepository.save(permission);
    }
}
