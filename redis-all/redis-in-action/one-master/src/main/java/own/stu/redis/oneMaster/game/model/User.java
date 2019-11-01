package own.stu.redis.oneMaster.game.model;

import lombok.Data;

import java.util.Set;

@Data
public class User {

    private Integer id;

    private String name;

    private Long funds;

    private Set<String> itemName;
}
