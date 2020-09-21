package dev.simplix.core.minecraft.bungeecord.plugin.util;

import com.google.common.base.Preconditions;
import java.io.File;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.PluginDescription;
import org.yaml.snakeyaml.Yaml;

public final class PluginDescriptionUtil {

  private static final Yaml YAML = new Yaml();

  private PluginDescriptionUtil() {}

  public static PluginDescription loadPluginYml(File target) {
    try (JarFile jar = new JarFile(target)) {
      JarEntry pdf = jar.getJarEntry("bungee.yml");
      if (pdf == null) {
        pdf = jar.getJarEntry("plugin.yml");
      }
      Preconditions.checkNotNull(pdf, "Plugin must have a plugin.yml or bungee.yml");

      try (InputStream in = jar.getInputStream(pdf)) {
        PluginDescription desc = YAML.loadAs(in, PluginDescription.class);
        Preconditions.checkNotNull(desc.getName(), "Plugin from %s has no name", target);
        Preconditions.checkNotNull(desc.getMain(), "Plugin from %s has no main", target);

        desc.setFile(target);
        return desc;
      }
    } catch (Exception ex) {
      ProxyServer
          .getInstance()
          .getLogger()
          .log(Level.WARNING, "Could not load plugin from file " + target, ex);
    }
    return null;
  }

}