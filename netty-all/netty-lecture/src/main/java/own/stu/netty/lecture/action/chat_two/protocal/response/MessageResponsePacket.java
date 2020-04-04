package own.stu.netty.lecture.action.chat_two.protocal.response;

import lombok.Data;
import own.stu.netty.lecture.action.chat_two.protocal.command.Command;
import own.stu.netty.lecture.action.chat_two.protocal.command.Packet;

@Data
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String fromUserName;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
