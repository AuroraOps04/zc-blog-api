package club.auroraops04.auroraops04_blog.vo.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author AuroraOps04
 * @date 2021/9/14 16:08:21
 * @description
 */
public class RegisterForm {

    @NotNull(message = "用户名不能为空")
    @Size(min = 6, max = 20, message = "用户名长度为6-20")
    private String username;

    @NotNull(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度为6-20")
    private String password;

    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式不符合")
    private String email;

    public RegisterForm() {
    }

    public RegisterForm(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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
}
