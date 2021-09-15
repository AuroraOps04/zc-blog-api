package club.auroraops04.auroraops04_blog.service;

import club.auroraops04.auroraops04_blog.entities.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author AuroraOps04
 * @date 2021/9/15 00:54:48
 * @description
 */
public interface PermissionService extends IService<Permission> {
    List<Permission> listByUserId(Long id);

    boolean addToRole(Long roleId, Long permissionId);

    /**
     * 移除角色下的权限
     * @param roleId 角色id
     * @param permissionId 权限id
     * @author AuroraOps04
     * @return 移除是否成功
     */
    boolean removePermissionFromRole(Long roleId, Long permissionId);

    /**
     * 根据角色获取权限列表
     * @param id 角色id
     * @return 权限列表
     */
    List<Permission> listByRoleId(Long id);
}
