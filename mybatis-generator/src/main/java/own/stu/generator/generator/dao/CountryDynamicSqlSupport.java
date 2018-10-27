package own.stu.generator.generator.dao;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CountryDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Country country = new Country();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = country.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> countryName = country.countryName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> countryCode = country.countryCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Country extends SqlTable {
        public final SqlColumn<Long> id = column("Id", JDBCType.INTEGER);

        public final SqlColumn<String> countryName = column("country_name", JDBCType.VARCHAR);

        public final SqlColumn<String> countryCode = column("country_code", JDBCType.VARCHAR);

        public Country() {
            super("country");
        }
    }
}