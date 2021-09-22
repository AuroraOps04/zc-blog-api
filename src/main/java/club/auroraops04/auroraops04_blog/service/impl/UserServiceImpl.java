package club.auroraops04.auroraops04_blog.service.impl;

import club.auroraops04.auroraops04_blog.entities.LoginLog;
import club.auroraops04.auroraops04_blog.entities.User;
import club.auroraops04.auroraops04_blog.mapper.LoginLogMapper;
import club.auroraops04.auroraops04_blog.mapper.UserMapper;
import club.auroraops04.auroraops04_blog.service.UserService;
import club.auroraops04.auroraops04_blog.utils.IpUtils;
import club.auroraops04.auroraops04_blog.utils.JwtTokenUtil;
import club.auroraops04.auroraops04_blog.utils.ServletUtils;
import cn.hutool.http.useragent.UserAgent;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author AuroraOps04
 * @date 2021/9/14 15:36:16
 * @description
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    LoginLogMapper loginLogMapper;

    @Override
    public User getByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getUsername, username);
        return baseMapper.selectOne(wrapper);
    }


    @Override
    public Optional<String> login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        try{
            if(!passwordEncoder.matches(password, userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenUtil.generateToken(userDetails);

            // 记录登录日志
            LoginLog loginLog = new LoginLog();
            loginLog.setTime(new Date());
            UserAgent userAgent = ServletUtils.getUserAgent();
            loginLog.setDevice(String.format("%s.%s.%s", userAgent.getOs().getName(), userAgent.getPlatform().getName(), userAgent.getBrowser().getName()));
            loginLog.setIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
            loginLog.setUserId(baseMapper.getByUsername(username).getId());
            int insert = loginLogMapper.insert(loginLog);

            if(insert <= 0){
                LOGGER.warn("插入登录日志失败,username: {}", username);
            }

            return Optional.of(token);
        }catch (AuthenticationException e){
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean register(User user) {
        //TODO: 检测用户名和邮箱是否存在
        //TODO: 发送事件 激活邮件给用户,让用户激活后才能使用,提取一个方法
//        user.setStatus(UserStatus.INACTIVE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return save(user);
    }

    @Override
    public boolean update(User user) {
        if(null == user || null == user.getId()){
            return false;
        }
        int i = baseMapper.updateById(user);
        return i > 0;
    }

    @Override
    public User getByCondition(User condition) {
        return baseMapper.selectOne(new QueryWrapper<>(condition));
    }

    @Override
    public List<User> listByCondition(User condition) {
        return baseMapper.selectList(new QueryWrapper<>(condition));
    }

    @Override
    public int deleteBatch(Set<Long> ids) {
        return baseMapper.deleteBatchIds(ids);
    }
}
