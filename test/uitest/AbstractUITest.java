package uitest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit.ScreenShooter;
import com.google.inject.Guice;
import com.google.inject.Injector;
import ee.marriage.MarriageRegistryServer;
import ee.marriage.MarriageRegistryServer.MarriageModule;
import org.junit.BeforeClass;
import org.junit.Rule;

import static com.codeborne.selenide.junit.ScreenShooter.failedTests;
import static java.lang.Integer.parseInt;

public class AbstractUITest {
  @Rule
  public ScreenShooter screenShooter = failedTests();

  private static Injector injector;
  private static MarriageRegistryServer server;

  @BeforeClass
  public static void runServer() throws Exception {
    if (server == null) {
      injector = Guice.createInjector(new MarriageModule());
      server = injector.getInstance(MarriageRegistryServer.class);
      String port = System.getProperty("http.port", "8081");
      server.start(parseInt(port));
      Configuration.baseUrl = "http://localhost:" + port;
    }
  }

//  @Before
//  public void resetQueues() {
//    injector.getInstance(MarriagesRepository.class).deleteAll();
//    injector.getInstance(QueueRepository.class).deleteAll();
//  }
}
