package own.spring.core.springmvc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "myServlet1", urlPatterns = "/myServlet-1", initParams = {
    @WebInitParam(name = "initValue", value = "world")
})
public class MyServlet extends HttpServlet {

  private String initValue;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    initValue = config.getInitParameter("initValue");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    PrintWriter writer = resp.getWriter();

    ServletContext servletContext = getServletContext();
    servletContext.log("myServlet doGet ...");

    writer.write("<html><body>Hello,World , my initValue = " + initValue + " </body></html>");
  }
}
