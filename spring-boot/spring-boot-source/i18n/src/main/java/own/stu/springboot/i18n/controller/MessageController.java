package own.stu.springboot.i18n.controller;

import static org.springframework.web.servlet.DispatcherServlet.LOCALE_RESOLVER_BEAN_NAME;

import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Controller
public class MessageController {

  @Autowired
  private WebApplicationContext applicationContext;

  @RequestMapping("i18n")
  public String message(Map<String, Object> map, HttpServletRequest request) {

    map.put("loginUser", "wocaolei");

    AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();//applicationContext.getBean(AcceptHeaderLocaleResolver.class);

    if (acceptHeaderLocaleResolver != null) {
      Locale locale = acceptHeaderLocaleResolver.resolveLocale(request);

//      System.out.println(applicationContext.getMessage("username", null, Locale.getDefault()));
      System.out.println(applicationContext.getMessage("username", null, locale));
    }

//    LocaleResolver bean = applicationContext.getBean(AcceptHeaderLocaleResolver.class);
//    System.out.println(bean);

    return "i18n";
  }

}
