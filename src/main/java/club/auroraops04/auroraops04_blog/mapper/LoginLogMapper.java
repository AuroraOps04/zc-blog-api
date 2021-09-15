package club.auroraops04.auroraops04_blog.mapper;

import club.auroraops04.auroraops04_blog.entities.LoginLog;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLog> {


}