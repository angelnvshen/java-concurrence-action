package own.stu.highConcurrence.cacheconsistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author HW
 * @since 2020-08-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IdGene extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String businessKey;

    /**
     * 生成类型
     */
    private Integer type;

    /**
     * 上一次生成的最大值
     */
    private Long lastMax;

    /**
     * 版本
     */
    private String version;

}
