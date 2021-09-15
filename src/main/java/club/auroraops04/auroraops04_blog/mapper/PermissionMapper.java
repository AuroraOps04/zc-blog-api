package club.auroraops04.auroraops04_blog.mapper;

import club.auroraops04.auroraops04_blog.entities.Permission;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> listByUserId(Long id);

    int addToRole(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    List<Permission> listByRoleId(Long id);

    int removePermissionFromRole(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);
}