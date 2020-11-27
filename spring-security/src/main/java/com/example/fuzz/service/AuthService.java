package com.example.fuzz.service;

import com.example.fuzz.exception.GlobalException;
import com.example.fuzz.model.Permission;
import com.example.fuzz.model.Role;
import com.example.fuzz.repository.PermissionDao;
import com.example.fuzz.repository.RoleDao;
import com.example.fuzz.vo.SelfUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/11/27 15:12
 */
@Component
public class AuthService {
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object o = authentication.getPrincipal();

        if (!(o instanceof SelfUserDetails)) {
            System.out.println("error");
            return false;
        }

        boolean hasPermission = false;
        SelfUserDetails userDetails = (SelfUserDetails) o;
        Long userId = userDetails.getId();
        List<Role> roles = roleDao.selectByUserId(userId);
        List<Long> roleIds = roles.stream()
                .map(Role::getId)
                .collect(Collectors.toList());
        List<Permission> permissions = permissionDao.selectByRoleIdList(roleIds);
        for (Permission permission : permissions) {
            AntPathRequestMatcher antPathMatcher = new AntPathRequestMatcher(permission.getUrl(), permission.getMethod());
            if (antPathMatcher.matches(request)) {
                hasPermission = true;
                break;
            }
        }

        return hasPermission;
    }


}
