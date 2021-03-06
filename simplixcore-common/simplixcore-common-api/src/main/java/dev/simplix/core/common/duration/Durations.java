package dev.simplix.core.common.duration;

import dev.simplix.core.common.TimeFormatUtil;
import dev.simplix.core.common.durations.SimpleDuration;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * Central utility class to manage durations
 */
@UtilityClass
public class Durations {

  /**
   * @return Returns an empty punish-duration. Normally indicates that the String that should be
   * parsed had the wrong format.
   */
  public Duration empty() {
    return new SimpleDuration(Long.MIN_VALUE);
  }

  public Duration of(@NonNull Timestamp timestamp) {
    return of(timestamp.getTime());
  }

  public Duration of(@NonNull String humanReadableTime) {
    if (humanReadableTime.equalsIgnoreCase("-1")) {
      return permanent();
    }

    // Converting to seconds
    long milliseconds = TimeFormatUtil.parseToMilliseconds(humanReadableTime);

    //Invalid format
    if (milliseconds == Long.MIN_VALUE) {
      return empty();
    }
    return new SimpleDuration(milliseconds);
  }

  public Duration of(long timeInMs) {
    if (timeInMs == -1) {
      return permanent();
    }
    if (timeInMs == Long.MIN_VALUE) {
      return empty();
    }
    return new SimpleDuration(timeInMs);
  }

  public Duration of(final long time, @NonNull TimeUnit unit) {
    if (time == -1) {
      return permanent();
    }

    if (time == Long.MIN_VALUE) {
      return empty();
    }
    return new SimpleDuration(unit.toMillis(time));
  }

  public Duration permanent() {
    return new SimpleDuration(-1);
  }
}
