package com.joolun.mall.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
/**
 * 树节点
 * @author
 */
public class TreeNode {
	protected String id;
	/**
	 * 父分类编号
	 */
	protected String parentId;
	/**
	 *排序
	 */
	private Integer sort;
	/**
	 * 用于添加ArrayList数组元素
	 * @return .
	 */
	protected List<TreeNode> children = new ArrayList<>();

	public void addChildren(TreeNode treeNode) {
		children.add(treeNode);
	}
	/**
	 * 获取数组元素
	 * @return .
	 */
	public List<TreeNode> getChildren() {
		if(children.size()<=0){ //判空
			return null;
		}
		return children;
	}
}
