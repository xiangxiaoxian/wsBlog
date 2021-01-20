package com.xiang.demo.mapper;

import com.xiang.demo.entity.Permission;
import com.xiang.demo.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XR
 * @since 2021-01-07
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Permission> getRolePermissionByRoleId(Long id);
}
