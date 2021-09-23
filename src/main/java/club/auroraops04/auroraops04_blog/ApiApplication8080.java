package club.auroraops04.auroraops04_blog;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * @author AuroraOps04
 * @date 2021/9/14 13:23:35
 * @description
 */

@SpringBootApplication
@MapperScan(basePackages = "club.auroraops04.auroraops04_blog.mapper")
public class ApiApplication8080 extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication8080.class, args);
    }

    // 配置war包
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ApiApplication8080.class);
    }

    /**
     * 配置枚举类序列化到数据库的问题
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer(){
        return builder -> builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    }
}
