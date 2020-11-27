package com.example.fuzz.vo;

import cn.hutool.core.util.StrUtil;
import com.example.fuzz.model.Permission;
import com.example.fuzz.model.Role;
import com.example.fuzz.model.User;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/11/23 17:08
 */
@Data
@Accessors(chain = true)
@Component
public class SelfUserDetails implements UserDetails {
    private List<String> roles;

    private Collection<? extends GrantedAuthority> authorities;

    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String phone;

    private String email;

    private Long birthday;

    private Integer sex;

    private Integer status;

    @Column(name = "create_time")
    private Long createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Long updateTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public static SelfUserDetails create(User user, List<Role> roles, List<Permission> permissions) {
        List<String> roleNames = roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        List<GrantedAuthority> authorities = permissions.stream()
                .filter(permission -> StrUtil.isNotBlank(permission.getPermission()))
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());

        SelfUserDetails userDetails = new SelfUserDetails();
        BeanUtils.copyProperties(user, userDetails);
        userDetails.setRoles(roleNames).setAuthorities(authorities);
        return userDetails;
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
