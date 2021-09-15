package club.auroraops04.auroraops04_blog.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author AuroraOps04
 * @date 2021-08-31 14:04:21
 * @description 统一响应体封装
 */
@ApiModel("统一响应体")
public class ApiResponse<T> {
    @ApiModelProperty("响应码")
    private Integer code;
    @ApiModelProperty("是否成功")
    private Boolean success;
    @ApiModelProperty("提示消息")
    private String msg;
    @ApiModelProperty("响应数据")
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(ApiResponseCode responseCode){
        this.code = responseCode.getCode();
        this.success = true;
        this.msg = responseCode.getMessage();
    }

    public ApiResponse(ApiResponseCode responseCode, Boolean success){
        this.code = responseCode.getCode();
        this.msg = responseCode.getMessage();
        this.success = success;
    }

    public ApiResponse(ApiResponseCode responseCode, T data){
        this.code = responseCode.getCode();
        this.msg = responseCode.getMessage();
        this.success = true;
        this.data = data;
    }

    public ApiResponse(ApiResponseCode responseCode, Boolean success, T data){
        this.code = responseCode.getCode();
        this.msg = responseCode.getMessage();
        this.success = success;
        this.data = data;
    }

    public ApiResponse(Integer code, Boolean success, String msg) {
        this.code = code;
        this.success = success;
        this.msg = msg;
    }

    public ApiResponse(Integer code, Boolean success, String msg, T data) {
        this.code = code;
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", success=" + success +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
