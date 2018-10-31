package own.stu.sso.sessioncross.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

  public static String getCookieValue(HttpServletRequest request, String cookieName, String encodeString) {

    Cookie[] cookieList = request.getCookies();
    if (cookieList == null || cookieName == null) {
      return null;
    }
    String retValue = null;
    try {
      for (int i = 0; i < cookieList.length; i++) {
        if (cookieList[i].getName().equals(cookieName)) {
          retValue = URLDecoder.decode(cookieList[i].getValue(), encodeString);
          break;
        }
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return retValue;
  }

  public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
      String cookieValue) {
    setCookie(request, response, cookieName, cookieValue, -1);
  }

  public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
      String cookieValue, int cookieMaxAge) {
    setCookie(request, response, cookieName, cookieValue, cookieMaxAge, false);
  }

  public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
      String cookieValue, boolean isEncode) {
    setCookie(request, response, cookieName, cookieValue, -1, isEncode);
  }

  public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
      String cookieValue, int cookieMaxAge, boolean isEncode) {
    doSetCookie(request, response, cookieName, cookieValue, cookieMaxAge, isEncode);
  }

  public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
      String cookieValue, int cookieMaxAge, String encodeString) {
    doSetCookie(request, response, cookieName, cookieValue, cookieMaxAge, encodeString);
  }

  public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
    doSetCookie(request, response, cookieName, "", -1, false);
  }

  private static final void doSetCookie(
      HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxAge,
      String encodeString){
    try {
      if (cookieValue == null) {
        cookieValue = "";
      } else {
        cookieValue = URLEncoder.encode(cookieValue, encodeString);
      }

      Cookie cookie = new Cookie(cookieName, cookieValue);
      if (cookieMaxAge > 0) {
        cookie.setMaxAge(cookieMaxAge);
      }
      if (null != request) {
        String domainName = getDomainName(request);
        if (!"localhost".equals(domainName)) {
          cookie.setDomain(domainName);
        }
        cookie.setPath("/");
        response.addCookie(cookie);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static final void doSetCookie(
      HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxAge,
      boolean isEncode) {
    doSetCookie(request, response, cookieName, cookieValue, cookieMaxAge, "utf-8");
  }

  /**
   * 得到cookies的域名
   */
  private static final String getDomainName(HttpServletRequest request) {
    String domainName = null;

    //获取请求的url地址
    String serverName = request.getRequestURL().toString();
    if (serverName == null || serverName.equals("")) {
      domainName = "";
    } else {
      serverName = serverName.toLowerCase();
      if (serverName.startsWith("http://")) {
        serverName = serverName.substring(7);
      } else if (serverName.startsWith("https://")) {
        serverName = serverName.substring(8);
      }
      final int end = serverName.indexOf("/");
      serverName = serverName.substring(0, end);
      final String[] domains = serverName.split("\\.");
      int len = domains.length;

      // 保留 *.baidu.com 或者 *.tieba.com.cn
      if (len > 3) {
        domainName = domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
      } else if (len <= 3 && len > 1) {
        domainName = domains[len - 2] + "." + domains[len - 1];
      } else {
        domainName = serverName;
      }
    }
    if (domainName != null && domainName.indexOf(":") > 0) {
      String[] ary = domainName.split("\\:");
      domainName = ary[0];
    }
    return domainName;
  }
}