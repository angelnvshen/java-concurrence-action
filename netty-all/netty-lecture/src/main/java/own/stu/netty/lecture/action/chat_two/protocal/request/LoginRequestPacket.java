package own.stu.netty.lecture.action.chat_two.protocal.request;

import lombok.Data;
import own.stu.netty.lecture.action.chat_two.protocal.command.Command;
import own.stu.netty.lecture.action.chat_two.protocal.command.Packet;

@Data
public class LoginRequestPacket extends Packet {

    private String userId;

    private String userName;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
