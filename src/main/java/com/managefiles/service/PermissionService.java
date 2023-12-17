package com.managefiles.service;

import com.managefiles.enums.PermissionLevel;
import com.managefiles.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public boolean hasEditAccess(String userEmail) {
        // Assuming there is a method in PermissionRepository to check if the user has EDIT access
        return permissionRepository.existsByUserEmailAndPermissionLevel(userEmail, PermissionLevel.EDIT.name());
    }
}
