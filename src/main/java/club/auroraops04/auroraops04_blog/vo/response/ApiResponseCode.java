package club.auroraops04.auroraops04_blog.vo.response;

/**
 * @author AuroraOps04
 * @date 2021-08-31 14:07:32
 * @description 响应码
 */
public enum ApiResponseCode {
    SUCCESS(200, "OK"),
    CREATED(201, "Entity Created"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHENTICATED(401, "UNAUTHENTICATED"),
    FORBIDDEN(403, "FORBIDDEN"),
    NOTFOUND(404, "NOTFOUND"),
    SERVER_ERROR(500, "SERVER_ERROR"),
    BUSINESS_ERROR(999, "BUSINESS_ERROR")
    ;
    private Integer code;
    private String message;

    ApiResponseCode() {
    }

    ApiResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
