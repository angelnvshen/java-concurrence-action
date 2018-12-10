package own.stu.com.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.google.common.base.Charsets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleApolloConfigDemo {

  private String DEFAULT_VALUE = "undefined";
  private Config config;

  public SimpleApolloConfigDemo() {

    System.setProperty("apollo.meta", "http://172.16.2.67:8080");

    ConfigChangeListener changeListener = new ConfigChangeListener() {
      @Override
      public void onChange(ConfigChangeEvent changeEvent) {
        log.info("Changes for namespace {}", changeEvent.getNamespace());
        for (String key : changeEvent.changedKeys()) {
          ConfigChange change = changeEvent.getChange(key);
          log.info("Change - key: {}, oldValue: {}, newValue: {}, changeType: {}",
              change.getPropertyName(), change.getOldValue(), change.getNewValue(),
              change.getChangeType());
        }
      }
    };
    config = ConfigService.getAppConfig();
    config.addChangeListener(changeListener);
  }

  private String getConfig(String key) {
    String result = config.getProperty(key, DEFAULT_VALUE);
    log.info(String.format("Loading key : %s with value: %s", key, result));
    return result;
  }

  public static void main(String[] args) throws IOException {
    SimpleApolloConfigDemo apolloConfigDemo = new SimpleApolloConfigDemo();
    System.out.println(
        "Apollo Config Demo. Please input key to get the value. Input quit to exit.");
    while (true) {
      System.out.print("> ");
      String input = new BufferedReader(new InputStreamReader(System.in, Charsets.UTF_8)).readLine();
      if (input == null || input.length() == 0) {
        continue;
      }
      input = input.trim();
      if (input.equalsIgnoreCase("quit")) {
        System.exit(0);
      }
      apolloConfigDemo.getConfig(input);
    }
  }
}
