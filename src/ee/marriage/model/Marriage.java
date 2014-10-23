package ee.marriage.model;

import java.util.Date;

public class Marriage {
  private final long id;
  private final Person husband;
  private final Person wife;
  private final Date registeredAt;

  public Marriage(long id, Person husband, Person wife, Date registrationDate) {
    this.id = id;
    this.husband = husband;
    this.wife = wife;
    this.registeredAt = registrationDate;
  }

  public Person getHusband() {
    return husband;
  }

  public Person getWife() {
    return wife;
  }

  public Date getRegisteredAt() {
    return registeredAt;
  }
}
