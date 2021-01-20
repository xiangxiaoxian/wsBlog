package com.xiang.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.common.Result;
import com.xiang.demo.entity.Role;
import com.xiang.demo.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * 角色控制层
 *
 * @author XR
 * @since 2021-01-07
 */
@Api(
    value = "RoleController",
    tags = {"角色相关接口"})
@RestController
@RequestMapping("/role")
public class RoleController {

  @Resource private RoleService roleService;

  @ApiOperation(value = "查询所有角色并分页")
  @PostMapping()
  public Result getAllRolesAndPages(@RequestBody Page page, @RequestParam String searchField) {
    return roleService.getAllRolesAndPages(page,searchField);
  }

  @ApiOperation(value = "新增或修改角色")
  @PutMapping
  public Result insertOrUpdateRoleById(@RequestBody Role role) {
    return roleService.insertOrUpdateRoleById(role);
  }

  @ApiOperation(value = "根据id删除单个角色")
  @DeleteMapping("{id}")
  public Result deleteRoleById(@PathVariable Long id) {
    return roleService.deleteRoleById(id);
  }

  @ApiOperation(value = "根据id列表删除多个角色")
  @DeleteMapping()
  public Result deleteRolesByBatchIds(@RequestBody Collection<Long> batchIds) {
    return roleService.deleteRolesByBatchIds(batchIds);
  }

  @ApiOperation(value = "根据角色id查询角色所拥有的权限")
  @GetMapping("{id}")
  public Result getRolePermissionByRoleId(@PathVariable Long id) {
    return roleService.getRolePermissionByRoleId(id);
  }

  @ApiOperation(value = "给角色分配权限")
  @PostMapping("/assignPermission/{roleId}")
  public Result assignPermissionByRoleIdAndPermissionId(
      @PathVariable Long roleId, @RequestBody Collection<Long> batchPermissionIds) {
    return roleService.assignPermissionByRoleIdAndPermissionId(roleId, batchPermissionIds);
  }
}
