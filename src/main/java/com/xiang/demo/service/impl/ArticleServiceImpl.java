package com.xiang.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.common.Result;
import com.xiang.demo.entity.*;
import com.xiang.demo.mapper.*;
import com.xiang.demo.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * 文章
 *
 * @author XR
 * @since 2021-01-07
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService {

  @Resource private ArticleMapper articleMapper;

  @Resource private CommentsMapper commentsMapper;

  @Resource private ArticleSortMapper articleSortMapper;

  @Resource private ArticleLableMapper articleLableMapper;

  // 查询所有文章并分页
  @Override
  public Result getAllArticlesAndPages(Page page, String searchField) {
    QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
    articleQueryWrapper.like("title", searchField).eq("deleted",0);
    Page<Article> articlePage = articleMapper.getAllArticlesAndPages(page, articleQueryWrapper);
    return Result.success(200, "查询成功", articlePage);
  }

  // 查询单条文章，用于文章详情
  @Override
  public Result getArticleByArticleId(Long id) {
    //对浏览记录加1
    articleMapper.upBrowseOne(id);
    return Result.success(200, "成功", articleMapper.getArticleByArticleId(id));
  }

  // 新增或修改文章
  @Override
  public Result insertOrUpdateArticleByArticleId(Map<String,Object> data) {
    ArticleSort articleSort = new ArticleSort();
    ArticleLable articleLable = new ArticleLable();
    Article article= JSON.parseObject(JSON.toJSONString(data.get("article")),Article.class);
    Long sortId=Long.valueOf(data.get("sortId").toString());
    Long lableId=Long.valueOf(data.get("lableId").toString());
    System.out.println(lableId);
    if (ObjectUtils.isEmpty(article.getId())) { // 判断传递的实体id是否为空
      article.setPubTime(new Date());
      articleMapper.insert(article);
      // 查询刚插入的数据
      QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
      articleQueryWrapper.eq("user_id", article.getUserId()).eq("title", article.getTitle()).eq("content",article.getContent());
      Article article1 = articleMapper.selectOne(articleQueryWrapper);
      // 插入分类表
      articleSort.setArticleId(article1.getId());
      articleSort.setSortId(sortId);
      articleSortMapper.insert(articleSort);
      // 插入标签表
      articleLable.setArticleId(article1.getId());
      articleLable.setLableId(lableId);
      articleLableMapper.insert(articleLable);
      return Result.success(200, "发布成功", article);
    }
    // 修改分类表
    // 删除之前的分类,标签
    QueryWrapper<ArticleSort> articleSortQueryWrapper = new QueryWrapper<>();
    articleSortQueryWrapper.eq("article_id", article.getId());
    articleSortMapper.delete(articleSortQueryWrapper);
    // 插入新的分类
    articleSort.setArticleId(article.getId());
    articleSort.setSortId(sortId);
    articleSortMapper.insert(articleSort);
    //删除之前的标签
    QueryWrapper<ArticleLable> articleLableQueryWrapper=new QueryWrapper<>();
    articleLableQueryWrapper.eq("article_id", article.getId());
    articleLableMapper.delete(articleLableQueryWrapper);
    // 插入标签表
    articleLable.setArticleId(article.getId());
    articleLable.setLableId(lableId);
    articleLableMapper.insert(articleLable);
    return Result.success(200, "修改成功", articleMapper.updateById(article));
  }

  // 删除单条文章
  @Override
  public Result deleteArticleByArticleId(Long id) {
    // 先删除该文章下的评论以及分类和标签相关
    // 删除评论
    QueryWrapper<Comments> commentsQueryWrapper = new QueryWrapper<>();
    commentsQueryWrapper.eq("article_id", id);
    commentsMapper.delete(commentsQueryWrapper);
    // 删除分类
    QueryWrapper<ArticleSort> articleSortQueryWrapper = new QueryWrapper<>();
    articleSortQueryWrapper.eq("article_id", id);
    articleSortMapper.delete(articleSortQueryWrapper);
    // 删除标签
    QueryWrapper<ArticleLable> articleLableQueryWrapper = new QueryWrapper<>();
    articleLableQueryWrapper.eq("article_id", id);
    articleLableMapper.delete(articleLableQueryWrapper);
    // 删除文章
    articleMapper.deleteById(id);
    return Result.success(200, "删除成功", null);
  }

  //点赞数加1
  @Override
  public Result upStarOne(Long id) {
    articleMapper.upStarOne(id);
    return Result.success(200,"success",null);
  }

  //点赞数减1
  @Override
  public Result lowStar(Long id) {
    articleMapper.lowStar(id);
    return Result.success(200,"success",null);
  }

  //根据用户id查询文章
  @Override
  public Result getArticleByUserId(Page page,Long id) {
    QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
    articleQueryWrapper.eq("user_id", id).eq("a.deleted",0);
    Page<Article> articlePage = articleMapper.getArticleByUserId(page, articleQueryWrapper);
    return Result.success(200,"查询成功",articlePage);
  }
}
