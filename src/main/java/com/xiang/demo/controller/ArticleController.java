package com.xiang.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.common.Result;
import com.xiang.demo.entity.Article;
import com.xiang.demo.entity.ArticleStar;
import com.xiang.demo.entity.Lable;
import com.xiang.demo.entity.Sort;
import com.xiang.demo.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
  public Result getAllArticlesAndPages(@RequestBody Page page, @RequestParam("searchField") String searchField) {
    return articleService.getAllArticlesAndPages(page, searchField);
  }

  @ApiOperation(value = "查询单条文章,用于文章详情")
  @GetMapping("{id}")
  public Result getArticleByArticleId(@PathVariable Long id) {
    return articleService.getArticleByArticleId(id);
  }

  @ApiOperation(value = "新增或修改文章")
  @RequiresAuthentication
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
  @PostMapping("/upStar/{id}")
  public Result upStarOne(@PathVariable Long id, @RequestBody ArticleStar articleStar) {
    return articleService.upStarOne(id,articleStar);
  }

  @ApiOperation(value = "文章点赞数减1")
  @PostMapping("/lowStar/{id}")
  public Result lowStar(@PathVariable Long id,@RequestBody ArticleStar articleStar) {
    return articleService.lowStar(id,articleStar);
  }

  @ApiOperation(value = "根据用户id查询文章")
  @PostMapping("/user/{id}")
  public Result getArticleByUserId(@PathVariable Long id,@RequestBody Page page) {
    return articleService.getArticleByUserId(page,id);
  }

  @ApiOperation(value = "文章内部图片上传")
  @PostMapping("/imgUpload")
  public Result imgUpload(@RequestParam(value = "pic", required = false) MultipartFile pic, HttpServletRequest request) {
    return articleService.imgUpload(pic,request);
  }

  @ApiOperation(value = "查询当前文章该用户是否点赞")
  @GetMapping("/starTrueOrFalse/{id}")
  public Result starTrueOrFalse(@PathVariable Long id,@RequestParam("userId") Long userId) {
    return articleService.starTrueOrFalse(id,userId);
  }

  @ApiOperation(value = "查询浏览量前20的文章不分页")
  @GetMapping()
  public Result getArticleTop20() {
    return articleService.getArticleTop20();
  }

}
