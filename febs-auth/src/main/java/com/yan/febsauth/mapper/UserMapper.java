
package com.yan.febsauth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yan.febscommon.entity.system.SystemUser;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febscommon.mapper
 * @Author: Yan
 * @CreateTime: 2020-04-30 09:23
 * @Description: 用户映射类
 */
public interface UserMapper extends BaseMapper<SystemUser> {
    SystemUser findByName(String username);
}
