package own.stu.shardingjdbc.shardingjdbcshopdemo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import own.stu.shardingjdbc.shardingjdbcshopdemo.ProductService;
import own.stu.shardingjdbc.shardingjdbcshopdemo.ShardingjdbcShopDemoApplication;
import own.stu.shardingjdbc.shardingjdbcshopdemo.model.ProductInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ShardingjdbcShopDemoApplication.class})
public class ProductServiceTest {

    @Autowired
    private ProductService productService;


    @Test
    public void testCreateProduct() {

        Random random = new Random(100);
        for (int i = 0; i < 20; i++) {
            ProductInfo productInfo = new ProductInfo();

            productInfo.setDescription("iphone 11 MAX the most powerful one " + i);
            productInfo.setImageUrl("http://www.baidu.com/xjwifw.jpg");
            productInfo.setPrice(new BigDecimal(5999));
            productInfo.setProductName("iphone 11 MAX" + i);
            productInfo.setRegionCode("410-100");
            productInfo.setSpec("黄景色");
            productInfo.setStoreId(random.nextInt(2) + 1L);

            productService.createProduct(productInfo);
        }
    }

    @Test
    public void testSelectProductInfoList(){
        List<ProductInfo> productInfos = productService.selectProductInfoList(4, 5);
        System.out.println(productInfos);
    }

    @Test
    public void testCount(){
        int count = productService.count();
        System.out.println(count);
    }

    @Test
    public void testSelectProductInfoGroupList(){
        List<Map> maps = productService.selectProductInfoGroupList();
        System.out.println(maps);
    }
}
