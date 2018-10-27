package own.stu.tkmybatis.demo.common.service.impl;

import com.github.pagehelper.PageHelper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import own.stu.tkmybatis.demo.common.service.BaseService;
import tk.mybatis.mapper.common.Mapper;

/**
 * @param <T>
 */
@Service
public  abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    protected Mapper<T> mapper;

    @Override
    public T selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }
    
    @Override
    public T selectOne(T entity){
    	return mapper.selectOne(entity);
    }

    @Override
    public int save(T entity) {
        return mapper.insert(entity);
    }

    @Override
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    public int updateAll(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateNotNull(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<T> selectPage(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return mapper.selectAll();
    }

    @Override
    public List<T> selectPage(int pageNum, int pageSize, T entity) {
        PageHelper.startPage(pageNum,pageSize);
        return mapper.select(entity);
    }

    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }
}
