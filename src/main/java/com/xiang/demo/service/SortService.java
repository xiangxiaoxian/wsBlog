package com.xiang.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.common.Result;
import com.xiang.demo.entity.Sort;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

/**
 * 服务类
 *
 * @author XR
 * @since 2021-01-07
 */
public interface SortService extends IService<Sort> {

  Result getAllSortsAndPages(Page page, String searchField);

  Result insertOrUpdateSortById(Sort sort);

  Result deleteSortById(Long id);

  Result deleteSortsByBatchIds(Collection<Long> batchIds);

  Result getArticlesBySortIdAndPage(Long id, Page page);
}
