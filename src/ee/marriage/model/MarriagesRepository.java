package ee.marriage.model;

import javax.inject.Singleton;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import static ee.marriage.model.Sex.Female;
import static ee.marriage.model.Sex.Male;

@Singleton
public class MarriagesRepository {
  private final AtomicLong sequence = new AtomicLong(1);
  private final List<Marriage> marriages = new ArrayList<>();
  
  public List<Marriage> all() {
    return marriages;
  }
  
  public void register(Person husband, Person wife) {
    register(husband, wife, new Date());
  }
  
  public void register(Person husband, Person wife, String registrationDate) {
    try {
      register(husband, wife, new SimpleDateFormat("dd.MM.yyyy").parse(registrationDate));
    }
    catch (ParseException e) {
      throw new IllegalArgumentException(e);
    }
  }
  
  public void register(Person husband, Person wife, Date registrationDate) {
    if (husband.getSex() != Male)
      throw new IllegalArgumentException("Husband must be a male: " + husband);

    if (wife.getSex() != Female)
      throw new IllegalArgumentException("Wife must be a female");
    
    marriages.add(new Marriage(sequence.incrementAndGet(), husband, wife, registrationDate));
  }
}
