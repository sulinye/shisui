package com.su.shisui.goods.controller;

import com.su.shisui.api.goods.service.GoodsService;
import com.su.shisui.common.result.Result;
import com.su.shisui.goods.common.po.Goods;
import com.su.shisui.goods.common.vo.GoodsVo;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.su.shisui.common.WebServerVersion.GOODS_WEB_V;

/**
 * author sly
 */
@RestController
@Api(tags = "订单接口")
@RequestMapping(GOODS_WEB_V+"/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/findGoodsList")
//    @CrossOrigin(origins = "http://localhost:8080")
//    @SysLog(menuCode = "log_emoticons",btnCode = "log_btn_new",code = "emoticons")
    public Result<List<GoodsVo>> findGoodsList(){
        System.out.println("***********************findGoodsList****************************");
        List<Goods> orderList = goodsService.findGoodsList();
        List<GoodsVo> orderVoList = convertGoodsVo(orderList);
        return Result.ok(orderVoList);
    }

    private List<GoodsVo> convertGoodsVo(List<Goods> goodsList) {
        if (null == goodsList || goodsList.size() <= 0) {
            return null;
        }
        List<GoodsVo> goodsVoList = new ArrayList<>();
        GoodsVo goodsVo = new GoodsVo();
        for (Goods goods : goodsList) {
            BeanUtils.copyProperties(goods,goodsVo);
            goodsVo.setId(goods.getId().toString());
            goodsVoList.add(goodsVo);
        }
        return goodsVoList;
    }
}
