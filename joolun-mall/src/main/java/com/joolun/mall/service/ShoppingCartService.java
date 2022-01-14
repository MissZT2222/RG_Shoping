package com.joolun.mall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joolun.mall.entity.ShoppingCart;

/**
 * 购物车
 */
public interface ShoppingCartService extends IService<ShoppingCart> {

	IPage<ShoppingCart> page2(IPage<ShoppingCart> page, ShoppingCart shoppingCart);
}
