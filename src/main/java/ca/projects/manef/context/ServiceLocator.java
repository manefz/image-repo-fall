package ca.projects.manef.context;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {
  private static ServiceLocator locator;
  private final Map<Class<?>, Object> instances = new HashMap<>();

  public static ServiceLocator getInstance() {
    if (locator == null) {
      locator = new ServiceLocator();
    }
    return locator;
  }

  public <T> void registerInstance(Class<T> contract, T instance) {
    instances.put(contract, instance);
  }

  public <T> T resolveInstance(Class<T> contract) {
    T instance = (T) instances.get(contract);
    return instance;
  }
}
