package own.stu.tkmybatis.demo.model;

import javax.persistence.*;

@Table(name = "cost_record")
public class CostRecord {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    /**
     * 持续时间
     */
    @Column(name = "last_minutes")
    private Integer lastMinutes;

    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return start_time
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return end_time
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取持续时间
     *
     * @return last_minutes - 持续时间
     */
    public Integer getLastMinutes() {
        return lastMinutes;
    }

    /**
     * 设置持续时间
     *
     * @param lastMinutes 持续时间
     */
    public void setLastMinutes(Integer lastMinutes) {
        this.lastMinutes = lastMinutes;
    }

    /**
     * @return category_id
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}