package own.spring.core.propertyEditor;

import java.beans.PropertyEditor;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

public class CustomEditorRegistrar implements PropertyEditorRegistrar {

  private Map<Class<?>, PropertyEditor> customEditors;

  public void registerCustomEditors(PropertyEditorRegistry registry) {
    if (customEditors != null) {
      Set<Entry<Class<?>, PropertyEditor>> entries = customEditors.entrySet();
      for (Map.Entry<Class<?>, PropertyEditor> entry : entries) {
        registry.registerCustomEditor(entry.getKey(), entry.getValue());
      }
    }
  }

  public void setCustomEditors(Map<Class<?>, PropertyEditor> customEditors) {
    this.customEditors = customEditors;
  }
}