package club.auroraops04.auroraops04_blog.controller.blog;

import club.auroraops04.auroraops04_blog.core.BaseController;
import club.auroraops04.auroraops04_blog.entities.User;
import club.auroraops04.auroraops04_blog.service.UserService;
import club.auroraops04.auroraops04_blog.utils.JwtTokenUtil;
import club.auroraops04.auroraops04_blog.vo.request.LoginRequest;
import club.auroraops04.auroraops04_blog.vo.request.RegisterRequest;
import club.auroraops04.auroraops04_blog.vo.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Optional;

/**
 * @author AuroraOps04
 * @date 2021/9/14 22:20:13
 * @description
 */
@RestController
@RequestMapping("/user")
@Api(value = "前台用户相关api", tags = "blog")
public class BlogUserController extends BaseController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    @ApiOperation("登录api")
    public ApiResponse<Object> login(@RequestBody @Valid LoginRequest form) {
        Optional<String> token = userService.login(form.getUsername(), form.getPassword());
        if (!token.isPresent()) {
            return success(false, "账号密码错误");
        }
        HashMap<String, String> map = new HashMap<>();
        map.put(tokenHeader, token.get());
        return success(map);
    }

    @PostMapping("/register")
    @ApiOperation("注册api")
    public ApiResponse<Object> register(@RequestBody @Valid RegisterRequest form) {
        User user = new User();
        BeanUtils.copyProperties(form, user);
        boolean saved = userService.register(user);
        if (saved) {
            return created(user);
        } else {
            return success(null, false);
        }
    }

    @PutMapping("/")
    @ApiOperation("修改个人用户信息")
    @PreAuthorize("hasAuthority('blog:user:edit')")
    public ApiResponse<User> update(@RequestBody @Valid User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        User byUsername = userService.getByUsername(principal.getUsername());
        BeanUtils.copyProperties(user, byUsername, "username", "email", "id");

        User updateUser = userService.update(user);
        if(null == updateUser){
            return success(null, false);
        }
        return success(updateUser);
    }


}
