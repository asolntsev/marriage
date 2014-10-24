package uitest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit.ScreenShooter;
import com.google.inject.Guice;
import ee.marriage.MarriageRegistryServer;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.junit.ScreenShooter.failedTests;
import static java.lang.Integer.parseInt;

public class AbstractUITest {
  @Rule
  public ScreenShooter screenShooter = failedTests();

  private static MarriageRegistryServer server;

  @BeforeClass
  public static void runServer() throws Exception {
    if (server == null) {
      server = Guice.createInjector().getInstance(MarriageRegistryServer.class);
      String port = System.getProperty("http.port", "8081");
      server.start(parseInt(port));
      Configuration.baseUrl = "http://localhost:" + port;
    }
  }

  @Before
  public void resetTestData() {
    open("/reset");
  }
}
