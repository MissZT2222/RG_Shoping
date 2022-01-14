/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.joolun.com
 */
package com.joolun.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joolun.mall.config.CommonConstants;
import com.joolun.mall.entity.GoodsCategory;
import com.joolun.mall.entity.GoodsCategoryTree;
import com.joolun.mall.mapper.GoodsCategoryMapper;
import com.joolun.mall.service.GoodsCategoryService;
import com.joolun.mall.util.TreeUtil;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品类目
 *
 * @author www.joolun.com
 * @date 2019-08-12 11:46:28
 */
@Service
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryMapper, GoodsCategory> implements GoodsCategoryService {

	@Override
	public List<GoodsCategoryTree> selectTree(GoodsCategory goodsCategory) {
		//Wrappers.lambdaQuery条件构造器，查询数据
		return getTree(this.list(Wrappers.lambdaQuery(goodsCategory)));
	}

	/**
	 * 构建树
	 *
	 * @param entitys
	 * @return
	 */
	private List<GoodsCategoryTree> getTree(List<GoodsCategory> entitys) {
		List<GoodsCategoryTree> treeList = entitys.stream()
				//filter：过滤流中的某些元素
				.filter(entity -> !entity.getId().equals(entity.getParentId()))
				//sorted()：自然排序，流中元素需实现Comparable接口,通过::访问GoodsCategory类的构造方法
				.sorted(Comparator.comparingInt(GoodsCategory::getSort))
				.map(entity -> {
					GoodsCategoryTree node = new GoodsCategoryTree();
					BeanUtil.copyProperties(entity,node);
					return node;
					//Collector类的toList()方法是静态(类)方法。
					// 它返回一个Collector接口，该接口将输入数据收集到一个新列表中。此方法从不保证返回列表的类型，可变性，可序列化性或线程安全性，
					// 但可以使用toCollection(Supplier)方法进行更多控制。
				}).collect(Collectors.toList());
		return TreeUtil.build(treeList, CommonConstants.PARENT_ID);
	}

	@Override
	public boolean removeById(Serializable id) {
		super.removeById(id);
		//使lambda表达式判断父分类编号和传入ID是否相等
		remove(Wrappers.<GoodsCategory>query().lambda().eq(GoodsCategory::getParentId, id));
		return true;
	}
}
