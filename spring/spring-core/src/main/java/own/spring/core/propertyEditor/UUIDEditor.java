package own.spring.core.propertyEditor;

import java.beans.PropertyEditorSupport;
import java.util.UUID;
import org.springframework.util.StringUtils;

public class UUIDEditor extends PropertyEditorSupport {

  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    if (StringUtils.hasText(text)) {
      setValue(UUID.fromString(text));
    } else {
      setValue(null);
    }
  }

  @Override
  public String getAsText() {
    UUID value = (UUID) getValue();
    return (value != null ? value.toString() : "");
  }
}