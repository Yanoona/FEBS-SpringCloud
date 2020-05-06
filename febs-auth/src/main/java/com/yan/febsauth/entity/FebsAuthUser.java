package com.yan.febsauth.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsauth.entity
 * @Author: Yan
 * @CreateTime: 2020-04-26 16:10
 * @Description: 用于装载登录时，从t_user表里查询出来的数据。
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FebsAuthUser extends User implements Serializable {
    private static final long serialVersionUID = -1748289340320186418L;

    private Long userId;

    private String avatar;

    private String email;

    private String mobile;

    private String sex;

    private Long deptId;

    private String deptName;

    private String roleId;

    private String roleName;

    private Date lastLoginTime;

    private String description;

    private String status;

    public FebsAuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public FebsAuthUser(String username, String password, boolean enabled, boolean accountNonExpired,
                        boolean credentialsNonExpired, boolean accountNonLocked,
                        Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
