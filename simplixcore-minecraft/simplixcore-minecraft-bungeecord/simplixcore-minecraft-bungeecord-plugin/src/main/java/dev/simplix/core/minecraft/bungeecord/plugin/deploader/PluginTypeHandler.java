package dev.simplix.core.minecraft.bungeecord.plugin.deploader;

import com.google.common.io.Files;
import dev.simplix.core.common.deploader.Dependency;
import dev.simplix.core.minecraft.bungeecord.plugin.util.PluginDescriptionUtil;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiConsumer;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.PluginDescription;
import net.md_5.bungee.api.plugin.PluginManager;

@Slf4j
public class PluginTypeHandler implements BiConsumer<Dependency, File> {

  private Field toLoadField;
  private Method enable;

  public PluginTypeHandler() {
    try {
      toLoadField = PluginManager.class.getDeclaredField("toLoad");
      toLoadField.setAccessible(true);
      enable = PluginManager.class.getDeclaredMethod(
          "enablePlugin",
          Map.class,
          Stack.class,
          PluginDescription.class);
      enable.setAccessible(true);
    } catch (ReflectiveOperationException ignored) {
    }
  }

  @Override
  public void accept(@NonNull Dependency dependency, @NonNull File file) {
    File target = new File("plugins", file.getName());
    try {
      Files.copy(file, target);
      PluginDescription pluginDescription = PluginDescriptionUtil.loadPluginYml(target);
      if (pluginDescription == null) {
        return;
      }
      if (willBeAutomaticallyLoaded(pluginDescription.getName())) {
        return;
      }
      boolean invoke = (boolean) enable.invoke(
          ProxyServer.getInstance().getPluginManager(),
          new HashMap<>(),
          new Stack<>(),
          pluginDescription);
      if (!invoke) {
        return;
      }
    } catch (Exception exception) {
      log.error("[Simplix | DependencyLoader] Unable to load plugin " + file.getName(), exception);
    }
  }

  private boolean willBeAutomaticallyLoaded(String name)
      throws ReflectiveOperationException {
    Map<String, PluginDescription> toLoad = (Map<String, PluginDescription>) toLoadField.get(
        ProxyServer.getInstance().getPluginManager());
    if (toLoad == null) {
      log.warn("[Simplix | DependencyLoader] Cannot check if "
               + name
               + " will be loaded by default plugin loader. This may occur when using outdated or unofficial BungeeCord versions.");
      return false;
    }
    log.debug("[Simplix] Checking if "
              + name
              + " will be automatically enabled. Automatically enabled will be: "
              + toLoad.keySet());
    return toLoad.containsKey(name);
  }

}
