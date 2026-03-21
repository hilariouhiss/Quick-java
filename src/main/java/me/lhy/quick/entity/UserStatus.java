package me.lhy.quick.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    DISABLED(0),
    ACTIVE(1),
    LOCKED(2);

    @EnumValue
    private final int code;
}
