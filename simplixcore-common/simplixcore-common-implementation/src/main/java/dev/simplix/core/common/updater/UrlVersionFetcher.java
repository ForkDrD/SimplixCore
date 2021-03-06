package dev.simplix.core.common.updater;

import dev.simplix.core.common.ApplicationInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
public final class UrlVersionFetcher implements VersionFetcher {

  private String url;

  @Override
  public Version fetchLatestVersion(@NonNull ApplicationInfo applicationInfo,@NonNull  UpdatePolicy updatePolicy)
      throws IOException {
    URL url = new URL(this.url.replace("{name}", applicationInfo.name()));
    String latestVersion;
    try (
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url
            .openConnection()
            .getInputStream()))) {
      latestVersion = bufferedReader.readLine();
    }
    Version version;
    if (updatePolicy.versionPattern() != null) {
      version = Version.parse(updatePolicy.versionPattern(), latestVersion);
    } else {
      version = Version.parse(latestVersion);
    }
    return version;
  }

}
