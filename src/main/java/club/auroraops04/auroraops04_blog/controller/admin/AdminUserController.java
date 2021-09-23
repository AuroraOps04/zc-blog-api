package club.auroraops04.auroraops04_blog.controller.admin;

import club.auroraops04.auroraops04_blog.core.BaseController;
import club.auroraops04.auroraops04_blog.entities.User;
import club.auroraops04.auroraops04_blog.service.UserService;
import club.auroraops04.auroraops04_blog.vo.request.LoginForm;
import club.auroraops04.auroraops04_blog.vo.request.RegisterForm;
import club.auroraops04.auroraops04_blog.vo.request.UpdateUserForm;
import club.auroraops04.auroraops04_blog.vo.request.UserListFilterForm;
import club.auroraops04.auroraops04_blog.vo.response.ApiPageResponse;
import club.auroraops04.auroraops04_blog.vo.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Set;

/**
 * @author AuroraOps04
 * @date 2021/9/14 15:46:47
 * @description
 */

@RestController
@RequestMapping("/admin/user")
@Api(value = "管理后台用户", tags = "admin")
@Validated
public class AdminUserController extends BaseController {
    @Autowired
    UserService userService;


    @GetMapping
    @ApiOperation("用户列表")
    @PreAuthorize("hasAuthority('admin:user:list')")
    @SuppressWarnings({"rawtypes"})
    public ApiPageResponse list(UserListFilterForm condition) {
        startPage();
        return getPageData(userService.listByCondition(condition));
    }

    @GetMapping("/{id}")
    @ApiOperation("获取用户详情")
    @PreAuthorize("hasAuthority('admin:user:getById')")
    public ApiResponse<User> getById(@PathVariable("id") Long id) {
        return success(userService.getById(id));
    }

    @PutMapping
    @ApiOperation("修改用户信息")
    @PreAuthorize("hasAuthority('admin:user:update')")
    public ApiResponse<Object> update(@RequestBody @Valid UpdateUserForm form){
        if(null == userService.getById(form.getId())){
            return success(false, "未找到该用户");
        }
        User user = new User();
        BeanUtils.copyProperties(form, user);
        return success(userService.update(user));
    }

    @DeleteMapping
    @ApiOperation("批量删除用户")
    @PreAuthorize("hasAuthority('admin:user:deleteBatch')")
    public ApiResponse<Object> deleteBatch(@RequestParam("ids") Set<Long> ids){
        return success(userService.deleteBatch(ids) == ids.size());
    }
}
