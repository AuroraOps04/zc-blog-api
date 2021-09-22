package club.auroraops04.auroraops04_blog.vo.request;

import club.auroraops04.auroraops04_blog.entities.enums.UserStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * @author AuroraOps04
 * @date 2021/9/15 14:34:39
 * @description
 */
@ApiModel("修改用户信息参数实体")
public class UpdateUserForm {
    @ApiModelProperty("主键")
    @NotNull
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    @Size(min = 6, max = 20, message = "用户名必须在6-20位")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @Size(min = 6, max = 20, message = "密码必须在6-20位")
    private String password;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    @Email(message = "邮箱格式不符合规则")
    private String email;

    /**
     * 头像地址
     */
    @ApiModelProperty("头像地址")
    @URL(message = "头像地址不符合规则")
    private String avatar;

    /**
     * github地址
     */
    @ApiModelProperty("github地址")
//    @URL(protocol = "https", port = 443, regexp = "https://github/.+", message = "github地址格式不符合规范")
    private String github;

    /**
     * 个人说明
     */
    @ApiModelProperty("个人说明")
    
    @Size(max = 255, message = "个人说明过长")
    private String motto;

    @ApiModelProperty("用户状态")
    
    private UserStatus status;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
