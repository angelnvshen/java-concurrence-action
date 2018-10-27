package own.stu.tkmybatis.demo.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

/**
 * Description: Country
 */
public class Country implements Serializable {
    @Id
    @ColumnType(jdbcType = JdbcType.BIGINT)
    private Long   id;

    private String countryName;

    private String countryCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
