package club.auroraops04.auroraops04_blog.config.exception;

import club.auroraops04.auroraops04_blog.vo.response.ApiResponse;
import club.auroraops04.auroraops04_blog.vo.response.ApiResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author AuroraOps04
 * @date 2021/9/15 13:45:46
 * @description
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UsernameNotFoundException.class)
    public ApiResponse<Object> usernameNotFoundExceptionHandler(UsernameNotFoundException e){
        return new ApiResponse<>(ApiResponseCode.BAD_REQUEST.getCode(), false, e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ApiResponse<Object> businessExceptionHandler(BusinessException e){
        return new ApiResponse<>(ApiResponseCode.BUSINESS_ERROR.getCode(), false, StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : ApiResponseCode.BUSINESS_ERROR.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Object> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException e){
        List<String> errors = e.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return new ApiResponse<>(ApiResponseCode.BAD_REQUEST, errors);
    }

    /**
     * 处理MethodArgumentTypeMismatchException 异常,转换错误,说明路径错误,返回404
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiResponse<Object> methodArgumentTypeMismatchExceptionHandler(){
        return new ApiResponse<>(ApiResponseCode.NOTFOUND);
    }

    /**
     * 处理不支持的content-type异常
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ApiResponse<Object> httpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException e){
        LOGGER.warn(e.getMessage());
        return new ApiResponse<>(200, false, "Content-type不支持", null);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResponse<Object> HttpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e){
        return new ApiResponse<>(200, false, e.getMessage(), null);
    }

    /**
     * 处理sql语句语法错误异常
     * @param e 异常对象
     */
    @ExceptionHandler(BadSqlGrammarException.class)
    public ApiResponse<Object> badSqlGrammarExceptionHandler(BadSqlGrammarException e){
        LOGGER.warn(e.getMessage());
        return new ApiResponse<>(200, false, "sql 错误", null);
    }

    /**
     * 没有权限访问会抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ApiResponse<Object> accessDeniedExceptionHandler(AccessDeniedException e){
        return new ApiResponse<>(ApiResponseCode.FORBIDDEN,false);
    }

    /**
     * 处理其他所有异常
     * @param e 异常对象
     */
    @ExceptionHandler(Exception.class)
    public ApiResponse<Object> allExceptionHandler(Exception e){
        LOGGER.error(e.getMessage());
        return new ApiResponse<>(ApiResponseCode.SERVER_ERROR, false);
    }
}
