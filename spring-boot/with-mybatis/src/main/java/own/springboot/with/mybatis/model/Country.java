package own.springboot.with.mybatis.model;

import lombok.Data;

@Data
public class Country {

  private Integer id;

  private String countryCode;

  private String countryName;
}
