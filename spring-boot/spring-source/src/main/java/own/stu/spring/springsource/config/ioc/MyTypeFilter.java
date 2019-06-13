package own.stu.spring.springsource.config.ioc;

import java.beans.Transient;
import java.io.IOException;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Controller;

public class MyTypeFilter implements TypeFilter {

  /**
   * 自定义匹配规则，用于指定哪些类可以被扫描到
   *
   * @param metadataReader 用于当前被扫描类的元数据 读取
   */
  @Override
  public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
    // 当前被扫描类的注解信息
    AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
    System.out.println(annotationMetadata.getAnnotationTypes());

    // 当前被扫描类的类信息
    ClassMetadata classMetadata = metadataReader.getClassMetadata();
    System.out.println(classMetadata.getClassName());

    // 当前被扫描类的资源信息（类的路径 ... )
    Resource resource = metadataReader.getResource();

    if (annotationMetadata.getAnnotationTypes().contains(Controller.class.getCanonicalName())) {
      return true;
    }

    return false;
  }
}
