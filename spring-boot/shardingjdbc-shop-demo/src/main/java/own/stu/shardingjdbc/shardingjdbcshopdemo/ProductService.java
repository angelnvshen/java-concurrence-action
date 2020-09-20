package own.stu.shardingjdbc.shardingjdbcshopdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import own.stu.shardingjdbc.shardingjdbcshopdemo.dao.ProductDao;
import own.stu.shardingjdbc.shardingjdbcshopdemo.model.ProductDescription;
import own.stu.shardingjdbc.shardingjdbcshopdemo.model.ProductInfo;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    @Transactional
    public void createProduct(ProductInfo productInfo){
        Assert.notNull(productInfo, "商品信息为空");

        productDao.insertProductInfo(productInfo);

        ProductDescription productDescription = new ProductDescription();
        productDescription.setDescription(productInfo.getDescription());
        productDescription.setProductInfoId(productInfo.getProductInfoId());
        productDescription.setStoreId(productInfo.getStoreId());

        productDao.insertProductDescript(productDescription);
    }

    public List<ProductInfo> selectProductInfoList(int pageId, int pageSize){
        pageId = (pageId - 1) * pageSize;
        return productDao.selectProductInfoList(pageId, pageSize);
    }

    public int count(){
        return productDao.count();
    }

    public List<Map> selectProductInfoGroupList(){
        return productDao.selectProductInfoGroupList();
    }
}
