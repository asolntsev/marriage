package ee.marriage.web;

import ee.marriage.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResetTestData extends BaseServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    generateTestData();
    response.sendRedirect("/");
  }

  @Override
  public void init() throws ServletException {
    generateTestData();
  }

  private void generateTestData() {
    marriagesRegistry.removeAll();
    persons.removeAll();
    
    Person meri = persons.add(new Person("3459021382937", "Lennart", "Meri"));
    Person reginaOjavere = persons.add(new Person("4489021382937", "Regina", "Ojavere"));
    Person pjotr = persons.add(new Person("3769021382937", "Пётр", "Петров"));
    Person vasilisa = persons.add(new Person("4839021382937", "Василиса", "Краса"));
    Person toomasHendrikIlves = persons.add(new Person("3720000000", "Toomas Henrik", "Ilves"));
    Person evelin = persons.add(new Person("4839021382937", "Evelin", "Int-Lambot"));

    marriagesRegistry.register(meri, reginaOjavere, "21.03.1953");
    marriagesRegistry.register(pjotr, vasilisa, "31.12.2001");
    marriagesRegistry.register(toomasHendrikIlves, evelin, "30.04.2004");
  }
}