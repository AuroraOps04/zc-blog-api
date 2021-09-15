package club.auroraops04.auroraops04_blog.controller.admin;

import club.auroraops04.auroraops04_blog.core.BaseController;
import club.auroraops04.auroraops04_blog.entities.Permission;
import club.auroraops04.auroraops04_blog.service.PermissionService;
import club.auroraops04.auroraops04_blog.vo.response.ApiResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author AuroraOps04
 * @date 2021/9/14 22:34:55
 * @description
 */

@RestController
@RequestMapping("/admin/permission")
@Api(value = "后台权限相关的api", tags = "admin")
public class AdminPermissionController extends BaseController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping
    @ApiOperation("权限列表")
    @PreAuthorize("hasAuthority('admin:permission:list')")
    @SuppressWarnings("rawtypes")
    public ApiResponse list() {
        startPage();
        return getPageData(permissionService.list());
    }

    @PostMapping
    @ApiOperation("新增权限")
    @PreAuthorize("hasAuthority('admin:permission:save')")
    @SuppressWarnings("rawtypes")
    public ApiResponse save(@RequestBody @Valid Permission permission) {
        Permission one = permissionService.getOne(new QueryWrapper<>(permission));
        if (null != one) {
            return success(false, "该权限已存在");
        }
        boolean save = permissionService.save(permission);
        return  save ? created() : success(false, "新增权限失败");

    }

    @PutMapping
    @ApiOperation("修改权限")
    @PreAuthorize("hasAuthority('admin:permission:update')")
    @SuppressWarnings("rawtypes")
    public ApiResponse update(@RequestBody @Valid Permission permission) {
        if (null == permission.getId() || null == permissionService.getById(permission.getId())) {
            return success(false, "没有找到指定权限");
        }
        return success(permissionService.updateById(permission));

    }

    @PutMapping("/{id}")
    @ApiOperation("删除权限")
    @PreAuthorize("hasAuthority('admin:permission:delete')")
    @SuppressWarnings("rawtypes")
    public ApiResponse delete(@PathVariable("id") Long id) {
        if (null == id || null == permissionService.getById(id)) {
            return success(false, "没有找到指定权限");
        }
        return success(permissionService.removeById(id));
    }

    @PostMapping("/addToRole")
    @ApiOperation("给角色添加权限")
    @PreAuthorize("hasAuthority('admin:permission:addToRole')")
    @SuppressWarnings("rawtypes")
    public ApiResponse addToRole(@RequestParam("roleId") Long roleId, @RequestParam("permissionId") Long permissionId){
        return success(permissionService.addToRole(roleId, permissionId));
    }

    @DeleteMapping("/removePermissionFromRole")
    @ApiOperation("给角色移除权限")
    @PreAuthorize("hasAuthority('admin:permission:removePermissionFromRole')")
    @SuppressWarnings("rawtypes")
    public ApiResponse removePermissionFromRole(@RequestParam("roleId") Long roleId, @RequestParam("permissionId") Long permissionId){
        return success(permissionService.removePermissionFromRole(roleId, permissionId));
    }

    @GetMapping("/listByRoleId")
    @ApiOperation("获取角色的所有权限")
    @PreAuthorize("hasAuthority('admin:permission:listByRoleId')")
    @SuppressWarnings("rawtypes")
    public ApiResponse listByRoleId(@RequestParam("roleId") Long roleId){
        return success(permissionService.listByRoleId(roleId));
    }
}
