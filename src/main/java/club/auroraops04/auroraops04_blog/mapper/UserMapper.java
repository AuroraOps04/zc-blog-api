package club.auroraops04.auroraops04_blog.mapper;

import club.auroraops04.auroraops04_blog.entities.Permission;
import club.auroraops04.auroraops04_blog.entities.User;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper  extends BaseMapper<User> {
    User getByUsername(String username);

}