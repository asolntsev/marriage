package ee.marriage;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import ee.marriage.web.Dashboard;
import ee.marriage.web.Registration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.http.HttpServlet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import static org.eclipse.jetty.servlet.ServletContextHandler.SESSIONS;

public class MarriageRegistryServer {
  static Map<String, Class<? extends HttpServlet>> mappings = new LinkedHashMap<String, Class<? extends HttpServlet>>() {{
    put("/registration", Registration.class);
    put("/", Dashboard.class);
    put("/img/*", DefaultServlet.class);
    put("/css/*", DefaultServlet.class);
    put("/js/*", DefaultServlet.class);
  }};

  private Server jetty;

  public void start(int port) throws Exception {
    jetty = new Server(port);
    ServletContextHandler context = new ServletContextHandler(SESSIONS);
    context.setResourceBase("web");
    for (Entry<String, Class<? extends HttpServlet>> mapping : mappings.entrySet()) {
      context.addServlet(mapping.getValue(), mapping.getKey());
    }
    jetty.setHandler(context);

    addShutdownHook();
    jetty.start();
  }

  public void stop() {
    if (jetty != null) {
      try {
        jetty.stop();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private void addShutdownHook() {
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        MarriageRegistryServer.this.stop();
      }
    });
  }

  public static void main(String[] args) throws Exception {
    Injector injector = Guice.createInjector(new MarriageModule());
    MarriageRegistryServer server = injector.getInstance(MarriageRegistryServer.class);
    server.start(8080);
  }
  
  public static class MarriageModule extends AbstractModule {
    @Override
    protected void configure() {
      for (Class<? extends HttpServlet> servletClass : mappings.values()) {
        requestStaticInjection(servletClass);
      }
    }
  }
}
