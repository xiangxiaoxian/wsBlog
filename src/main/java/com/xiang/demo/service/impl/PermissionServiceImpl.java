package com.xiang.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.common.Result;
import com.xiang.demo.entity.Permission;
import com.xiang.demo.entity.RolePermission;
import com.xiang.demo.mapper.PermissionMapper;
import com.xiang.demo.mapper.RolePermissionMapper;
import com.xiang.demo.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  权限逻辑实现类
 * </p>
 *
 * @author XR
 * @since 2021-01-07
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    //查询所有权限并分页
    @Override
    public Result getAllPermissionsAndPages(Page page,@RequestParam String searchField) {
        QueryWrapper<Permission> wrapperByPermission=new QueryWrapper<>();
        wrapperByPermission.like("permission_name",searchField);
        return Result.success(200,"查询成功",permissionMapper.selectMapsPage(page,wrapperByPermission));
    }

    //新增或修改权限
    @Override
    public Result insertOrUpdatePermissionById(Permission permission) {
        if (ObjectUtils.isEmpty(permission.getId())){//判断传递的实体id是否为空
            permissionMapper.insert(permission);
            return Result.success(200,"新增成功",permission);
        }
        permissionMapper.updateById(permission);
        return Result.success(200,"编辑成功",permission);
    }

    //根据id删除单个权限
    @Override
    public Result deletePermissionById(Long id) {
        //查询当前权限是否有角色拥有
        QueryWrapper<RolePermission> rolePermissionQueryWrapper=new QueryWrapper<>();
        rolePermissionQueryWrapper.eq("permission_id",id).eq("deleted",0);
        List<RolePermission> rolePermissionList = rolePermissionMapper.selectList(rolePermissionQueryWrapper);
        if (!ObjectUtils.isEmpty(rolePermissionList)){//权限分配列表有该权限
            return Result.error(400,"该权限已被分配给角色，无法进行删除");
        }
        return Result.success(200,"删除成功",permissionMapper.deleteById(id));
    }

    //根据di列表删除多个权限
    @Override
    public Result deletePermissionsByBatchIds(Collection<Long> batchIds) {
        //查询选择的权限中是否有被分配的权限
        List<RolePermission>  rolePermissionList=rolePermissionMapper.selectRolePermissionByBatchIds(batchIds);
        if (!ObjectUtils.isEmpty(rolePermissionList)){
            return Result.error(400,"选择的权限中有权限已经被分配，无法进行删除");
        }
        return Result.success(200,"删除成功",permissionMapper.deleteBatchIds(batchIds));
    }
}
