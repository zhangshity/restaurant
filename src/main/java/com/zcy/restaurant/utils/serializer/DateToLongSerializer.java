package com.zcy.restaurant.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * @ Author: chunyang.zhang
 * @ Description:    格式画订单 创建时间。与前端模版保持一致
 *                            使用：在OrderDTO中的 createTime 属性上加上@JsonSerialize注解
 *                            @ JsonSerialize(using = DateToLongSerializer.class)
 * @ Date: Created in
 * @ Modified: By:
 */
public class DateToLongSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeNumber(date.getTime() / 1000);
    }
}
