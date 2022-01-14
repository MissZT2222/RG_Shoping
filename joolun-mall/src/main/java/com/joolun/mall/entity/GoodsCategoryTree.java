/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.joolun.com
 */
package com.joolun.mall.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 商品类目
 *
 * @author www.joolun.com
 * @date 2019-08-12 11:46:28
 */
@Data
/**
 * @EqualsAndHashCode：
 * 此注解会生成equals(Object other) 和 hashCode()方法。
 * 它默认使用非静态，非瞬态的属性
 * 可通过参数exclude排除一些属性
 * 可通过参数of指定仅使用哪些属性
 * 它默认仅使用该类中定义的属性且不调用父类的方法
 * @return .
 */
@EqualsAndHashCode(callSuper = true)
public class GoodsCategoryTree extends TreeNode {

	/**
	 * （1：开启；0：关闭）
	 */
	@ApiModelProperty(value = "1：开启；0：关闭")
	private String enable;
    /**
   * 父分类编号
   */
	@ApiModelProperty(value = "父分类编号")
    private String parentId;
    /**
   * 名称
   */
	@ApiModelProperty(value = "名称")
    private String name;
    /**
   * 描述
   */
	@ApiModelProperty(value = "描述")
    private String description;
    /**
   * 图片
   */
	@ApiModelProperty(value = "图片")
    private String picUrl;
    /**
   * 排序
   */
	@ApiModelProperty(value = "排序")
    private Integer sort;
    /**
   * 创建时间
   */
	@ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
   * 最后更新时间
   */
	@ApiModelProperty(value = "最后更新时间")
    private LocalDateTime updateTime;
    /**
   * 逻辑删除标记（0：显示；1：隐藏）
   */
	@ApiModelProperty(value = "逻辑删除标记")
    private String delFlag;

}
