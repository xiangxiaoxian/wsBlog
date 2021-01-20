package com.xiang.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.common.Result;
import com.xiang.demo.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiang.demo.entity.Lable;
import com.xiang.demo.entity.Sort;

/**
 * <p>
 *  文章
 * </p>
 *
 * @author XR
 * @since 2021-01-07
 */
public interface ArticleService extends IService<Article> {


    Result getAllArticlesAndPages(Page page,String searchField);

    Result getArticleByArticleId(Long id);

    Result insertOrUpdateArticleByArticleId(Article article, Sort sort, Lable lable);

    Result deleteArticleByArticleId(Long id);

    Result upStarOne(Long id);

    Result lowStar(Long id);
}
