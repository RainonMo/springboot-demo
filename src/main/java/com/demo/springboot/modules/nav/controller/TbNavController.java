package com.demo.springboot.modules.nav.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.springboot.common.CommonPage;
import com.demo.springboot.common.CommonResult;
import com.demo.springboot.modules.nav.model.TbNav;
import com.demo.springboot.modules.nav.service.TbNavService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 收藏导航表 前端控制器
 * </p>
 *
 * @author macro
 * @since 2023-08-09
 */
@RestController
@RequestMapping("/nav")
public class TbNavController {

    @Autowired
    private TbNavService tbNavService;

    @ApiOperation("添加导航")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody TbNav tbNav) {
        boolean success = tbNavService.create(tbNav);
        if (success) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改导航")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id,
                               @RequestBody TbNav tbNav) {
        boolean success = tbNavService.update(id, tbNav);
        if (success) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据ID获取导航详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<TbNav> getItem(@PathVariable Long id) {
        TbNav tbNav = tbNavService.getById(id);
        return CommonResult.success(tbNav);
    }

    @ApiOperation("根据ID删除导航")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        boolean success = tbNavService.removeById(id);
        if (success) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("分页查询导航")
    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<TbNav>> list(@PathVariable Long parentId,
                                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<TbNav> menuList = tbNavService.list(parentId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(menuList));
    }



}

