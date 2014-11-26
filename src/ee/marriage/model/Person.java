package ee.marriage.model;

import static ee.marriage.model.Sex.Female;
import static ee.marriage.model.Sex.Male;

public class Person {
  public Long id;
  public final String code;
  public final String firstName;
  public final String lastName;

  public Person(String code, String firstName, String lastName) {
    this.code = code;
    this.firstName = firstName;
    this.lastName = lastName;
  }
  
  public String getName() {
    return firstName + ' ' + lastName;
  }
  
  public Sex getSex() {
    switch (Integer.parseInt(code.substring(0, 1)) % 2) {
      case 1:
        return Male;
      case 0:
        return Female;
      default:
        throw new IllegalArgumentException("Incorrect personal code: " + code);
    }
  }

  @Override
  public boolean equals(Object obj) {
    return (obj instanceof Person) && ((Person) obj).code.equals(code);
  }
}
