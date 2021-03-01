package com.xiang.demo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.common.Result;
import com.xiang.demo.entity.Sort;
import com.xiang.demo.service.SortService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * <p>
 *  分类控制
 * </p>
 *
 * @author XR
 * @since 2021-01-07
 */
@Api(value = "SortController",tags = {"分类相关接口"})
@RestController
@RequestMapping("/sort")
public class SortController {

    @Resource
    private SortService sortService;

    @ApiOperation(value = "查询所有分类并分页")
    @PostMapping()
    public Result getAllSortsAndPages(@RequestBody Page page, @RequestParam String searchField){
        return sortService.getAllSortsAndPages(page,searchField);
    }
    @ApiOperation(value = "查询所有分类不分页")
    @GetMapping()
    public Result getAllSort(){
        return Result.success(200,"查询成功",sortService.list());
    }
    @ApiOperation(value = "新增或修改分类")
    @PutMapping
    public Result insertOrUpdateSortById(@RequestBody Sort sort){
        return sortService.insertOrUpdateSortById(sort);
    }

    @ApiOperation(value = "根据id删除单个分类")
    @DeleteMapping("/{id}")
    public Result deleteSortById(@PathVariable Long id){
        return sortService.deleteSortById(id);
    }

    @ApiOperation(value = "根据id列表删除多个分类")
    @DeleteMapping()
    public Result deleteSortsByBatchIds(@RequestBody Collection<Long> batchIds){
        return sortService.deleteSortsByBatchIds(batchIds);
    }

    @ApiOperation(value = "根据类别id查询当前分类下所有文章并进行分页")
    @PostMapping("{id}")
    public Result getArticlesBySortIdAndPage(@PathVariable Long id,@RequestBody Page page){
        return sortService.getArticlesBySortIdAndPage(id,page);
    }

}
