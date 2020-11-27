package com.example.fuzz.service;

import com.example.fuzz.model.Permission;
import com.example.fuzz.model.Role;
import com.example.fuzz.model.User;
import com.example.fuzz.repository.PermissionDao;
import com.example.fuzz.repository.RoleDao;
import com.example.fuzz.repository.UserDao;
import com.example.fuzz.vo.SelfUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/11/23 16:38
 */
@Service
public class SelfUserDetailService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        List<Role> roles = roleDao.selectByUserId(user.getId());
        List<Long> roleIds = roles.stream()
                .map(Role::getId)
                .collect(Collectors.toList());
        List<Permission> permissions = permissionDao.selectByRoleIdList(roleIds);
        return SelfUserDetails.create(user, roles, permissions);
    }
}
