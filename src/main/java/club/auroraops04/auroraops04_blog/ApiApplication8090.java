package club.auroraops04.auroraops04_blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author AuroraOps04
 * @date 2021/9/14 13:23:35
 * @description
 */

@SpringBootApplication
@MapperScan(basePackages = "club.auroraops04.auroraops04_blog.mapper")
public class ApiApplication8090 {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication8090.class, args);
    }
}
