package own.stu.shardingjdbc.shardingjdbcshopdemo.model;

import lombok.Data;

@Data
public class Region {

    private Long id;

    private Integer level;

    private String regionCode;

    private String regionName;

    private String parentRegionCode;
}
