package own.spring.core.model;

import java.util.Date;

public class Order {

  private Date createDate;

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  @Override
  public String toString() {
    return "Order{" +
        "createDate=" + createDate +
        '}';
  }
}