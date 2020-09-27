package com.dh.datahub.mapper;

import com.dh.datahub.entity.SosOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * SosOrdersnapshot Mybatis Mapper
 *
 * @author Nevernow
 * @since 2020-09-08
 */
public interface SosOrderMapper extends BaseMapper<SosOrder> {

    List<SosOrder> getSosOrder(Map map);

    int getSosOrderCount(Map map);

    List<SosOrder> getSosOrderByDateAndShop(String startDate,String endDate,String shopId);
}
