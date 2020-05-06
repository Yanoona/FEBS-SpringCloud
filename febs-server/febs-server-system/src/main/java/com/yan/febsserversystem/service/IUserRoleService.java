package com.yan.febsserversystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yan.febscommon.entity.system.UserRole;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsserversystem.service
 * @Author: Yan
 * @CreateTime: 2020-04-30 15:49
 * @Description:
 */
public interface IUserRoleService extends IService<UserRole> {
    void deleteUserRolesByRoleId(String[] roleIds);

    void deleteUserRolesByUserId(String[] userIds);
}
