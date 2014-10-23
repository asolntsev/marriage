package ee.marriage.model;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Singleton
public class PersonRepository {
  private final AtomicLong sequence = new AtomicLong(1);
  private final Map<String, Person> personsByCode = new HashMap<>();
  
  public Person add(Person person) {
    person.id = sequence.incrementAndGet();
    personsByCode.put(person.code, person);
    return person;
  }

  public Person byCode(String code) {
    return personsByCode.get(code);
  }
}
