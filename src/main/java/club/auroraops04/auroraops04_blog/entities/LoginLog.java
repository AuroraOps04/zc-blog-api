package club.auroraops04.auroraops04_blog.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@TableName("login_log")
public class LoginLog implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 登录的用户id
     */
    private Long userId;

    /**
     * 登录时间
     */
    private Date time;

    /**
     * 登录ip
     */
    private String ip;

    /**
     * 登录地点
     */
    private String place;

    /**
     * 登录的设备和浏览器
     */
    private String device;

    private static final long serialVersionUID = 1L;

    public LoginLog() {
    }

    public LoginLog(Long id, Long userId, Date time, String ip, String place, String device) {
        this.id = id;
        this.userId = userId;
        this.time = time;
        this.ip = ip;
        this.place = place;
        this.device = device;
    }

    @Override
    public String toString() {
        return "LoginLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", time=" + time +
                ", ip='" + ip + '\'' +
                ", place='" + place + '\'' +
                ", device='" + device + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}