package ee.marriage.model;

import org.junit.Test;

import static ee.marriage.model.Sex.*;
import static ee.marriage.model.Sex.Female;
import static org.junit.Assert.assertEquals;

public class PersonTest {

  @Test
  public void malesHavePersonalCodeStartingWith3or5() {
    assertEquals(Male, new Person("100000000", "", "").getSex());
    assertEquals(Male, new Person("300000000", "", "").getSex());
    assertEquals(Male, new Person("500000000", "", "").getSex());
  }

  @Test
  public void femalesHavePersonalCodeStartingWith4or6() {
    assertEquals(Female, new Person("200000000", "", "").getSex());
    assertEquals(Female, new Person("400000000", "", "").getSex());
    assertEquals(Female, new Person("600000000", "", "").getSex());
  }
}