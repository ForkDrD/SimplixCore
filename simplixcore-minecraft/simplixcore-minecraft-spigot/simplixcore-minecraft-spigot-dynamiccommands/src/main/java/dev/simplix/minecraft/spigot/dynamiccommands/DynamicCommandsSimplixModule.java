package dev.simplix.minecraft.spigot.dynamiccommands;

import dev.simplix.core.common.aop.AbstractSimplixModule;
import java.lang.reflect.Field;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

@Slf4j
public class DynamicCommandsSimplixModule extends AbstractSimplixModule {

  {
    registerComponentInterceptor(
        Command.class,
        this::registerCommand);
  }

  public void registerCommand(@NonNull final Command command) {
    try {
      final Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
      commandMapField.setAccessible(true);

      final CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
      commandMap.register(command.getLabel(), command);
    } catch (final Throwable throwable) {
      log.error("Unable to register command " + command.getName(), throwable);
    }
  }

}
