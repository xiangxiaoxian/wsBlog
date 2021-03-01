package com.xiang.demo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.common.Result;
import com.xiang.demo.entity.Lable;
import com.xiang.demo.service.LableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * <p>
 *  标签控制层
 * </p>
 *
 * @author XR
 * @since 2021-01-07
 */
@Api(value = "LableController",tags = {"标签相关接口"})
@RestController
@RequestMapping("/lable")
public class LableController {

    @Resource
    private LableService lableService;

    @ApiOperation(value = "查询所有标签并分页")
    @PostMapping()
    public Result getAllLablesAndPages(@RequestBody Page page,@RequestParam String searchField){
        return lableService.getAllLablesAndPages(page,searchField);
    }

    @ApiOperation(value = "查询所有标签不分页")
    @GetMapping()
    public Result getAllLables(){
        return Result.success(200,"查询成功",lableService.list());
    }

    @ApiOperation(value = "新增或修改标签")
    @PutMapping
    public Result insertOrUpdateLableById(@RequestBody Lable lable){
        return lableService.insertOrUpdateLableById(lable);
    }

    @ApiOperation(value = "根据id删除单个标签")
    @DeleteMapping("/{id}")
    public Result deleteLableById(@PathVariable Long id){
        return lableService.deleteLableById(id);
    }

    @ApiOperation(value = "根据id列表删除多个标签")
    @DeleteMapping()
    public Result deleteLablesByBatchIds(@RequestBody Collection<Long> batchIds){
        return lableService.deleteLablesByBatchIds(batchIds);
    }

    @ApiOperation(value = "根据类别id查询当前标签下所有文章并进行分页")
    @PostMapping("{id}")
    public Result getArticlesByLableIdAndPage(@PathVariable Long id,@RequestBody Page page){
        return lableService.getArticlesByLableIdAndPage(id,page);
    }

}
