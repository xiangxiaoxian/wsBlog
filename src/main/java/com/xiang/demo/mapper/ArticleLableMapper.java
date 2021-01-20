package com.xiang.demo.mapper;

import com.xiang.demo.entity.ArticleLable;
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
public interface ArticleLableMapper extends BaseMapper<ArticleLable> {

    List<ArticleLable> selectArticlesByBatchIds(Collection<Long> batchIds);

}
