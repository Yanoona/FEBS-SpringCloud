package com.yan.febsauth.manager;

import com.yan.febsauth.mapper.MenuMapper;
import com.yan.febsauth.mapper.UserMapper;
import com.yan.febscommon.entity.system.Menu;
import com.yan.febscommon.entity.system.SystemUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsauth.manager
 * @Author: Yan
 * @CreateTime: 2020-04-30 09:36
 * @Description: 用于统一定义和用户相关的业务方法
 */
@Service
public class UserManager {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    public SystemUser findByName(String username) {
        return userMapper.findByName(username);
    }

    /**
     * 该方法返回的是用户权限集合以逗号拼接后的值，如user:add,user:update,user:delete
     * @param username
     * @return 如user:add,user:update,user:delete
     */
    public String findUserPermissions(String username) {
        List<Menu> userPermissions = menuMapper.findUserPermissions(username);
        return userPermissions.stream().map(Menu::getPerms).collect(Collectors.joining(","));
    }
}
