package club.auroraops04.auroraops04_blog.entities;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserStatus {
        NORMAL(0, "NORMAL"),
        DISABLED(1, "DISABLED"),
        INACTIVE(2, "INACTIVE");
        @JsonCreator
        UserStatus(Integer code, String value) {
            this.code = code;
            this.value = value;
        }

        @EnumValue
        private final Integer code;
        @JsonValue
        private final String value;

    }