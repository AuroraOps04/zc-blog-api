package club.auroraops04.auroraops04_blog.config.security;

import club.auroraops04.auroraops04_blog.vo.response.ApiResponse;
import club.auroraops04.auroraops04_blog.vo.response.ApiResponseCode;
import cn.hutool.json.JSONUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author AuroraOps04
 * @date 2021/9/14 15:19:07
 * @description 当访问接口没有权限时，自定义的返回结果
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(new ApiResponse<>(ApiResponseCode.FORBIDDEN)));
        response.getWriter().flush();
    }
}
