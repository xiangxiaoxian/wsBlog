package com.xiang.demo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.common.Result;
import com.xiang.demo.entity.Permission;
import com.xiang.demo.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * <p>
 *  权限控制器
 * </p>
 *
 * @author XR
 * @since 2021-01-07
 */
@Api(value = "PermissionController",tags = {"权限相关接口"})
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @ApiOperation(value = "查询所有权限并分页")
    @PostMapping()
    public Result getAllPermissionsAndPages(@RequestBody Page page,@RequestParam String searchField){
        return permissionService.getAllPermissionsAndPages(page,searchField);
    }

    @ApiOperation(value = "新增或修改权限")
    @PutMapping
    public Result insertOrUpdatePermissionById(@RequestBody Permission permission){
        return permissionService.insertOrUpdatePermissionById(permission);
    }

    @ApiOperation(value = "根据id删除单个权限")
    @DeleteMapping("/{id}")
    public Result deletePermissionById(@PathVariable Long id){
        return permissionService.deletePermissionById(id);
    }

    @ApiOperation(value = "根据id列表删除多个权限")
    @DeleteMapping()
    public Result deletePermissionsByBatchIds(@RequestBody Collection<Long> batchIds){
        return permissionService.deletePermissionsByBatchIds(batchIds);
    }
}
