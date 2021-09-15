package club.auroraops04.auroraops04_blog.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@TableName("permission")
@ApiModel("权限实体")
public class Permission implements Serializable {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 权限名
     */
    @ApiModelProperty("权限名")
    private String name;

    /**
     * 权限对应的访问路径

     */
    @ApiModelProperty("权限对应的路径")
    private String path;

    private static final long serialVersionUID = 1L;

    public Permission() {
    }

    public Permission(Long id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}