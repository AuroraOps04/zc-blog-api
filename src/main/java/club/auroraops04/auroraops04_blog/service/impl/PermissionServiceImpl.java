package club.auroraops04.auroraops04_blog.service.impl;

import club.auroraops04.auroraops04_blog.entities.Permission;
import club.auroraops04.auroraops04_blog.mapper.PermissionMapper;
import club.auroraops04.auroraops04_blog.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author AuroraOps04
 * @date 2021/9/15 00:56:26
 * @description
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> listByUserId(Long id) {
        return baseMapper.listByUserId(id);
    }

    @Override
    public boolean addToRole(Long roleId, Long permissionId) {
        if(null == baseMapper.selectById(roleId) || null == permissionMapper.selectById(permissionId)){
            return false;
        }
        boolean b = baseMapper.listByRoleId(roleId).stream().anyMatch(o -> o.getId().equals(permissionId));
        if(b){
            return false;
        }
        return baseMapper.addToRole(roleId, permissionId) >= 1;
    }

    /**
     * 移除角色下的权限
     *
     * @param roleId       角色id
     * @param permissionId 权限id
     * @return 移除是否成功
     * @author AuroraOps04
     */
    @Override
    public boolean removePermissionFromRole(Long roleId, Long permissionId) {
        return baseMapper.removePermissionFromRole(roleId, permissionId) >= 1;
    }

    /**
     * 根据角色获取权限列表
     *
     * @param id 角色id
     * @return 权限列表
     */
    @Override
    public List<Permission> listByRoleId(Long id) {
        return baseMapper.listByRoleId(id);
    }


}
