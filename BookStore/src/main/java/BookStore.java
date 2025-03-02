import jakarta.servlet.Servlet;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.apache.catalina.Context;

public class BookStore {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        // Set the webapp directory to the current directory
        String webAppDirLocation = System.getProperty("user.dir");

        // Create a context for the web application with an empty context path
        Context context = tomcat.addWebapp("", webAppDirLocation);

        // Create a Jersey servlet container
        Servlet jerseyServlet = new ServletContainer(new ResourceConfig(BookStore.class));

        // Add the Jersey servlet to the context and map it to /api/*
        tomcat.addServlet(context, "jerseyServlet", jerseyServlet).addMapping("/api/*");

        // Start Tomcat and wait for requests
        tomcat.start();
        tomcat.getServer().await();
    }
}