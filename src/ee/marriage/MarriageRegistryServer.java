package ee.marriage;

import com.google.inject.Guice;
import com.google.inject.Injector;
import ee.marriage.web.Dashboard;
import ee.marriage.web.PersonSearch;
import ee.marriage.web.Registration;
import ee.marriage.web.ResetTestData;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;

import static org.eclipse.jetty.servlet.ServletContextHandler.SESSIONS;

public class MarriageRegistryServer {
  @Inject private Registration registration;
  @Inject private Dashboard dashboard;
  @Inject private PersonSearch personSearch;
  @Inject private DefaultServlet defaultServlet;

  private void mappings() {
    map("/", dashboard);
    map("/registration", registration);
    map("/person/search", personSearch);
    map("/img/*", defaultServlet);
    map("/css/*", defaultServlet);
    map("/js/*", defaultServlet);
  }
  
  private ServletContextHandler context = new ServletContextHandler(SESSIONS);
  private Server jetty;

  private void map(String path, HttpServlet servlet) {
    context.addServlet(new ServletHolder(servlet), path);
  }

  public void start(int port) throws Exception {
    mappings();

    jetty = new Server(port);
    context.setResourceBase("web");
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
    Injector injector = Guice.createInjector();
    MarriageRegistryServer server = injector.getInstance(MarriageRegistryServer.class);
    server.start(8080);
  }
}
