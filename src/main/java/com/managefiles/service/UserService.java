package com.managefiles.service;

import com.managefiles.entity.Permission;
import com.managefiles.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Permission> permissionOptional = permissionRepository.findByUserEmail(username);
        if (permissionOptional.isEmpty()) {
            throw new UsernameNotFoundException("This User is Not Found");
        }
        return new User(permissionOptional.get().getUserEmail(), "{noop}123", Collections.singleton(new SimpleGrantedAuthority(permissionOptional.get().getPermissionLevel())));
    }
}
