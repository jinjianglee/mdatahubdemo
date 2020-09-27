package com.dh.datahub.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dh.datahub.entity.SosOrder;
import com.dh.datahub.mapper.SosOrderMapper;
import com.dh.datahub.service.ISosOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * SosOrdersnapshot Service Implements
 *
 * @author Nevernow
 * @since 2020-09-08
 */
@Service
public class SosOrderServiceImpl extends ServiceImpl<SosOrderMapper, SosOrder> implements ISosOrderService {
    @Autowired
    SosOrderMapper mapper;
    /**
     * log Object
     */
    private static Logger log = LoggerFactory.getLogger(SosOrderServiceImpl.class);

    @Override
    public Page<SosOrder> getSosOrder(Map param, Page page) {
        param.put("current", (page.getCurrent() - 1) * page.getSize());
        param.put("pageSize", page.getSize());
        page.setRecords(baseMapper.getSosOrder(param));
        page.setTotal(baseMapper.getSosOrderCount(param));
        page.setPages((page.getTotal() / page.getSize()) - 1);
        return page;
    }

    @Override
    public List<SosOrder> getSosOrdersByDate(String createdDate, String shopId) {
        return baseMapper.getSosOrderByDateAndShop(createdDate+" 00:00:00",createdDate+" 23:59:59",shopId);
    }


}
