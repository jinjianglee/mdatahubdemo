package com.dh.datahub.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.dh.common.basic.*;
import com.dh.datahub.entity.SosOrder;
import java.util.Map;
import com.dh.datahub.service.ISosOrderService;

/**
 *
 * @author Nevernow
 * @since 2020-09-08
 */
@RestController
@RequestMapping("/datahub/apiSosOrder")
public class SosOrderController{
    /**
     * log Object
     */
    private static Logger log = LoggerFactory.getLogger(SosOrderController.class);

    @Autowired
    ISosOrderService service;

    @RequestMapping(value = "/insertSosOrder", method = RequestMethod.POST)
    public BaseResponse insertSosOrder(@RequestBody SosOrder data) {
        return BaseResponse.run(() -> service.save(data));
    }

    @RequestMapping(value = "/updateSosOrder", method = RequestMethod.POST)
    public BaseResponse updateSosOrder(@RequestBody SosOrder data) {
        return BaseResponse.run(() -> service.updateById(data));
    }

    @RequestMapping(value = "/deleteSosOrder", method = RequestMethod.GET)
    public BaseResponse deleteSosOrder(String id) {
        return BaseResponse.run(() -> service.removeById(id));
    }

    @RequestMapping(value = "/getSosOrder", method = RequestMethod.POST)
    public BaseResponse listSosOrder(@RequestBody Map param, Page page) {
        return BaseResponse.run(() -> service.getSosOrder(param, page));
    }
}
