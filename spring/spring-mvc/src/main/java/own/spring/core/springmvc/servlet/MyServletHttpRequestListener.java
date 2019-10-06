package own.spring.core.springmvc.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletHttpRequestListener implements ServletRequestListener {

  @Override
  public void requestDestroyed(ServletRequestEvent sre) {
    ServletContext servletContext = sre.getServletContext();
    servletContext.log("requestListener destroy ...");
  }

  @Override
  public void requestInitialized(ServletRequestEvent sre) {
    ServletContext servletContext = sre.getServletContext();
    servletContext.log("requestListener initialized ...");
  }
}
