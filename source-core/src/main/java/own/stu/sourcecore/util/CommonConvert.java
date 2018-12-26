package own.stu.sourcecore.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

public class CommonConvert {

  public static <R, T> List<T> entity2DtoList(List<R> entityList, Class<T> clazz) {
    if (CollectionUtils.isNotEmpty(entityList)) {
      return entityList.stream().map(entity -> entity2Dto(entity, clazz)).collect(Collectors.toList());
    }

    return new ArrayList<>();
  }

  public static <R, T> List<R> dto2EntityList(List<T> dtoList, Class<R> clazz) {
    if (CollectionUtils.isNotEmpty(dtoList)) {
      return dtoList.stream().map(dto -> dto2Entity(dto, clazz)).collect(Collectors.toList());
    }

    return new ArrayList<>();
  }

  public static <R, T> T entity2Dto(R entity, Class<T> clazz) {

    if (null == entity) {
      return null;
    }
    T dto = createInstance(clazz);
    entity2Dto(entity, dto);

    return dto;
  }

  public static <R, T> T entity2Dto(R entity, T dto) {

    if (null == entity || null == dto) {
      return null;
    }
    BeanUtils.copyProperties(entity, dto);

    return dto;
  }

  public static <R, T> R dto2Entity(T dto, Class<R> clazz) {

    if (null == dto) {
      return null;
    }
    R entity = createInstance(clazz);
    dto2Entity(dto, entity);
    return entity;
  }

  public static <R, T> R dto2Entity(T dto, R entity) {

    if (null == entity || null == dto) {
      return null;
    }
    BeanUtils.copyProperties(dto, entity);
    return entity;
  }

  public static <T> T createInstance(Class<T> cls) {
    T obj;
    try {
      obj=cls.newInstance();
    } catch (Exception e) {
      obj=null;
    }
    return obj;
  }
}