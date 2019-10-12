package own.stu.web_embedded_tomcat;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import own.stu.web.HelloServlet;
import own.stu.web.annotation.AnnotationServlet;

import javax.servlet.ServletException;
import java.io.File;

@Data
public class InsideTomcat {

    public static void main(String[] args) throws LifecycleException, ServletException {

        // 1.创建一个内嵌的Tomcat
        Tomcat tomcat = new Tomcat();


        // 2.设置Tomcat端口默认为8080
        final Integer webPort = 8080;
        tomcat.setPort(webPort);


        // 3.设置工作目录,tomcat需要使用这个目录进行写一些东西
        final String baseDir = "/Users/my/IdeaProjects_own/core/spring/simple-web-sample/tomcat/";
        tomcat.setBaseDir(baseDir);
        tomcat.getHost().setAutoDeploy(false);


        // 4. 设置webapp资源路径
        String webappDirLocation = "webapp/";
        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(baseDir + webappDirLocation).getAbsolutePath());
        System.out.println("configuring app with basedir: " + new File("" + webappDirLocation).getAbsolutePath());
        System.out.println("project dir:" + new File("").getAbsolutePath());


        // 5. 设置上下文路每径
        String contextPath = "/embedded-web";
        ctx.setPath(contextPath);
        ctx.addLifecycleListener(new Tomcat.FixContextListener());
        ctx.setName("/embedded-web");

        System.out.println("child Name:" + ctx.getName());
        tomcat.getHost().addChild(ctx);


        /*File additionWebInfClasses = new File("");
		WebResourceRoot resources = new StandardRoot(ctx);
		resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
		  additionWebInfClasses.getAbsolutePath() + "/classes", "/"));
		ctx.setResources(resources);*/

        tomcat.addServlet(contextPath, "helloServlet", new HelloServlet());
        ctx.addServletMappingDecoded("/hello", "helloServlet");

        // tomcat.addServlet(contextPath, "annotationServlet", new AnnotationServlet());

        System.out.println("服务器加载完配置，正在启动中……");
        tomcat.start();
        System.out.println("服务器启动成功");
        tomcat.getServer().await();
    }
}