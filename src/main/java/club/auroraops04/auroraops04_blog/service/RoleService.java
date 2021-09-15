package club.auroraops04.auroraops04_blog.service;

import club.auroraops04.auroraops04_blog.entities.Permission;
import club.auroraops04.auroraops04_blog.entities.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author AuroraOps04
 * @date 2021/9/15 01:29:02
 * @description
 */
public interface RoleService extends IService<Role> {
    /**
     * 查询用户的所有角色
     * @param id 角色id
     * @return 角色列表
     */
    List<Role> listByUserId(Long id);

    /**
     * 给用户添加角色
     * @param roleId 角色id
     * @param userId 用户id
     * @return 添加是否成功
     */
    boolean addToUser(Long roleId, Long userId);

    /**
     * 移除用户的角色
     * @param roleId 角色id
     * @param userId 用户id
     * @author AuroraOps04
     * @return 移除是否成功
     */
    boolean removeRoleFromUser(Long roleId, Long userId);

    /**
     * 删除角色,并删除角色和用户与权限的所有关联关系
     * @param id 角色id
     * @return 删除是否成功
     */
    boolean delete(Long id);
}
