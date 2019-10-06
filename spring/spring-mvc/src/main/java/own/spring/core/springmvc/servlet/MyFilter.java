package own.spring.core.springmvc.servlet;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

@WebFilter(servletNames = "myServlet1")
public class MyFilter extends HttpFilter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    ServletContext servletContext = request.getServletContext();

    servletContext.log("MyFilter doFilter ...");

    super.doFilter(request, response, chain);
  }
}
