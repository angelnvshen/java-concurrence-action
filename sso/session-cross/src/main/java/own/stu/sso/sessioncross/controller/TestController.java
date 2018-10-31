package own.stu.sso.sessioncross.controller;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import own.stu.sso.sessioncross.util.CookieUtils;

@RestController
public class TestController {

  @RequestMapping("/ok")
  public String ok(){
    return "SUCCESS";
  }

  @RequestMapping("/test")
  public String test(HttpServletRequest request, HttpServletResponse response) {
    String cookieName = "custom_global_session_id";
    String encodeString = "UTF-8";
    String cookieValue = CookieUtils.getCookieValue(request, cookieName, encodeString);
    if (null == cookieValue || "".equals(cookieValue.trim())) {
      System.out.println("无cookie, 生成新的");
      cookieValue = UUID.randomUUID().toString();
    }

    CookieUtils.setCookie(request, response, cookieName, cookieValue, -1, encodeString);
    return "/ok.jsp";
  }
}

