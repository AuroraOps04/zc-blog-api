package club.auroraops04.auroraops04_blog.core;

import club.auroraops04.auroraops04_blog.core.page.PageDomain;
import club.auroraops04.auroraops04_blog.core.page.TableSupport;
import club.auroraops04.auroraops04_blog.entities.User;
import club.auroraops04.auroraops04_blog.utils.SqlUtil;
import club.auroraops04.auroraops04_blog.utils.StringUtils;
import club.auroraops04.auroraops04_blog.vo.response.ApiPageResponse;
import club.auroraops04.auroraops04_blog.vo.response.ApiResponse;
import club.auroraops04.auroraops04_blog.vo.response.ApiResponseCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author AuroraOps04
 * @date 2021/9/14 20:03:24
 * @description 控制器基类
 */
public class BaseController {
    protected final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected ApiPageResponse getPageData(List<?> data){
        return new ApiPageResponse(
                ApiResponseCode.SUCCESS.getCode(),
                true,
                ApiResponseCode.SUCCESS.getMessage(),
                data,
                new PageInfo<>(data).getTotal()
        );
    }
    protected   ApiResponse<Object> success(){
        return new ApiResponse<>(ApiResponseCode.SUCCESS);
    }

    protected   ApiResponse<Object> success(boolean success){
        return new ApiResponse<>(ApiResponseCode.SUCCESS, success);
    }

    protected   ApiResponse<Object> success(Boolean success, String message){
        return new ApiResponse<>(ApiResponseCode.SUCCESS.getCode(), success, message);
    }

    protected   <T> ApiResponse<T> success(T data){
        return new ApiResponse<>(ApiResponseCode.SUCCESS, data);
    }

    protected   <T> ApiResponse<T> success(T data, Boolean success){
        return new ApiResponse<>(ApiResponseCode.SUCCESS, success, data);
    }

    protected   ApiResponse<Object> created(){
        return new ApiResponse<>(ApiResponseCode.CREATED);
    }

    protected   <T> ApiResponse<T> created(T data){
        return new ApiResponse<>(ApiResponseCode.CREATED, data);
    }
    protected   ApiResponse<Object> badRequest(){
        return new ApiResponse<>(ApiResponseCode.BAD_REQUEST, false);
    }
    protected   ApiResponse<Object> badRequest(String message){
        return new ApiResponse<>(ApiResponseCode.BAD_REQUEST.getCode(), false, message);
    }
}
