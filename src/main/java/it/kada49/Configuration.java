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

import static it.kada49.DisableBurningAnimation.LOGGER;

@Setter
@Getter
public class Configuration {
    private boolean burningEnabled = true;

    public static Configuration create(File configFile) throws RuntimeException{
        LOGGER.info("Generating new Configuration");
        Configuration c;
        try {
            Writer writer = Files.newBufferedWriter(configFile.toPath(), StandardCharsets.UTF_8);
            c = new Configuration();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement e = gson.toJsonTree(c);

            gson.toJson(e, writer);
            writer.flush();
            writer.close();
            return c;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Configuration get(File configFile) {
        Configuration c;
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Reader reader = Files.newBufferedReader(configFile.toPath(), StandardCharsets.UTF_8);
            JsonElement e = JsonParser.parseReader(reader);
            reader.close();
            c = gson.fromJson(e, Configuration.class);
        } catch (IOException e) { c = new Configuration(); }
        catch (JsonParseException e) {
            LOGGER.info("Could not parse config, creating new one.");
            deleteFile(configFile);
            return create(configFile);
        }

        return c;
    }

    private static void deleteFile(File configFile) throws RuntimeException {
        if (!configFile.delete()) throw new RuntimeException();
    }

    public static void update(File configFile, boolean newBurningEnabled) {
        try {
            Writer writer = Files.newBufferedWriter(configFile.toPath(), StandardCharsets.UTF_8);
            Configuration c = new Configuration();
            c.setBurningEnabled(newBurningEnabled);
            JsonElement e = new Gson().toJsonTree(c);
            new Gson().toJson(e, writer);
            writer.flush();
            writer.close();
            LOGGER.info("Updated and saved config to burningEnabled = " + newBurningEnabled);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
