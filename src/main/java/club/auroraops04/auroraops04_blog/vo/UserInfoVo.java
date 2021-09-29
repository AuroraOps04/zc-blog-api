package club.auroraops04.auroraops04_blog.vo;

import club.auroraops04.auroraops04_blog.entities.LoginLog;
import club.auroraops04.auroraops04_blog.entities.Permission;
import club.auroraops04.auroraops04_blog.entities.Role;
import club.auroraops04.auroraops04_blog.entities.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
@ApiModel
public class UserInfoVo extends User{
    @ApiModelProperty("用户拥有的角色")
    private List<Role> roles;
    @ApiModelProperty("用户拥有的权限")
    private List<Permission> permissions;
    @ApiModelProperty("用户上次登录信息")
    private LoginLog lastLogin;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserInfoVo{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", avatar='").append(avatar).append('\'');
        sb.append(", github='").append(github).append('\'');
        sb.append(", motto='").append(motto).append('\'');
        sb.append(", status=").append(status);
        sb.append(", roles=").append(roles);
        sb.append(", permissions=").append(permissions);
        sb.append(", lastLogin=").append(lastLogin);
        sb.append('}');
        return sb.toString();
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public LoginLog getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LoginLog lastLogin) {
        this.lastLogin = lastLogin;
    }
}
