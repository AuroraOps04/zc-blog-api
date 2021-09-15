package club.auroraops04.auroraops04_blog.controller.admin;

import club.auroraops04.auroraops04_blog.core.BaseController;
import club.auroraops04.auroraops04_blog.entities.Role;
import club.auroraops04.auroraops04_blog.service.RoleService;
import club.auroraops04.auroraops04_blog.vo.response.ApiPageResponse;
import club.auroraops04.auroraops04_blog.vo.response.ApiResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author AuroraOps04
 * @date 2021/9/14 22:33:49
 * @description
 */
@RestController
@RequestMapping("/admin/role")
@Api(value = "后台角色相关的api", tags = "admin")
public class AdminRoleController extends BaseController {
    @Autowired
    private RoleService roleService;


    @ApiOperation("查询用户所有角色")
    @GetMapping("/listByUserId")
    @SuppressWarnings("rawtypes")
    @PreAuthorize("hasAuthority('admin:role:listByUserId')")
    public ApiResponse listByUserId(@RequestParam("id") Long id){
        startPage();
        return getPageData(roleService.listByUserId(id));
    }

    @ApiOperation("给用户添加角色")
    @PostMapping("/addToUser")
    @PreAuthorize("hasAuthority('admin:role:addToUser')")
    public ApiResponse<Object> addToUser(@RequestParam("roleId") Long roleId, @RequestParam("userId") Long userId){
        return success(roleService.addToUser(roleId, userId));
    }
    @ApiOperation("移除用户的角色")
    @DeleteMapping("/removeRoleFromUser")
    @PreAuthorize("hasAuthority('admin:role:removeRoleFromUser')")
    public ApiResponse<Object> removeRoleFromUser(@RequestParam("roleId") Long roleId, @RequestParam("userId")Long userId){
        return success(roleService.removeRoleFromUser(roleId, userId));
    }

    @ApiOperation("角色列表")
    @GetMapping
    @PreAuthorize("hasAuthority('admin:role:list')")
    @SuppressWarnings("rawtypes")
    public ApiPageResponse list(){
        startPage();
        return getPageData(roleService.list());
    }

    @ApiOperation("新增角色")
    @PostMapping
    @PreAuthorize("hasAuthority('admin:role:save')")
    public ApiResponse<Object> save(@RequestBody @Valid Role role){
        if(roleService.getOne(new QueryWrapper<>(role)) != null){
            return success(false, "该角色已存在");
        }
        return success(roleService.save(role));
    }


    @ApiOperation("修改角色")
    @PutMapping
    @PreAuthorize("hasAuthority('admin:role:update')")
    public ApiResponse<Object> update(@RequestBody @Valid Role role){
        if(null == role.getId() || roleService.getById(role.getId()) == null){
            return success(false, "该角色不存在");
        }
        return success(roleService.updateById(role));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除角色")
    @PreAuthorize("hasAuthority('admin:role:delete')")
    public ApiResponse<Object> delete(@PathVariable("id") Long id){
        if(roleService.getById(id) == null){
            return success(false, "该角色不存在, 无法删除");
        }
        return  success(roleService.delete(id));
    }

}
