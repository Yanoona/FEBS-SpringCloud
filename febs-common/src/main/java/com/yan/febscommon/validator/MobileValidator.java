package com.yan.febscommon.validator;

import com.yan.febscommon.annotation.IsMobile;
import com.yan.febscommon.entity.RegexpConstant;
import com.yan.febscommon.util.FebsUtil;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febscommon.validator
 * @Author: Yan
 * @CreateTime: 2020-04-30 16:28
 * @Description: 校验手机号码
 */
public class MobileValidator implements ConstraintValidator<IsMobile,String> {
    @Override
    public void initialize(IsMobile isMobile) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (StringUtils.isBlank(s)) {
                return true;
            } else {
                String regex = RegexpConstant.MOBILE_REG;
                return FebsUtil.match(regex, s);
            }
        } catch (Exception e) {
            return false;
        }
    }
}
