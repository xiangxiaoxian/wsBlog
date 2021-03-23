package com.xiang.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.common.Result;
import com.xiang.demo.entity.Notice;
import com.xiang.demo.entity.Sort;
import com.xiang.demo.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author XR
 * @since 2021-03-22
 */
@Api(
        value = "ArticleController",
        tags = {"公告相关接口"})
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Resource
    private NoticeService noticeService;

    @ApiOperation(value = "查询所有公告不分页")
    @GetMapping()
    public Result getAllNotice(){
        QueryWrapper<Notice> noticeQueryWrapper=new QueryWrapper<>();
        noticeQueryWrapper.orderByDesc("id");
        return Result.success(200,"查询成功",noticeService.list(noticeQueryWrapper));
    }
    @ApiOperation(value = "新增或修改公告")
    @PutMapping
    public Result insertOrUpdateNoticeById(@RequestBody Notice notice){
        return noticeService.insertOrUpdateNoticeById(notice);
    }

    @ApiOperation(value = "根据id删除单个公告")
    @DeleteMapping("/{id}")
    public Result deleteNoticeById(@PathVariable Long id){
        return noticeService.deleteNoticeById(id);
    }

    @ApiOperation(value = "根据id列表删除多个公告")
    @DeleteMapping()
    public Result deleteNoticesByBatchIds(@RequestBody Collection<Long> batchIds){
        return noticeService.deleteNoticesByBatchIds(batchIds);
    }

}
