package own.stu.netty.lecture.action.chat_two.attribute;

import io.netty.util.AttributeKey;
import own.stu.netty.lecture.action.chat_two.session.Session;

public interface Attributes {

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
