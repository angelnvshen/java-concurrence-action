package own.spring.core.propertyEditor;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.Locale;
import java.text.ParseException;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.util.StringUtils;

public class CustomDateEditor extends PropertyEditorSupport {

  /**
   * Date的转换模板
   */
  private final String pattern;

  private final boolean allowEmpty;

  private final DateFormatter dateFormatter;

  public CustomDateEditor(String pattern) {
    this(pattern, true);
  }

  public CustomDateEditor(String pattern, boolean allowEmpty) {
    this.pattern = pattern;
    this.allowEmpty = allowEmpty;
    this.dateFormatter = new DateFormatter(this.pattern);
  }

  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    if (!StringUtils.hasText(text) && this.allowEmpty) {
      // 字符为""或者null，并且属性编辑器允许属性为null，把属性设为null
      setValue(null);
    } else if (!StringUtils.hasText(text)) {
      // 字符为""或者null，并且属性编辑器不允许属性为null，抛异常
      throw new IllegalArgumentException(
          "date string is empty, but allowEmpty argument is false");
    } else {
      try {
        setValue(this.dateFormatter.parse(text, Locale.getDefault()));
      } catch (ParseException ex) {
        throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
      }
    }
  }

  @Override
  public String getAsText() {
    Date value = (Date) getValue();
    return (value != null ? this.dateFormatter.print(value, Locale.getDefault()) : "");
  }

}