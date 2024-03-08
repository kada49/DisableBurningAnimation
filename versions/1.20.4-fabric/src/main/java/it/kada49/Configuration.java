package it.kada49;

import com.google.gson.*;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static it.kada49.DisableBurningAnimation.BURNING_ENABLED;
import static it.kada49.DisableBurningAnimation.LOGGER;

@Setter
@Getter
public class Configuration {
    private boolean burningEnabled = true;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static Configuration create(File configFile) throws RuntimeException{
        LOGGER.info("Generating new Configuration");
        Configuration config;
        try {
            Writer writer = Files.newBufferedWriter(configFile.toPath(), StandardCharsets.UTF_8);

            config = new Configuration();
            gson.toJson(gson.toJsonTree(config), writer);

            writer.close();
            return config;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Configuration get(File configFile) {
        Configuration config;
        try {
            Reader reader = Files.newBufferedReader(configFile.toPath(), StandardCharsets.UTF_8);
            JsonElement e = JsonParser.parseReader(reader);
            reader.close();
            config = gson.fromJson(e, Configuration.class);
        } catch (IOException e) { config = new Configuration(); }
        catch (JsonParseException e) {
            LOGGER.info("Could not parse config, creating new one.");
            deleteFile(configFile);
            return create(configFile);
        }

        return config;
    }

    private static void deleteFile(File configFile) throws RuntimeException {
        if (!configFile.delete()) throw new RuntimeException();
    }

    public static void update(File configFile) {
        try {
            Writer writer = Files.newBufferedWriter(configFile.toPath(), StandardCharsets.UTF_8);
            Configuration config = new Configuration();
            config.setBurningEnabled(BURNING_ENABLED);
            gson.toJson(gson.toJsonTree(config), writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
