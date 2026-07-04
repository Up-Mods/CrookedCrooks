package io.github.ennuil.crooked_crooks.satire_config;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.util.Util;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.Supplier;

/*
 * Satire Config v1
 *
 * this config system is a joke lmfao
 * Do not dare to steal this and scale it up to more than one config option,
 * I'm not the one who will murder you, it will be people who care about performance!
 * (IO operations are expensive, this is why there's only one config option)
 */
public class SatireConfig {
	private static Supplier<Path> configPath = () -> null;
	private static final Logger LOGGER = LogUtils.getLogger();

	private static final String SHEPHERDS_TOUCH_DESCRIPTION = """
		If enabled, the crook will give its user the Shepherd's Touch effect on right-clicking a sheep, attracting all the sheep to them.

		Drag and drop this file into the "disabled" folder in order to disable this feature.
		""";
	private static final String README_TEXT = """
		Config Version v1

		Drag and drop features into the "disabled" folder in order to disable them!

		Delete this file (!README.txt) in order to reset your settings.
		""";

	private static ModConfig config;

	public static void init() {
		try {
			var configPath = SatireConfig.configPath.get();
			if (Files.notExists(configPath)) {
				Files.createDirectory(configPath);
				Files.writeString(configPath.resolve("!README.txt"), README_TEXT);
				Files.writeString(configPath.resolve("shepherds_touch.txt"), SHEPHERDS_TOUCH_DESCRIPTION);
				Files.createDirectory(configPath.resolve("disabled"));
				SatireConfig.config = new ModConfig(true);
			} else {
				if (Files.exists(configPath.resolve("!README.txt"))) {
					if (!Files.readString(configPath.resolve("!README.txt")).startsWith("Config Version v1")) {
						LOGGER.warn("Invalid config version was detected! Resetting the config.");
						Files.walkFileTree(configPath, new SimpleFileVisitor<>() {
							@Override
							public FileVisitResult visitFile(@NotNull Path file, BasicFileAttributes attrs) throws IOException {
								Files.delete(file);
								return FileVisitResult.CONTINUE;
							}

							@Override
							public FileVisitResult postVisitDirectory(@NotNull Path dir, IOException exc) throws IOException {
								if (exc == null) {
									Files.delete(dir);
									return FileVisitResult.CONTINUE;
								} else {
									throw exc;
								}
							}
						});
						Files.createDirectory(configPath);
						Files.writeString(configPath.resolve("!README.txt"), README_TEXT);
						Files.writeString(configPath.resolve("shepherds_touch.txt"), SHEPHERDS_TOUCH_DESCRIPTION);
						Files.createDirectory(configPath.resolve("disabled"));
						SatireConfig.config = new ModConfig(true);

						return;
					}
				}

				boolean shepherdsTouch = true;
				boolean enabledFileExists = Files.exists(configPath.resolve("shepherds_touch.txt"));
				boolean disabledFileExists = Files.exists(configPath.resolve("disabled").resolve("shepherds_touch.txt"));
				if (disabledFileExists) {
					if (enabledFileExists) {
						Files.delete(configPath.resolve("shepherds_touch.txt"));
					}

					shepherdsTouch = false;
				} else if (!enabledFileExists) {
					Files.writeString(configPath.resolve("disabled").resolve("shepherds_touch.txt"), SHEPHERDS_TOUCH_DESCRIPTION);
					shepherdsTouch = false;
				}

				SatireConfig.config = new ModConfig(shepherdsTouch);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static ModConfig getConfig() {
		return SatireConfig.config;
	}

	public static Path getConfigPath() {
		return SatireConfig.configPath.get();
	}

	public static void setConfigPath(Path path) {
		SatireConfig.configPath = () -> path;
	}

	public static Screen wrapConfigScreen(Screen screen) {
		Util.getPlatform().openPath(SatireConfig.getConfigPath());
		return screen;
	}
}
