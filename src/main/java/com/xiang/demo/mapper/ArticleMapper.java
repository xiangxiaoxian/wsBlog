package com.xiang.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.demo.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章 Mapper 接口
 * </p>
 *
 * @author XR
 * @since 2021-01-07
 */
public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> selectArticleByUserBatchIds(Collection<Long> batchIds);

    Page<Article> getAllArticlesAndPages(Page page,@Param(Constants.WRAPPER) QueryWrapper<Article> wrapper);

    Article getArticleByArticleId(Long id);

    void upBrowseOne(Long id);

    void upStarOne(Long id);

    void lowStar(Long id);

    void upCommentsByArticleId(Long articleId);

    void lowCommentsByArticleId(Long articleId);

    Page<Article> getArticleByUserId(Page page,@Param(Constants.WRAPPER) QueryWrapper<Article> articleQueryWrapper);
}
