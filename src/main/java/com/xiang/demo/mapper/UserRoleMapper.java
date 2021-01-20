package com.xiang.demo.mapper;

import com.xiang.demo.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XR
 * @since 2021-01-07
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<UserRole> selectByBatchIds(Collection<Long> batchIds);

    Long deleteUserRoleByBatchIds(Collection<Long> batchIds);

}
