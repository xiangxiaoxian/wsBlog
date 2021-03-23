package com.xiang.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.common.Result;
import com.xiang.demo.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiang.demo.entity.ArticleStar;
import com.xiang.demo.entity.Lable;
import com.xiang.demo.entity.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

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

    Result insertOrUpdateArticleByArticleId(Map<String,Object> data);

    Result deleteArticleByArticleId(Long id);

    Result upStarOne(Long id, ArticleStar articleStar);

    Result lowStar(Long id,ArticleStar articleStar);

    Result getArticleByUserId(Page page,Long id);

    Result imgUpload(MultipartFile pic);

    Result starTrueOrFalse(Long id, Long userId);

    Result getArticleTop20();
}
