package club.auroraops04.auroraops04_blog.mapper;

import club.auroraops04.auroraops04_blog.entities.User;

import java.util.List;

import club.auroraops04.auroraops04_blog.vo.UserInfoVo;
import club.auroraops04.auroraops04_blog.vo.request.UserListFilterForm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper  extends BaseMapper<User> {
    User getByUsername(String username);
    List<UserInfoVo> listByCondition(UserListFilterForm condition);

}