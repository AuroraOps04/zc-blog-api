package club.auroraops04.auroraops04_blog.mapper;

import club.auroraops04.auroraops04_blog.entities.Permission;
import club.auroraops04.auroraops04_blog.entities.Role;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> listByUserId(Long id);

    int addToUser(@Param("roleId") Long roleId, @Param("userId") Long userId);

    int removeRoleFromUser(@Param("roleId") Long roleId, @Param("userId") Long userId);

    int removeRoleFromAllUser(Long id);

    int removeAllPermissionByRoleId(Long id);
}