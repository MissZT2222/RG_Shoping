package com.joolun.web.api;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joolun.common.core.domain.AjaxResult;
import com.joolun.mall.config.CommonConstants;
import com.joolun.mall.entity.GoodsSpu;
import com.joolun.mall.service.GoodsSpuService;
import com.joolun.weixin.constant.MyReturnCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品api
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/weixin/api/ma/goodsspu")
@Api(value = "goodsspu", tags = "商品接口API")
public class GoodsSpuApi {

    private final GoodsSpuService goodsSpuService;

	/**
	* 分页查询
	* @param page 分页对象
	* @param goodsSpu spu商品
	* @return
	*/
	@ApiOperation(value = "分页查询")
    @GetMapping("/page")
    public AjaxResult getGoodsSpuPage(Page page, GoodsSpu goodsSpu, String couponUserId) {
		//商品是否上架开关
		goodsSpu.setShelf(CommonConstants.YES);
        return AjaxResult.success(goodsSpuService.page1(page, goodsSpu));
    }

    /**
    * 通过id查询spu商品
    * @param id
    * @return R
    */
	@ApiOperation(value = "通过id查询spu商品")
    @GetMapping("/{id}")
    public AjaxResult getById(@PathVariable("id") String id){
		GoodsSpu goodsSpu = goodsSpuService.getById1(id);
		if(goodsSpu == null){
			return AjaxResult.error(MyReturnCode.ERR_80004.getCode(), MyReturnCode.ERR_80004.getMsg());
		}
        return AjaxResult.success(goodsSpu);
    }

}
