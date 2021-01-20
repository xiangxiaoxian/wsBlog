package com.xiang.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.common.Result;
import com.xiang.demo.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XR
 * @since 2021-01-07
 */
public interface PermissionService extends IService<Permission> {

    Result getAllPermissionsAndPages(Page page,String searchField);

    Result insertOrUpdatePermissionById(Permission permission);

    Result deletePermissionById(Long id);

    Result deletePermissionsByBatchIds(Collection<Long> batchIds);
}
