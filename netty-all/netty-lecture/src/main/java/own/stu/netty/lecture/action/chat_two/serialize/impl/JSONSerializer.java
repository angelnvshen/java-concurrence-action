package own.stu.netty.lecture.action.chat_two.serialize.impl;

import com.alibaba.fastjson.JSON;
import own.stu.netty.lecture.action.chat_two.serialize.Serializer;
import own.stu.netty.lecture.action.chat_two.serialize.SerializerAlgorithm;

public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object o) {
        return JSON.toJSONBytes(o);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
