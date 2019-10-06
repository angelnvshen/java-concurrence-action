package own.spring.core.springmvc.spring.boot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet2 extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    PrintWriter writer = resp.getWriter();

    ServletContext servletContext = getServletContext();
    servletContext.log("myServlet2 doGet ...");

    writer.write("<html><body>Hello,World from MyServlet2 </body></html>");
  }
}
