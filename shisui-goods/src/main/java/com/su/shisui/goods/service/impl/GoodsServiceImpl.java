package com.su.shisui.goods.service.impl;

import com.su.shisui.api.goods.service.GoodsService;
import com.su.shisui.goods.common.po.Goods;
import com.su.shisui.goods.mapper.GoodsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * author sly
 */
@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> findGoodsList() {
        return goodsMapper.findGoodsList();
    }
}
