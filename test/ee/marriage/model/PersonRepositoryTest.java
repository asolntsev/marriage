package ee.marriage.model;

import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class PersonRepositoryTest {

  PersonRepository personRepository = new PersonRepository();

  @Before
  public void setUp() {
    personRepository.add(new Person("37609080707", "John", "Milter"));
  }

  @Test
  public void canSearchByPersonCode() {
    assertEquals(
        asList(new Person("37609080707", "John", "Milter")),
        personRepository.search("707"));
    
  }
  
  @Test
  public void canSearchPersonByFirstName() {
    assertEquals(
        asList(new Person("37609080707", "John", "Milter")), 
        personRepository.search("Joh"));
  }
  
  @Test
  public void canSearchPersonByLastName() {
    assertEquals(
        asList(new Person("37609080707", "John", "Milter")), 
        personRepository.search("ilt"));
  }
  
  @Test
  public void searchingIsCaseInsensitive() {
    assertEquals(
        asList(new Person("37609080707", "John", "Milter")), 
        personRepository.search("jOHN"));
  }
}