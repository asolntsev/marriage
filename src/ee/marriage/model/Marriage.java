package ee.marriage.model;

import java.util.Date;

public class Marriage {
  private final String husband;
  private final String wife;
  private final Date registeredAt;

  public Marriage(String husband, String wife, Date registrationDate) {
    this.husband = husband;
    this.wife = wife;
    this.registeredAt = registrationDate;
  }

  public String getHusband() {
    return husband;
  }

  public String getWife() {
    return wife;
  }

  public Date getRegisteredAt() {
    return registeredAt;
  }
}
