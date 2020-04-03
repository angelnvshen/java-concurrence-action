package own.stu.netty.lecture.action.chat_two.protocal.response;

import lombok.Data;
import own.stu.netty.lecture.action.chat_two.protocal.command.Command;
import own.stu.netty.lecture.action.chat_two.protocal.command.Packet;

@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
