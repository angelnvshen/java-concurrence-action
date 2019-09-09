package own.stu.spring.springsource;

import lombok.Data;

import java.io.Serializable;

@Data
public class Stamina implements Serializable {

    private Long secondsToIncentivisedVideoCooldown;

    private Integer stamina;

    private Long secondsToMoreStamina;

    public Long getSecondsToIncentivisedVideoCooldown() {
        return secondsToIncentivisedVideoCooldown;
    }

    public void setSecondsToIncentivisedVideoCooldown(Long secondsToIncentivisedVideoCooldown) {
        this.secondsToIncentivisedVideoCooldown = secondsToIncentivisedVideoCooldown;
    }

    public Integer getStamina() {
        return stamina;
    }

    public void setStamina(Integer stamina) {
        this.stamina = stamina;
    }

    public Long getSecondsToMoreStamina() {
        return secondsToMoreStamina;
    }

    public void setSecondsToMoreStamina(Long secondsToMoreStamina) {
        this.secondsToMoreStamina = secondsToMoreStamina;
    }
}

