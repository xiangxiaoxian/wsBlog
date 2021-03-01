package com.xiang.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.common.Result;
import com.xiang.demo.entity.Article;
import com.xiang.demo.entity.Lable;
import com.xiang.demo.entity.Sort;
import com.xiang.demo.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 文章
 *
 * @author XR
 * @since 2021-01-07
 */
@Api(
    value = "ArticleController",
    tags = {"文章相关接口"})
@RestController
@RequestMapping("/article")
public class ArticleController {

  @Resource private ArticleService articleService;

  @ApiOperation(value = "查询所有文章并分页,用于首页遍历")
  @PostMapping()
  public Result getAllArticlesAndPages(@RequestBody Page page, @RequestParam String searchField) {
    return articleService.getAllArticlesAndPages(page, searchField);
  }

  @ApiOperation(value = "查询单条文章并分页,用于文章详情")
  @GetMapping("{id}")
  public Result getArticleByArticleId(@PathVariable Long id) {
    return articleService.getArticleByArticleId(id);
  }

  @ApiOperation(value = "新增或修改文章")
  @PutMapping()
  public Result insertOrUpdateArticleByArticleId(@RequestBody Map<String, Object> data) {
    return articleService.insertOrUpdateArticleByArticleId(data);
  }

  @ApiOperation(value = "删除文章")
  @DeleteMapping("{id}")
  public Result deleteArticleByArticleId(@PathVariable Long id) {
    return articleService.deleteArticleByArticleId(id);
  }

  @ApiOperation(value = "文章点赞数加1")
  @GetMapping("/upStar/{id}")
  public Result upStarOne(@PathVariable Long id) {
    return articleService.upStarOne(id);
  }

  @ApiOperation(value = "文章点赞数减1")
  @GetMapping("/lowStar/{id}")
  public Result lowStar(@PathVariable Long id) {
    return articleService.lowStar(id);
  }
}
