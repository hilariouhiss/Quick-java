package me.lhy.quick.validation.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import me.lhy.quick.validation.validator.SoftDeleteAuditConsistencyValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SoftDeleteAuditConsistencyValidator.class)
@Documented
public @interface ConsistentSoftDeleteAudit {
    String message() default "删除时间与删除人必须同时为空或同时非空";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
