package ee.marriage.model;

import javax.inject.Singleton;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Singleton
public class MarriagesRepository {
  private final List<Marriage> marriages = new ArrayList<>();
  
  public List<Marriage> all() {
    return marriages;
  }
  
  public void register(String husband, String wife) {
    marriages.add(new Marriage(husband, wife, new Date()));
  }
  
  public void register(String husband, String wife, String registrationDate) {
    try {
      marriages.add(new Marriage(husband, wife, new SimpleDateFormat("dd.MM.yyyy").parse(registrationDate)));
    }
    catch (ParseException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
