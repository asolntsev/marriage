package ee.marriage.model;

import javax.inject.Singleton;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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
    marriages.add(new Marriage(sequence.incrementAndGet(), husband, wife, registrationDate));
  }

  public void removeAll() {
    marriages.clear();
  }
}
