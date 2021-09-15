package club.auroraops04.auroraops04_blog.dto;

import club.auroraops04.auroraops04_blog.entities.Permission;
import club.auroraops04.auroraops04_blog.entities.User;
import club.auroraops04.auroraops04_blog.entities.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author AuroraOps04
 * @date 2021/9/14 15:22:10
 * @description
 */

public class BlogUserDetails implements UserDetails {
    private User user;
    private List<Permission> permissions;

    public BlogUserDetails(User user, List<Permission> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return permissions.stream()
                .filter(permission -> permission.getPath()!=null)
                .map(permission ->new SimpleGrantedAuthority(permission.getPath()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return !UserStatus.DISABLED.equals(user.getStatus());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !UserStatus.INACTIVE.equals(user.getStatus());
    }
}
