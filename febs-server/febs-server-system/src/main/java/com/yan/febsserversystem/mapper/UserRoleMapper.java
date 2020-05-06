package com.yan.febsserversystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yan.febscommon.entity.system.UserRole;
import feign.Param;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsserversystem.mapper
 * @Author: Yan
 * @CreateTime: 2020-04-30 15:47
 * @Description:
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {
    /**
     * 根据用户Id删除该用户的角色关系
     *
     * @param userId 用户 ID
     * @return boolean
     */
    Boolean deleteByUserId(@Param("userId") Long userId);

    /**
     * 根据角色Id删除该角色的用户关系
     *
     * @param roleId 角色 ID
     * @return boolean
     */
    Boolean deleteByRoleId(@Param("roleId") Long roleId);
}
