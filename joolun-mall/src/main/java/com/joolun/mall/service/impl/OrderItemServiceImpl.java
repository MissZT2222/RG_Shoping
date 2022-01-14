package com.joolun.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joolun.mall.entity.OrderItem;
import com.joolun.mall.mapper.OrderItemMapper;
import com.joolun.mall.service.OrderItemService;
import org.springframework.stereotype.Service;

/**
 * 商城订单详情
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}
