package ee.marriage.web;

import com.google.common.collect.ImmutableMap;
import ee.marriage.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PersonSearch extends BaseServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String personCodeOrName = request.getParameter("term");
    Collection<Person> foundPersons = persons.search(personCodeOrName);
    
    List<Map<String, String>> results = new ArrayList<>();
    for (Person foundPerson : foundPersons) {
      results.add(ImmutableMap.of("label", foundPerson.getName(), "value", "" + foundPerson.code));
    }
    renderJSon(response, results);
  }
}