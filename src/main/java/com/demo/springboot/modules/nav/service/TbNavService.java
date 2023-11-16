package com.demo.springboot.modules.nav.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.springboot.modules.nav.model.TbNav;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 收藏导航表 服务类
 * </p>
 *
 * @author macro
 * @since 2023-08-09
 */
public interface TbNavService extends IService<TbNav> {

    boolean create(TbNav tbNav);

    boolean update(Long id, TbNav tbNav);

    Page<TbNav> list(Long parentId, Integer pageSize, Integer pageNum);
}
