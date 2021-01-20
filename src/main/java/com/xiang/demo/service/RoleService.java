package com.xiang.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.common.Result;
import com.xiang.demo.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

/**
 * 服务类
 *
 * @author XR
 * @since 2021-01-07
 */
public interface RoleService extends IService<Role> {

  Result getAllRolesAndPages(Page page, String searchField);

  Result insertOrUpdateRoleById(Role role);

  Result deleteRoleById(Long id);

  Result deleteRolesByBatchIds(Collection<Long> batchIds);

  Result getRolePermissionByRoleId(Long id);

  Result assignPermissionByRoleIdAndPermissionId(Long roleId, Collection<Long> batchPermissionIds);
}
