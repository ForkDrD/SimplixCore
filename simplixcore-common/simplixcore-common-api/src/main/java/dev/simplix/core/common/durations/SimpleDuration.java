package dev.simplix.core.common.durations;

import dev.simplix.core.common.duration.AbstractDuration;
import dev.simplix.core.common.duration.Duration;
import lombok.NonNull;

public final class SimpleDuration extends AbstractDuration {

  public SimpleDuration(long ms) {
    super(ms);
  }

  // ----------------------------------------------------------------------------------------------------
  // Convenience methods here
  // ----------------------------------------------------------------------------------------------------

  @Override
  public boolean moreThan(@NonNull Duration duration) {
    if (isPermanent()) {
      return true;
    }

    if (duration.isPermanent()) {
      return false;
    }

    return toMs() > duration.toMs();
  }

  @Override
  public boolean lessThan(@NonNull Duration duration) {
    return !moreThan(duration);
  }

  @Override
  public boolean sameAs(@NonNull Duration duration) {
    if (isPermanent() && duration.isPermanent()) {
      return true;
    }

    if (isEmpty() && duration.isEmpty()) {
      return true;
    }

    return toMs() == duration.toMs();
  }

  @Override
  public boolean isEmpty() {
    return toMs() == Long.MIN_VALUE;
  }

  @Override
  public boolean isPermanent() {
    return this.ms == -1L || this.ms == 0;
  }

  @Override
  public String toString() {
    if (isPermanent()) {
      return "Permanent";
    }
    if (isEmpty()) {
      return "Empty";
    }
    return Long.toString(toMs());
  }

  @Override
  public long toMs() {
    return this.ms;
  }
}
