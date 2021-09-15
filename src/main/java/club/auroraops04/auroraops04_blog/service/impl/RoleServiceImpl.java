package club.auroraops04.auroraops04_blog.service.impl;

import club.auroraops04.auroraops04_blog.entities.Role;
import club.auroraops04.auroraops04_blog.mapper.RoleMapper;
import club.auroraops04.auroraops04_blog.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author AuroraOps04
 * @date 2021/9/15 01:35:39
 * @description
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    /**
     * 查询用户的所有角色
     *
     * @param id 角色id
     * @return 角色列表
     */
    @Override
    public List<Role> listByUserId(Long id) {
        return baseMapper.listByUserId(id);
    }

    /**
     * 给用户添加角色
     *
     * @param roleId 角色id
     * @param userId 用户id
     * @return 添加是否成功
     */
    @Override
    public boolean addToUser(Long roleId, Long userId) {
        boolean b = baseMapper.listByUserId(userId).stream().anyMatch(o -> o.getId().equals(roleId));
        if(b){
            return false;
        }
        return baseMapper.addToUser(roleId, userId) >= 1;
    }

    /**
     * 移除用户的角色
     *
     * @param roleId 角色id
     * @param userId 用户id
     * @return 移除是否成功
     * @author AuroraOps04
     */
    @Override
    public boolean removeRoleFromUser(Long roleId, Long userId) {
        return baseMapper.removeRoleFromUser(roleId, userId) >= 1;
    }

    /**
     * 删除角色,并删除角色和用户与权限的所有关联关系
     *
     * @param id 角色id
     * @return 删除是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        baseMapper.removeRoleFromAllUser(id);
        baseMapper.removeAllPermissionByRoleId(id);
        return baseMapper.deleteById(id) > 0;
    }
}
