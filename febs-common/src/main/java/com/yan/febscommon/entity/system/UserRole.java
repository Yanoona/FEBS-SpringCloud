package com.yan.febscommon.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febscommon.entity.system
 * @Author: Yan
 * @CreateTime: 2020-04-30 15:45
 * @Description:
 */
@Data
@TableName(value = "t_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 9192830580876002012L;

    @TableField(value = "USER_ID")
    private Long userId;

    @TableField(value = "ROLE_ID")
    private Long roleId;
}
