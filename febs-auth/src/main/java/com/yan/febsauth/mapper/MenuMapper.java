package com.yan.febsauth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yan.febscommon.entity.system.Menu;

import java.util.List;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febscommon.mapper
 * @Author: Yan
 * @CreateTime: 2020-04-30 09:23
 * @Description: 菜单映射类
 */
public interface MenuMapper extends BaseMapper<MenuMapper> {
    List<Menu> findUserPermissions(String username);
}
