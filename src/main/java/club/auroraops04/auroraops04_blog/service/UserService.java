package club.auroraops04.auroraops04_blog.service;

import club.auroraops04.auroraops04_blog.entities.Permission;
import club.auroraops04.auroraops04_blog.entities.User;
import club.auroraops04.auroraops04_blog.vo.UserInfoVo;
import club.auroraops04.auroraops04_blog.vo.request.UserListFilterForm;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author AuroraOps04
 * @date 2021/9/14 15:33:04
 * @description 用户, 角色, 权限, 相关的业务类
 */
public interface UserService extends IService<User> {
    User getByUsername(String username);



    Optional<String> login(String username, String password);

    boolean register(User user);

    boolean update(User user);

    User getByCondition(User condition);

    List<UserInfoVo> listByCondition(UserListFilterForm condition);

    int deleteBatch(Set<Long> ids);
}
