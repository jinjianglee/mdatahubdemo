package com.dh.datahub.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dh.datahub.entity.SosOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * SosOrdersnapshot Service
 *
 * @author Nevernow
 * @since 2020-09-08
 */
public interface ISosOrderService extends IService<SosOrder> {
    Page<SosOrder> getSosOrder(Map param, Page page);

    List<SosOrder> getSosOrdersByDate(String createdDate,String shopId);

}
