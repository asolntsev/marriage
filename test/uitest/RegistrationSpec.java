package uitest;

import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationSpec extends AbstractUITest {
  @Test
  public void canRegisterNewMarriage() {
    open("/");
    $(By.linkText("Register a marriage")).click();
    
    $(By.name("husbandCode")).setValue("3769021382937");
    $(By.name("wifeCode")).setValue("4839021382937");
    $(byText("Register")).click();

    $$("#marriages .marriage").shouldHave(size(4));
    $(".alert-error").shouldNotBe(visible);
  }

  @Test
  public void validatesPersonCodes() {
    open("/");
    $(By.linkText("Register a marriage")).click();
    
    $(By.name("husbandCode")).setValue("33333");
    $(By.name("wifeCode")).setValue("44444");
    $(byText("Register")).click();
    
    $(".alert-error").shouldHave(Condition.text("Husband not found"));
    
    $(By.name("husbandCode")).shouldHave(value("33333"));
    $(By.name("wifeCode")).shouldHave(value("44444"));
  }
}
