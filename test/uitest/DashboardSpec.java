package uitest;

import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardSpec extends AbstractUITest {
  @Test
  public void listAllActiveMarriages() {
    $$("#marriages .marriage").shouldHave(size(3));
    $("#marriages .marriage", 0).shouldHave(text("Lennart Meri"), text("Regina Ojavere"), text("21.03.1953"));
    $("#marriages .marriage", 1).shouldHave(text("Пётр Петров"), text("Василиса Краса"), text("31.12.2001"));
    $("#marriages .marriage", 2).shouldHave(text("Toomas Henrik Ilves"), text("Evelin Int-Lambot"), text("30.04.2004"));
  }
}
