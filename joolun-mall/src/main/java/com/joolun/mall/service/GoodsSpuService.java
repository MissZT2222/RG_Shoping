/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.joolun.com
 * 注意：
 * 本软件为www.joolun.com开发研制，项目使用请保留此说明
 */
package com.joolun.mall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joolun.mall.entity.GoodsSpu;

/**
 * spu商品
 *
 * @author JL
 * @date 2019-08-12 16:25:10
 */
public interface GoodsSpuService extends IService<GoodsSpu> {
	/**
	 * mybatis-plus分页方法
	 * @return .
	 */
	IPage<GoodsSpu> page1(IPage<GoodsSpu> page, GoodsSpu goodsSpu);
	/**
	 * 插入
	 * @return .
	 */
	boolean save1(GoodsSpu goodsSpu);
	/**
	 * 更新
	 * @return .
	 */
	boolean updateById1(GoodsSpu goodsSpu);
	/**
	 * 根据ID进行查找
	 * @return .
	 */
	GoodsSpu getById1(String id);

}
