package club.auroraops04.auroraops04_blog.controller.admin;

import club.auroraops04.auroraops04_blog.core.BaseController;
import club.auroraops04.auroraops04_blog.entities.User;
import club.auroraops04.auroraops04_blog.service.UserService;
import club.auroraops04.auroraops04_blog.vo.request.SaveUserRequest;
import club.auroraops04.auroraops04_blog.vo.request.UpdateUserRequest;
import club.auroraops04.auroraops04_blog.vo.request.UserListFilterRequest;
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
    public ApiPageResponse list(UserListFilterRequest condition) {
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
    public ApiResponse<User> update(@RequestBody @Valid UpdateUserRequest form){
        if(null == userService.getById(form.getId())){
            return success(null, false);
        }
        User user = new User();
        BeanUtils.copyProperties(form, user);
        User updateUser = userService.update(user);
        if(null == updateUser){
            return success(null, false);
        }
        return success(updateUser);
    }

    @DeleteMapping
    @ApiOperation("批量删除用户")
    @PreAuthorize("hasAuthority('admin:user:deleteBatch')")
    public ApiResponse<Object> deleteBatch(@RequestParam("ids") Set<Long> ids){
        return success(userService.deleteBatch(ids) == ids.size());
    }

    @PostMapping
    @ApiOperation("添加用户")
    @PreAuthorize("hasAuthority('admin:user:add')")
    public ApiResponse<User> save(@RequestBody @Valid SaveUserRequest request){
        User user = new User();
       BeanUtils.copyProperties(request, user);
        boolean saved = userService.save(user);
        if(saved){
            return created(user);
        }
        return success(null, false);
    }
}
