package own.stu.tkmybatis.demo.model;

import javax.persistence.*;

@Table(name = "area")
public class Area {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "JDBC")
    private Short id;

    @Column(name = "CITY_NAME")
    private String cityName;

    @Column(name = "PARENT_ID")
    private Short parentId;

    @Column(name = "INITIAL")
    private String initial;

    @Column(name = "INITIALS")
    private String initials;

    @Column(name = "PINYIN")
    private String pinyin;

    @Column(name = "EXTRA")
    private String extra;

    @Column(name = "SUFFIX")
    private String suffix;

    @Column(name = "CODE")
    private String code;

    @Column(name = "AREA_CODE")
    private String areaCode;

    @Column(name = "ORDER_ID")
    private Byte orderId;

    /**
     * @return ID
     */
    public Short getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Short id) {
        this.id = id;
    }

    /**
     * @return CITY_NAME
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * @param cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * @return PARENT_ID
     */
    public Short getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Short parentId) {
        this.parentId = parentId;
    }

    /**
     * @return INITIAL
     */
    public String getInitial() {
        return initial;
    }

    /**
     * @param initial
     */
    public void setInitial(String initial) {
        this.initial = initial;
    }

    /**
     * @return INITIALS
     */
    public String getInitials() {
        return initials;
    }

    /**
     * @param initials
     */
    public void setInitials(String initials) {
        this.initials = initials;
    }

    /**
     * @return PINYIN
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * @param pinyin
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    /**
     * @return EXTRA
     */
    public String getExtra() {
        return extra;
    }

    /**
     * @param extra
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }

    /**
     * @return SUFFIX
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * @param suffix
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * @return CODE
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return AREA_CODE
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * @param areaCode
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * @return ORDER_ID
     */
    public Byte getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Byte orderId) {
        this.orderId = orderId;
    }
}