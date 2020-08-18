package dev.simplix.core.common.event;

import dev.simplix.core.common.listener.Listener;
import dev.simplix.core.common.listener.Listeners;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * Central utility class for handling events
 */
@UtilityClass
@SuppressWarnings({"rawtypes", "unchecked"})
public class Events {

  private final List<Event> knownEvents = new ArrayList<>();

  void add(final Event event) {
    knownEvents.add(event);
  }

  public List<Event> knownEvents() {
    return Collections.unmodifiableList(knownEvents);
  }

  public <T extends Event> T call(@NonNull final T event) {
    for (final Listener listener : Listeners.registeredListeners()) {
      if (listener.type() != event.getClass()) {
        continue;
      }

      listener.handleEvent(event);
      if (event.canceled()) {
        return event;
      }
    }

    return event;
  }
}
