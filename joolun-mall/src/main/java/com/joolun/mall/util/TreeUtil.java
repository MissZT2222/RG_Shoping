package com.joolun.mall.util;

import com.joolun.mall.entity.TreeNode;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * 构建递归树工具类，用于返回商品类目
 * @author
 */
@UtilityClass
public class TreeUtil {
	/**
	 * 两层循环实现建树
	 *
	 * @param treeNodes 传入的树节点列表
	 * @return
	 */
	public <T extends TreeNode> List<T> build(List<T> treeNodes, Object root) {
		List<T> trees = new ArrayList<>();
		//root是树形父类ID，默认0
		for (T treeNode : treeNodes) {
			//判断父分类编号是否为0
			if (root.equals(treeNode.getParentId())) {
				trees.add(treeNode);//添加数据
//				trees.sort(Comparator.comparing(TreeNode::getSort));
			}
			for (T it : treeNodes) {
				//判断父分类编号是否跟PK ID相等，相等则往ArrayList中添加数组
				if (it.getParentId().equals(treeNode.getId())) {
					treeNode.addChildren(it);
//					treeNode.getChildren().sort(Comparator.comparing(TreeNode::getSort));
				}
			}
		}
		return trees;
	}

	/**
	 * 使用递归方法建树
	 *
	 * @param treeNodes
	 * @return
	 */
	public <T extends TreeNode> List<T> buildByRecursive(List<T> treeNodes, Object root) {
		List<T> trees = new ArrayList<T>();
		for (T treeNode : treeNodes) {
			if (root.equals(treeNode.getParentId())) {
				trees.add(findChildren(treeNode, treeNodes));
			}
		}
		return trees;
	}

	/**
	 * 递归查找子节点
	 *
	 * @param treeNodes
	 * @return
	 */
	public <T extends TreeNode> T findChildren(T treeNode, List<T> treeNodes) {
		for (T it : treeNodes) {
			if (treeNode.getId() == it.getParentId()) {
				if (treeNode.getChildren() == null) {
					treeNode.setChildren(new ArrayList<>());
				}
				treeNode.addChildren(findChildren(it, treeNodes));
			}
		}
		return treeNode;
	}


}
