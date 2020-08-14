package own.stu.shardingjdbc.shardingjdbcshopdemo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import own.stu.shardingjdbc.shardingjdbcshopdemo.model.ProductDescription;
import own.stu.shardingjdbc.shardingjdbcshopdemo.model.ProductInfo;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface ProductDao {

    @Insert("insert into product_info(store_id, product_name, spec, region_code, price, image_url) value " +
            "(#{storeId}, #{productName}, #{spec}, #{regionCode}, #{price}, #{imageUrl} )")
    @Options(useGeneratedKeys = true, keyProperty = "productInfoId", keyColumn = "product_info_id")
    int insertProductInfo(ProductInfo productInfo);

    @Insert("insert into product_descript(product_info_id, description, store_id) value " +
            "(#{productInfoId}, #{description}, #{storeId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertProductDescript(ProductDescription productDescription);

    @Select("select p.*, d.description, r.region_name as regionName from product_info p " +
            "left join product_descript d on p.product_info_id = d.product_info_id " +
            "left join region r on p.region_code = r.region_code " +
            "order by p.product_info_id " +
            "limit #{start}, #{size}")
    List<ProductInfo> selectProductInfoList(int start, int size);

    @Select("select count(1) from product_info ")
    int count();

    @Select("select count(1) as num from product_info p " +
            "group by region_code having num > 1 " +
            "order by region_code ")
    List<Map> selectProductInfoGroupList();
}
