package com.yan.febscommon.annotation;

import com.yan.febscommon.validator.MobileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febscommon.annotation
 * @Author: Yan
 * @CreateTime: 2020-04-30 16:27
 * @Description: 校验手机号码
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MobileValidator.class)
public @interface IsMobile {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
