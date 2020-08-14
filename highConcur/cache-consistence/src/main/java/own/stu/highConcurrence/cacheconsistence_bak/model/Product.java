package own.stu.highConcurrence.cacheconsistence_bak.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Log
@Table(name = "t_product")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class Product {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Integer store;

}
