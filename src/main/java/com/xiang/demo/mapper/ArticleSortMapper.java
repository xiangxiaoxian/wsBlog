package com.xiang.demo.mapper;

import com.xiang.demo.entity.ArticleSort;
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
public interface ArticleSortMapper extends BaseMapper<ArticleSort> {

    List<ArticleSort> selectArticleSortBySortBatchIds(Collection<Long> batchIds);
}
