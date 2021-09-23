package club.auroraops04.auroraops04_blog.entities;

import club.auroraops04.auroraops04_blog.entities.enums.UserStatus;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@TableName("user")
@ApiModel
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("主键")
    protected Long id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    protected String username;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    protected String password;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    protected String email;

    /**
     * 头像地址
     */
    @ApiModelProperty("头像地址")
    protected String avatar;

    /**
     * github地址
     */
    @ApiModelProperty("github地址")
    protected String github;

    /**
     * 个人说明
     */
    @ApiModelProperty("个人说明")
    protected String motto;

    @ApiModelProperty("用户状态")
    protected UserStatus status;

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