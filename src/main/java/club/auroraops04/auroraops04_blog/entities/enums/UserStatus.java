package club.auroraops04.auroraops04_blog.entities.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserStatus implements IEnum<Integer> {
        NORMAL(0, "NORMAL"),
        DISABLED(1, "DISABLED"),
        INACTIVE(2, "INACTIVE");

        UserStatus(Integer code, String value) {
            this.code = code;
            this.description = value;
        }
        @JsonValue
        private final Integer code;
        private final String description;


    @Override
    public Integer getValue() {
        return code;
    }


    public String getDescription() {
        return description;
    }
}