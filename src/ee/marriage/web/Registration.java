package ee.marriage.web;

import ee.marriage.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Registration extends BaseServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    render("registration.ftl", response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Person husband = personRepository.byCode(request.getParameter("husbandCode"));
    Person wife = personRepository.byCode(request.getParameter("wifeCode"));
    
    marriagesRepository.register(husband, wife);
    
    response.sendRedirect("/");
  }
}