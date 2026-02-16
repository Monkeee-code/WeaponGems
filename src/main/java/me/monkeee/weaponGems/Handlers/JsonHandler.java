package me.monkeee.weaponGems.Handlers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHandler {

    public static String String_reader(String gem, String path) {
        File file = new File("resources/items.json");
        try {
           String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject jsonContent = new JSONObject(content);
            JSONObject gemObject = jsonContent.getJSONObject(gem);
            return gemObject.getString(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONArray ListReader(String gem) {
        File file = new File("resources/items.json");
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject jsonObject = new JSONObject(content);
            JSONObject gemObject = jsonObject.getJSONObject(gem);
            return gemObject.getJSONArray("lore");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int SpawnChanceReader(String gem, String path) {
        File file = new File("resources/items.json");
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject jsonObject = new JSONObject(content);
            JSONObject gemObject = jsonObject.getJSONObject(gem);
            JSONObject spawnObject = gemObject.getJSONObject("spawnrate");
            return spawnObject.getInt(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
