package own.stu.generator.generator.model;

import java.io.Serializable;
import javax.annotation.Generated;

public class Country implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String countryName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String countryCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private static final long serialVersionUID = 1L;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getCountryName() {
        return countryName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCountryName(String countryName) {
        this.countryName = countryName == null ? null : countryName.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getCountryCode() {
        return countryCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode == null ? null : countryCode.trim();
    }

    @Override
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", countryName=").append(countryName);
        sb.append(", countryCode=").append(countryCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}