package ee.marriage;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import ee.marriage.model.MarriagesRepository;
import ee.marriage.model.Person;
import ee.marriage.model.PersonRepository;
import ee.marriage.web.Dashboard;
import ee.marriage.web.Registration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.inject.Inject;
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

  @Inject
  private PersonRepository persons;

  @Inject
  private MarriagesRepository marriages;
  
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
    server.generateTestData();
  }

  private void generateTestData() {
    Person meri = persons.add(new Person("3459021382937", "Lennart", "Meri"));
    Person reginaOjavere = persons.add(new Person("4489021382937", "Regina", "Ojavere"));
    Person pjotr = persons.add(new Person("3769021382937", "Пётр", "Петров"));
    Person vasilisa = persons.add(new Person("4839021382937", "Василиса", "Краса"));
    Person toomasHendrikIlves = persons.add(new Person("3720000000", "Toomas Henrik", "Ilves"));
    Person evelin = persons.add(new Person("4839021382937", "Evelin", "Int-Lambot"));

    marriages.register(meri, reginaOjavere, "21.03.1953");
    marriages.register(pjotr, vasilisa, "31.12.2001");
    marriages.register(toomasHendrikIlves, evelin, "30.04.2004");
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
