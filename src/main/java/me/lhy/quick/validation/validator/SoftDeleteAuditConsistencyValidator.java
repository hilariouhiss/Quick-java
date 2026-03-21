package me.lhy.quick.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import me.lhy.quick.validation.constraint.ConsistentSoftDeleteAudit;

import java.lang.reflect.Field;

public class SoftDeleteAuditConsistencyValidator implements ConstraintValidator<ConsistentSoftDeleteAudit, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        try {
            Field deletedAtField = value.getClass().getDeclaredField("deletedAt");
            Field deletedByField = value.getClass().getDeclaredField("deletedBy");
            deletedAtField.setAccessible(true);
            deletedByField.setAccessible(true);
            Object deletedAt = deletedAtField.get(value);
            Object deletedBy = deletedByField.get(value);
            return (deletedAt == null) == (deletedBy == null);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            return true;
        }
    }
}
