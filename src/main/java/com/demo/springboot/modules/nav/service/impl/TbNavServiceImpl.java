package com.demo.springboot.modules.nav.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.springboot.modules.nav.model.TbNav;
import com.demo.springboot.modules.nav.mapper.TbNavMapper;
import com.demo.springboot.modules.nav.service.TbNavService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 收藏导航表 服务实现类
 * </p>
 *
 * @author macro
 * @since 2023-08-09
 */
@Service
public class TbNavServiceImpl extends ServiceImpl<TbNavMapper, TbNav> implements TbNavService {

    @Override
    public boolean create(TbNav tbNav) {
        tbNav.setCreateTime(new Date());
        return save(tbNav);
    }

    @Override
    public boolean update(Long id, TbNav tbNav) {
        tbNav.setId(id);
        return updateById(tbNav);
    }

    @Override
    public Page<TbNav> list(Long parentId, Integer pageSize, Integer pageNum) {
        Page<TbNav> page = new Page<>(pageNum,pageSize);
        QueryWrapper<TbNav> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(TbNav::getSort,parentId)
                .orderByDesc(TbNav::getSort);
        return page(page,wrapper);
    }
}
