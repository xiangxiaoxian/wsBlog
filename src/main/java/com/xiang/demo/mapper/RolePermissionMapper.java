package com.xiang.demo.mapper;

import com.xiang.demo.entity.RolePermission;
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
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    Long deleteByBatchIds(Collection<Long> batchIds);

    List<RolePermission> selectRolePermissionByBatchIds(Collection<Long> batchIds);
}
