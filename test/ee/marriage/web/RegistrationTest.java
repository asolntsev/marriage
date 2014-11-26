package ee.marriage.web;

import ee.marriage.model.MarriagesRepository;
import ee.marriage.model.Person;
import ee.marriage.model.PersonRepository;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class RegistrationTest {
  Registration registration = new Registration();

  @Before
  public void setUp() {
    registration.persons = mock(PersonRepository.class);
    registration.marriagesRegistry = mock(MarriagesRepository.class);
  }

  @Test
  public void canRegisterMarriage() {
    registration.register("3808080", "4808080");

    verify(registration.marriagesRegistry)
        .register(new Person("30000", "John", "Smith"), new Person("40000", "Екатерина", "Самойлова"));
  }

  @Test
  public void maleAndFemaleCanRegisterMarriage() {
    when(registration.persons.byCode("30000")).thenReturn(new Person("30000", "John", "Smith"));
    when(registration.persons.byCode("40000")).thenReturn(new Person("40000", "Екатерина", "Самойлова"));
    
    registration.register("30000", "40000");
    
    verify(registration.marriagesRegistry)
        .register(new Person("30000", "John", "Smith"), new Person("40000", "Екатерина", "Самойлова"));
  }

  @Test(expected = ValidationError.class)
  public void husbandMustBeMale() {
    when(registration.persons.byCode("4000")).thenReturn(new Person("4000", "female", ""));
    when(registration.persons.byCode("4444")).thenReturn(new Person("4444", "", ""));
    
    registration.register("4000", "4444");
  }

  @Test(expected = ValidationError.class)
  public void wifeMustBeMale() {
    when(registration.persons.byCode("30000")).thenReturn(new Person("30000", "", ""));
    when(registration.persons.byCode("50000")).thenReturn(new Person("50000", "male", ""));

    registration.register("30000", "50000");
  }
}
