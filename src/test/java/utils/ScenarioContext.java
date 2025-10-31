package utils;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
  private final Map<String, Object> context = new HashMap<>();

  public void set(String key, Object value) {
    context.put(key, value);
  }

  public Object get(String key) {
    return context.get(key);
  }

  public <T> T get(String key, Class<T> type) {
    return type.cast(context.get(key));
  }
}
