package own.stu.tkmybatis.demo.model;

import java.util.List;
import java.util.Map;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example;

public class OwnExample extends Example {

  public OwnExample(Class<?> entityClass) {
    super(entityClass);
  }

  public OwnExample(Class<?> entityClass, boolean exists) {
    super(entityClass, exists);
  }

  public OwnExample(Class<?> entityClass, boolean exists, boolean notNull) {
    super(entityClass, exists, notNull);
  }

  protected Criteria createCriteriaInternal() {
    Criteria criteria = new OwnExampleCriteria(propertyMap, exists, notNull);
    return criteria;
  }

  public static class OwnExampleCriteria extends Example.Criteria {

    protected OwnExampleCriteria(Map<String, EntityColumn> propertyMap, boolean exists,
        boolean notNull) {
      super(propertyMap, exists, notNull);
    }

    public Criteria andIdIsNull() {
      addCriterion("Id is null");
      return (Criteria) this;
    }

    public Criteria andIdIsNotNull() {
      addCriterion("Id is not null");
      return (Criteria) this;
    }

    public Criteria andIdEqualTo(Long value) {
      addCriterion("Id =", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotEqualTo(Long value) {
      addCriterion("Id <>", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThan(Long value) {
      addCriterion("Id >", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThanOrEqualTo(Long value) {
      addCriterion("Id >=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThan(Long value) {
      addCriterion("Id <", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThanOrEqualTo(Long value) {
      addCriterion("Id <=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdIn(List<Long> values) {
      addCriterion("Id in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotIn(List<Long> values) {
      addCriterion("Id not in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdBetween(Long value1, Long value2) {
      addCriterion("Id between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotBetween(Long value1, Long value2) {
      addCriterion("Id not between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andCountryNameIsNull() {
      addCriterion("country_name is null");
      return (Criteria) this;
    }

    public Criteria andCountryNameIsNotNull() {
      addCriterion("country_name is not null");
      return (Criteria) this;
    }

    public Criteria andCountryNameEqualTo(String value) {
      addCriterion("country_name =", value, "countryName");
      return (Criteria) this;
    }

    public Criteria andCountryNameNotEqualTo(String value) {
      addCriterion("country_name <>", value, "countryName");
      return (Criteria) this;
    }

    public Criteria andCountryNameGreaterThan(String value) {
      addCriterion("country_name >", value, "countryName");
      return (Criteria) this;
    }

    public Criteria andCountryNameGreaterThanOrEqualTo(String value) {
      addCriterion("country_name >=", value, "countryName");
      return (Criteria) this;
    }

    public Criteria andCountryNameLessThan(String value) {
      addCriterion("country_name <", value, "countryName");
      return (Criteria) this;
    }

    public Criteria andCountryNameLessThanOrEqualTo(String value) {
      addCriterion("country_name <=", value, "countryName");
      return (Criteria) this;
    }

    public Criteria andCountryNameLike(String value) {
      addCriterion("country_name like", value, "countryName");
      return (Criteria) this;
    }

    public Criteria andCountryNameNotLike(String value) {
      addCriterion("country_name not like", value, "countryName");
      return (Criteria) this;
    }

    public Criteria andCountryNameIn(List<String> values) {
      addCriterion("country_name in", values, "countryName");
      return (Criteria) this;
    }

    public Criteria andCountryNameNotIn(List<String> values) {
      addCriterion("country_name not in", values, "countryName");
      return (Criteria) this;
    }

    public Criteria andCountryNameBetween(String value1, String value2) {
      addCriterion("country_name between", value1, value2, "countryName");
      return (Criteria) this;
    }

    public Criteria andCountryNameNotBetween(String value1, String value2) {
      addCriterion("country_name not between", value1, value2, "countryName");
      return (Criteria) this;
    }

    public Criteria andCountryCodeIsNull() {
      addCriterion("country_code is null");
      return (Criteria) this;
    }

    public Criteria andCountryCodeIsNotNull() {
      addCriterion("country_code is not null");
      return (Criteria) this;
    }

    public Criteria andCountryCodeEqualTo(String value) {
      addCriterion("country_code =", value, "countryCode");
      return (Criteria) this;
    }

    public Criteria andCountryCodeNotEqualTo(String value) {
      addCriterion("country_code <>", value, "countryCode");
      return (Criteria) this;
    }

    public Criteria andCountryCodeGreaterThan(String value) {
      addCriterion("country_code >", value, "countryCode");
      return (Criteria) this;
    }

    public Criteria andCountryCodeGreaterThanOrEqualTo(String value) {
      addCriterion("country_code >=", value, "countryCode");
      return (Criteria) this;
    }

    public Criteria andCountryCodeLessThan(String value) {
      addCriterion("country_code <", value, "countryCode");
      return (Criteria) this;
    }

    public Criteria andCountryCodeLessThanOrEqualTo(String value) {
      addCriterion("country_code <=", value, "countryCode");
      return (Criteria) this;
    }

    public Criteria andCountryCodeLike(String value) {
      addCriterion("country_code like", value, "countryCode");
      return (Criteria) this;
    }

    public Criteria andCountryCodeNotLike(String value) {
      addCriterion("country_code not like", value, "countryCode");
      return (Criteria) this;
    }

    public Criteria andCountryCodeIn(List<String> values) {
      addCriterion("country_code in", values, "countryCode");
      return (Criteria) this;
    }

    public Criteria andCountryCodeNotIn(List<String> values) {
      addCriterion("country_code not in", values, "countryCode");
      return (Criteria) this;
    }

    public Criteria andCountryCodeBetween(String value1, String value2) {
      addCriterion("country_code between", value1, value2, "countryCode");
      return (Criteria) this;
    }

    public Criteria andCountryCodeNotBetween(String value1, String value2) {
      addCriterion("country_code not between", value1, value2, "countryCode");
      return (Criteria) this;
    }
  }
}
