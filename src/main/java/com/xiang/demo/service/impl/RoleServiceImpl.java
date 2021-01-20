package com.xiang.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.common.Result;
import com.xiang.demo.entity.Permission;
import com.xiang.demo.entity.Role;
import com.xiang.demo.entity.RolePermission;
import com.xiang.demo.entity.UserRole;
import com.xiang.demo.mapper.ArticleSortMapper;
import com.xiang.demo.mapper.RoleMapper;
import com.xiang.demo.mapper.RolePermissionMapper;
import com.xiang.demo.mapper.UserRoleMapper;
import com.xiang.demo.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 服务实现类
 *
 * @author XR
 * @since 2021-01-07
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

  @Resource private RoleMapper roleMapper;
  @Resource private UserRoleMapper userRoleMapper;
  @Resource private RolePermissionMapper rolePermissionMapper;

  // 查询所有角色并进行分页
  @Override
  public Result getAllRolesAndPages(Page page, String searchField) {
    QueryWrapper<Role> wrapper = new QueryWrapper<>();
    wrapper.like("role_name",searchField);
    return Result.success(200, "查询成功", roleMapper.selectMapsPage(page, wrapper));
  }

  // 新增或修改角色信息
  @Transactional
  @Override
  public Result insertOrUpdateRoleById(Role role) {
    if (ObjectUtils.isEmpty(role.getId())) { // 判断传递实体类中是否有id
      roleMapper.insert(role);
      return Result.success(200, "新增成功", role);
    }
    roleMapper.updateById(role);
    return Result.success(200, "编辑成功", role);
  }

  // 根据id删除角色
  @Transactional
  @Override
  public Result deleteRoleById(Long id) {
    // 删除之前先判断改角色下是否否账号存在
    QueryWrapper<UserRole> wrapperByUserRole = new QueryWrapper<>();
    wrapperByUserRole.eq("role_id", id).eq("deleted", 0);
    List<UserRole> userRoles = userRoleMapper.selectList(wrapperByUserRole);
    if (!ObjectUtils.isEmpty(userRoles)) {
      return Result.error(400, "该角色下存在账号，无法删除");
    }
    // 删除该角色拥有的权限
    QueryWrapper<RolePermission> wrapperByRolePermission = new QueryWrapper<>();
    wrapperByRolePermission.eq("role_id", id);
    rolePermissionMapper.delete(wrapperByRolePermission);
    return Result.success(200, "删除成功", roleMapper.deleteById(id));
  }

  // 根据id列表批量删除角色
  @Transactional
  @Override
  public Result deleteRolesByBatchIds(Collection<Long> batchIds) {
    // 删除之前先判断改角色下是否否账号存在
    List<UserRole> userRoles = userRoleMapper.selectByBatchIds(batchIds);
    if (!ObjectUtils.isEmpty(userRoles)) {
      return Result.error(400, "选择的角色下存在账号，无法删除");
    }
    // 删除该角色拥有的权限
    Long length = rolePermissionMapper.deleteByBatchIds(batchIds);
    return Result.success(200, "批量删除成功", roleMapper.deleteBatchIds(batchIds));
  }

  // 根据角色id查询角色所拥有的权限
  @Override
  public Result getRolePermissionByRoleId(Long id) {
    return Result.success(200, "查找成功", roleMapper.getRolePermissionByRoleId(id));
  }

  // 给角色分配权限
  @Transactional
  @Override
  public Result assignPermissionByRoleIdAndPermissionId(
      Long roleId, Collection<Long> batchPermissionIds) {
    // 分配权限之前先删除之前已经拥有的权限
    QueryWrapper<RolePermission> rolePermissionWrapper = new QueryWrapper<>();
    rolePermissionWrapper.eq("role_id", roleId);
    rolePermissionMapper.delete(rolePermissionWrapper);
    // 给角色分配新的权限
    RolePermission rolePermission = new RolePermission();
    rolePermission.setRoleId(roleId);
    for (Long PermissionId : batchPermissionIds) {
      rolePermission.setPermissionId(PermissionId);
      rolePermissionMapper.insert(rolePermission);
    }
    return Result.success(200, "分配成功", null);
  }
}
