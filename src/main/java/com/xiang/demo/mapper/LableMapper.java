package com.xiang.demo.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.demo.entity.Article;
import com.xiang.demo.entity.Lable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XR
 * @since 2021-01-07
 */
public interface LableMapper extends BaseMapper<Lable> {

    Page<Article> getArticlesBySortIdAndPage(Long id, Page page);
}
