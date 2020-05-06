package com.yan.febsserversystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yan.febscommon.entity.system.SystemUser;
import feign.Param;
import org.springframework.data.domain.Page;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsserversystem.mapper
 * @Author: Yan
 * @CreateTime: 2020-04-30 15:31
 * @Description: 用户Dao层
 */
public interface UserMapper extends BaseMapper<SystemUser> {

    /**
     * 查找用户详细信息
     *
     * @param page 分页对象
     * @param user 用户对象，用于传递查询条件
     * @return Ipage
     */
    IPage<SystemUser> findUserDetailPage(Page page, @Param("user") SystemUser user);
}
