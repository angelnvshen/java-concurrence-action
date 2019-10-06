package own.spring.core.springmvc.spring.boot.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletHttpRequestListener2 implements ServletRequestListener {

  @Override
  public void requestDestroyed(ServletRequestEvent sre) {
    ServletContext servletContext = sre.getServletContext();
    servletContext.log("requestListener2 destroy ...");
  }

  @Override
  public void requestInitialized(ServletRequestEvent sre) {
    ServletContext servletContext = sre.getServletContext();
    servletContext.log("requestListener2 initialized ...");
  }
}
