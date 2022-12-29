package com.atguigu.converter;

import com.atguigu.pojo.Goods;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GoodsConverter implements Converter<String, Goods> {
    @Override
    public Goods convert(String source) {
        // 创建一个Goods实例
        Goods goods = new Goods();
        // 以“，”分隔
        String stringvalues[] = source.split(",");
        if (stringvalues != null && stringvalues.length == 3) {
            // 为Goods实例赋值
            goods.setName(stringvalues[0].trim());
            goods.setPrice(Double.parseDouble(stringvalues[1].trim()));
            goods.setNumber(Integer.parseInt(stringvalues[2].trim()));
            return goods;
        } else {
            throw new IllegalArgumentException(String.format(
                    "类型转换失败， 需要格式'apple, 10.58,200 ',但格式是[% s ] ", source));
        }
    }
}