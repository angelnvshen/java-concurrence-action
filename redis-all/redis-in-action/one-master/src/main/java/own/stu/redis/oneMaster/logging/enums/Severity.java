package own.stu.redis.oneMaster.logging.enums;

import java.util.Objects;

public enum Severity {
    DEBUG("debug", "logging.DEBUG"),
    INFO("info", "logging.INFO"),
    WARN("warn", "logging.WARN"),
    ERROR("error", "logging.ERROR"),
    CRITICAL("critical", "logging.CRITICAL"),
    ;

    String code;
    String desc;

    Severity(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static boolean contain(String code) {
        if (code == null) {
            return false;
        }
        for (Severity severity : Severity.values()) {
            if (Objects.equals(severity.code, code)) {
                return true;
            }
        }
        return false;
    }
}
