package com.su.shisui.goods.mapper;

import com.su.shisui.goods.common.po.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * author sly
 */
@Mapper
public interface GoodsMapper {
    List<Goods> findGoodsList();
}
