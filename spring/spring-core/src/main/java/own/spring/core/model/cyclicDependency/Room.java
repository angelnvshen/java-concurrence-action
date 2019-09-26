package own.spring.core.model.cyclicDependency;

public class Room {

  private String television;
  private String airConditioner;
  private String refrigerator;
  private String washer;

  public String getTelevision() {
    return television;
  }

  public void setTelevision(String television) {
    this.television = television;
  }

  public String getAirConditioner() {
    return airConditioner;
  }

  public void setAirConditioner(String airConditioner) {
    this.airConditioner = airConditioner;
  }

  public String getRefrigerator() {
    return refrigerator;
  }

  public void setRefrigerator(String refrigerator) {
    this.refrigerator = refrigerator;
  }

  public String getWasher() {
    return washer;
  }

  public void setWasher(String washer) {
    this.washer = washer;
  }
}