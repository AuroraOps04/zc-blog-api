package club.auroraops04.auroraops04_blog.vo.response;

import io.swagger.annotations.ApiModel;

/**
 * @author AuroraOps04
 * @date 2021-09-02 10:13:07
 * @description
 */
@ApiModel("分页数据对象")
public class ApiPageResponse<T> extends ApiResponse<T> {
    private static final long serialVersionUID = 1L;
    /** 总记录数 */
    private long total;

    public ApiPageResponse() {
    }

    public ApiPageResponse(Integer code, Boolean success, String msg, T data, long total) {
        super(code, success, msg, data);
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
