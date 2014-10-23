package ee.marriage.web;

import ee.marriage.model.MarriagesRepository;
import ee.marriage.model.Person;
import ee.marriage.model.PersonRepository;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyVararg;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.*;

public class RegistrationTest {
  private Registration registration = spy(new Registration());
  private HttpServletRequest request = mock(HttpServletRequest.class);
  private HttpServletResponse response = mock(HttpServletResponse.class);

  @Before
  public void setUp() throws IOException, ServletException {
    doNothing().when(registration).render(anyString(), same(response), anyVararg());
    registration.persons = mock(PersonRepository.class);
    registration.marriagesRegistry = mock(MarriagesRepository.class);
  }

  @Test
  public void checksThatHusbandExists() throws ServletException, IOException {
    when(request.getParameter("husbandCode")).thenReturn("380000000");
    when(request.getParameter("wifeCode")).thenReturn("480000000");
    
    registration.doPost(request, response);
    
    verify(registration).render("registration.ftl", response, "error", "Husband not found", 
        "husband", "380000000", "wife", "480000000");
  }

  @Test
  public void checksThatWifeExists() throws ServletException, IOException {
    when(request.getParameter("husbandCode")).thenReturn("380000000");
    when(request.getParameter("wifeCode")).thenReturn("480000000");
    
    when(registration.persons.byCode("380000000")).thenReturn(new Person("380000000", "", ""));
    
    registration.doPost(request, response);
    verify(registration).render("registration.ftl", response, "error", "Wife not found", 
        "husband", "380000000", "wife", "480000000");
  }

  @Test
  public void checksThatHusbandIsMale() throws ServletException, IOException {
    when(request.getParameter("husbandCode")).thenReturn("470000000");
    when(request.getParameter("wifeCode")).thenReturn("480000000");
    
    when(registration.persons.byCode("470000000")).thenReturn(new Person("470000000", "", ""));
    when(registration.persons.byCode("480000000")).thenReturn(new Person("480000000", "", ""));
    
    registration.doPost(request, response);
    verify(registration).render("registration.ftl", response, "error", "Husband must be a male: 470000000", 
        "husband", "470000000", "wife", "480000000");
  }

  @Test
  public void checksThatWifeIsFemale() throws ServletException, IOException {
    when(request.getParameter("husbandCode")).thenReturn("370000000");
    when(request.getParameter("wifeCode")).thenReturn("390000000");
    
    when(registration.persons.byCode("370000000")).thenReturn(new Person("370000000", "", ""));
    when(registration.persons.byCode("390000000")).thenReturn(new Person("390000000", "", ""));
    
    registration.doPost(request, response);
    verify(registration).render("registration.ftl", response, "error", "Wife must be a female: 390000000", 
        "husband", "370000000", "wife", "390000000");
  }

  @Test
  public void registersMarriage() throws ServletException, IOException {
    when(request.getParameter("husbandCode")).thenReturn("370000000");
    when(request.getParameter("wifeCode")).thenReturn("480000000");

    Person husband = new Person("370000000", "", "");
    Person wife = new Person("480000000", "", "");
    
    when(registration.persons.byCode("370000000")).thenReturn(husband);
    when(registration.persons.byCode("480000000")).thenReturn(wife);
    
    registration.doPost(request, response);
    
    verify(registration.marriagesRegistry).register(husband, wife);
    verify(response).sendRedirect("/");
  }
}