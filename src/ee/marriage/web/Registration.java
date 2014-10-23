package ee.marriage.web;

import ee.marriage.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ee.marriage.model.Sex.Female;
import static ee.marriage.model.Sex.Male;

public class Registration extends BaseServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    render("registration.ftl", response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String husbandCode = request.getParameter("husbandCode");
    String wifeCode = request.getParameter("wifeCode");

    try {
      Person husband = persons.byCode(husbandCode);
      Person wife = persons.byCode(wifeCode);

      validate(husband, wife);
      marriagesRegistry.register(husband, wife);
    }
    catch (ValidationError e) {
      render("registration.ftl", response, "error", e.getMessage(), "husband", husbandCode, "wife", wifeCode);
    }
    
    response.sendRedirect("/");
  }

  private void validate(Person husband, Person wife) {
    if (husband == null)
      throw new ValidationError("Husband not found");

    if (wife == null)
      throw new ValidationError("Wife not found");

    if (husband.getSex() != Male)
      throw new ValidationError("Husband must be a male: " + husband.code);

    if (wife.getSex() != Female)
      throw new ValidationError("Wife must be a female: " + wife.code);
  }
}