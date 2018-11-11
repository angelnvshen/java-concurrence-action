package own.stu.tkmybatis.demo.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import own.stu.tkmybatis.demo.common.util.IpUtil;
import own.stu.tkmybatis.demo.common.util.TCaptchaVerify;

@Controller
public class HomeController {

  @RequestMapping("water")
  public String getWaterIndex(){
    return "file";
  }

  @ResponseBody
  @RequestMapping("check")
  public String checkWaterInfo(String ticket, String randstr, HttpServletRequest request){

    String ip = IpUtil.getIpAddress(request);
    int code = TCaptchaVerify.verifyTicket(ticket, randstr, ip);
    if(code == 1) {
      return "SUCCESS";
    }else {
      return "FAIL";
    }
  }
}
