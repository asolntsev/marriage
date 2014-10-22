package uitest;

import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardSpec extends AbstractUITest {
  @Test
  public void listAllActiveMarriages() {
    $$("#marriages .marriage").shouldHave(size(2));
  }
}
