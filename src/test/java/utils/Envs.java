package utils;

import io.github.cdimascio.dotenv.Dotenv;

import org.jspecify.annotations.NonNull;

import java.util.Objects;

public final class Envs {
  private static final Dotenv $ = Dotenv.configure()
    .directory(getResourcesDir())
    .filename(".env.staging")
    .ignoreIfMissing()
    .load();

  public static final String BASE_URI = get("BASE_URI");
  public static final int DEFAULT_EXPLICIT_WAIT_TIMEOUT = getInt("DEFAULT_EXPLICIT_WAIT_TIMEOUT");

  private Envs() {}
  private static String getResourcesDir() {
    return Objects.requireNonNull(Envs.class.getClassLoader().getResource(".")).getPath();
  }

  public static String get(@NonNull String key) {
    return $.get(key);
  }

  public static int getInt(@NonNull String key) {
    return Integer.parseInt(get(key));
  }
}
