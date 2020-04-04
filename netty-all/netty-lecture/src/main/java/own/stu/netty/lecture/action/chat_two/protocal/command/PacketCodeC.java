package own.stu.netty.lecture.action.chat_two.protocal.command;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import own.stu.netty.lecture.action.chat_two.protocal.request.LoginRequestPacket;
import own.stu.netty.lecture.action.chat_two.protocal.request.MessageRequestPacket;
import own.stu.netty.lecture.action.chat_two.protocal.response.LoginResponsePacket;
import own.stu.netty.lecture.action.chat_two.protocal.response.MessageResponsePacket;
import own.stu.netty.lecture.action.chat_two.serialize.Serializer;
import own.stu.netty.lecture.action.chat_two.serialize.SerializerAlgorithm;
import own.stu.netty.lecture.action.chat_two.serialize.impl.JSONSerializer;

import java.util.HashMap;
import java.util.Map;

import static own.stu.netty.lecture.action.chat_two.protocal.command.Command.*;

public class PacketCodeC {

    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private static final int MAGIC_NUMBER = 0x12345678;

    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);

        serializerMap = new HashMap<>();
        serializerMap.put(SerializerAlgorithm.JSON, JSONSerializer.DEFAULT);
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return packetTypeMap.get(command);
    }

    public ByteBuf encode(ByteBuf buffer, Packet packet) {

        // 2. 序列化 java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 3. 实际编码过程
        buffer.writeInt(MAGIC_NUMBER);
        buffer.writeByte(packet.getVersion());
        buffer.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        buffer.writeByte(packet.getCommand());
        buffer.writeInt(bytes.length);
        buffer.writeBytes(bytes);

        return buffer;
    }

    public Packet decode(ByteBuf byteBuf) {
        // 跳过 magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);
        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }
        return null;
    }
}
