package ee.marriage.model;

import javax.inject.Singleton;
import java.util.*;
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

  public Collection<Person> search(String personCodeOrName) {
    Set<Person> results = new LinkedHashSet<>();
    for (Person person : personsByCode.values()) {
      if (person.code.contains(personCodeOrName) ||
          person.firstName.contains(personCodeOrName) ||
          person.lastName.contains(personCodeOrName)) 
        results.add(person);
    }
    return results;
  }
  
  public void removeAll() {
    personsByCode.clear();
  }
}
