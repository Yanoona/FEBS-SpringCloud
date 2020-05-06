package com.yan.febsauth.service;

import com.yan.febsauth.entity.FebsAuthUser;
import com.yan.febsauth.manager.UserManager;
import com.yan.febscommon.entity.system.SystemUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsauth.service
 * @Author: Yan
 * @CreateTime: 2020-04-26 16:06
 * @Description:
 */
@Service
public class FebsUserDetailService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserManager userManager;

    /**
     * 本来获取用户信息等逻辑应该是在febs-server-system模块实现的，
     * febs-auth模块通过Feign来远程调用服务，以完成登录逻辑。
     * 但是考虑到这样做的话，febs-auth和febs-server-system就强耦合在一起了，
     * 所以最终还是决定在febs-auth内部完成用户信息查询和用户权限查询。
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser systemUser = userManager.findByName(username);
        if (systemUser != null) {
            String permissions = userManager.findUserPermissions(systemUser.getUsername());
            boolean notLocked = false;
            if (StringUtils.equals(SystemUser.STATUS_VALID, systemUser.getStatus()))
                notLocked = true;
            FebsAuthUser authUser = new FebsAuthUser(systemUser.getUsername(), systemUser.getPassword(),
                    true, true, true, notLocked,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
//            将authUser实体类中的值复制给systemUser，避免一个一个set
            BeanUtils.copyProperties(systemUser,authUser);
            return authUser;
        } else {
            throw new UsernameNotFoundException("");
        }
    }
}
