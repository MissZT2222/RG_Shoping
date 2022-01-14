package com.joolun.mall.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joolun.mall.entity.GoodsSpu;
import com.joolun.mall.mapper.GoodsSpuMapper;
import com.joolun.mall.service.GoodsSpuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * spu商品
 */
@Service
public class GoodsSpuServiceImpl extends ServiceImpl<GoodsSpuMapper, GoodsSpu> implements GoodsSpuService {


	@Override
	//@Transactional 注解应该只被应用到 public 方法上
	//默认情况下,事务遇到RuntimeException 时会回滚 . 遇到受检查的异常 是不会回滚的.
	// 要想所有异常都回滚,要加上 @Transactional( rollbackFor={Exception.class,其它异常})
	@Transactional(rollbackFor = Exception.class)
	/**
	 * removeById()是根据数据库中已有id删除的结果作为参数。
	 * @return .
	 */
	public boolean removeById(Serializable id) {
		super.removeById(id);
		return true;
	}

	@Override
	public IPage<GoodsSpu> page1(IPage<GoodsSpu> page, GoodsSpu goodsSpu) {
		return baseMapper.selectPage1(page, goodsSpu);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save1(GoodsSpu goodsSpu) {
		baseMapper.insert(goodsSpu);
		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updateById1(GoodsSpu goodsSpu) {
		baseMapper.updateById(goodsSpu);
		return true;
	}

	@Override
	public GoodsSpu getById1(String id) {
		return baseMapper.selectById1(id);
	}
}
