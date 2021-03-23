package com.xiang.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.common.Result;
import com.xiang.demo.entity.Article;
import com.xiang.demo.entity.ArticleSort;
import com.xiang.demo.entity.Sort;
import com.xiang.demo.mapper.ArticleMapper;
import com.xiang.demo.mapper.ArticleSortMapper;
import com.xiang.demo.mapper.SortMapper;
import com.xiang.demo.service.SortService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 分类业务实现层
 *
 * @author XR
 * @since 2021-01-07
 */
@Service
public class SortServiceImpl extends ServiceImpl<SortMapper, Sort> implements SortService {

  @Resource private SortMapper sortMapper;

  @Resource private ArticleSortMapper articleSortMapper;

  @Resource private ArticleMapper articleMapper;

  // 新增或修改分类
  @Override
  @Transactional
  public Result insertOrUpdateSortById(Sort sort) {
    if (ObjectUtils.isEmpty(sort.getId())) { // 判断传递的实体类有没有id,没有进行新增操作
      sortMapper.insert(sort);
      return Result.success(200, "新增成功", sort);
    }
    sortMapper.updateById(sort);
    return Result.success(200, "编辑成功", sort);
  }

  // 根据id列表删除多个分类
  @Override
  @Transactional
  public Result deleteSortsByBatchIds(Collection<Long> batchIds) {
    // 查询列表中分类下是否有博文
    List<ArticleSort> articleSortList = articleSortMapper.selectArticleSortBySortBatchIds(batchIds);
    if (!ObjectUtils.isEmpty(articleSortList)) {
      return Result.error(400, "选中的分类下存在博文,无法删除");
    }
    return Result.success(200, "批量删除成功", sortMapper.deleteBatchIds(batchIds));
  }

  // 根据id删除单个分类
  @Override
  @Transactional
  public Result deleteSortById(Long id) {
    // 判断该分类下是否拥有文章
    QueryWrapper<ArticleSort> articleSortQueryWrapper = new QueryWrapper<>();
    articleSortQueryWrapper.eq("sort_id", id).eq("deleted", 0);
    List<ArticleSort> articleSortList = articleSortMapper.selectList(articleSortQueryWrapper);
    if (!ObjectUtils.isEmpty(articleSortList)) {
      return Result.error(400, "该分类下存在博文,无法删除");
    }
    return Result.success(200, "删除成功", sortMapper.deleteById(id));
  }

  // 查询所有分类并分页
  @Override
  public Result getAllSortsAndPages(Page page, String searchField) {
    QueryWrapper<Sort> queryWrapper = new QueryWrapper<>();
    queryWrapper.like("sort_name", searchField);
    return Result.success(200, "查询成功", sortMapper.selectPage(page, queryWrapper));
  }

  // 根据类别id查询当前分类下所有文章并进行分页
  @Override
  public Result getArticlesBySortIdAndPage(Long id, Page page) {
    Page<Article> articleList = articleMapper.getArticlesBySortIdAndPage(id, page);
    return Result.success(200, "success", articleList);
  }
}
