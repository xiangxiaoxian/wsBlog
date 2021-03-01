package com.xiang.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.common.Result;
import com.xiang.demo.entity.Lable;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XR
 * @since 2021-01-07
 */
public interface LableService extends IService<Lable> {

    Result getAllLablesAndPages(Page page,String searchField);

    Result insertOrUpdateLableById(Lable lable);

    Result deleteLableById(Long id);

    Result deleteLablesByBatchIds(Collection<Long> batchIds);

    Result getArticlesByLableIdAndPage(Long id, Page page);

}
