package club.auroraops04.auroraops04_blog.vo.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author AuroraOps04
 * @date 2021/9/14 15:54:12
 * @description
 */
public class LoginForm {
    @NotNull(message = "用户名不能为空")
    @Size(min = 6, max = 20, message = "用户名长度为6-20")
    private String username;

    @NotNull(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度为6-20")
    private String password;

    public LoginForm() {
    }

    public LoginForm(String username, String password) {
        this.username = username;
        this.password = password;
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
}
