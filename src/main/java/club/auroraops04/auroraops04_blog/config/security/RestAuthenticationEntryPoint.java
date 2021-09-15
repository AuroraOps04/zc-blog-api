package club.auroraops04.auroraops04_blog.config.security;

import club.auroraops04.auroraops04_blog.vo.response.ApiResponse;
import club.auroraops04.auroraops04_blog.vo.response.ApiResponseCode;
import cn.hutool.json.JSONUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author AuroraOps04
 * @date 2021/9/14 15:20:38
 * @description  当未登录或者token失效访问接口时，自定义的返回结果
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(new ApiResponse<>(ApiResponseCode.UNAUTHENTICATED)));
        response.getWriter().flush();
    }
}
